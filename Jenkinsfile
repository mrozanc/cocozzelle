node('linux') {
    properties([
            parameters([
                    booleanParam(defaultValue: false, description: 'If true, perform a release.', name: 'DO_RELEASE'),
                    gitParameter(branch: "refs/remotes/origin/${env.BRANCH_NAME}",
                            branchFilter: '.*',
                            defaultValue: 'NONE',
                            description: '''This field can be used to chose a commit from the selected branch.
If it is selected, it will only allow rc, dev and devSnapshot stages and nothing will be merged into master.''',
                            listSize: '20',
                            name: 'COMMIT_TO_RELEASE',
                            quickFilterEnabled: true,
                            selectedValue: 'NONE',
                            sortMode: 'DESCENDING',
                            tagFilter: '*',
                            type: 'PT_REVISION'),
                    choice(choices: ['dev', 'rc', 'final'], description: 'Kind of release to create.',
                            name: 'RELEASE_STAGE'),
                    choice(choices: ['', 'minor', 'major', 'patch'], description: 'Optional version number incrementation. If not set it will be inferred from current branch and existing tags.',
                            name: 'RELEASE_SCOPE'),
                    string(defaultValue: '', description: 'Optional version to release. Let empty to infer version.',
                            name: 'RELEASE_VERSION', trim: true)
            ])
    ])

    boolean doRelease = params.DO_RELEASE
    boolean doMerge = false
    String releaseVersion = params.RELEASE_VERSION
    String releaseScope = params.RELEASE_SCOPE
    String releaseStage = params.RELEASE_STAGE
    if (!doRelease && env.BRANCH_NAME == 'develop') {
        doRelease = true
        releaseStage = 'SNAPSHOT'
    }

    String releaseParams = ''
    if (env.BRANCH_NAME.matches(/^hotfix\/.+/) && releaseVersion == '') {
        if (releaseScope != '' && releaseScope != 'patch') {
            error "Specified RELEASE_SCOPE='$releaseScope' differs from 'patch' on branch '${env.BRANCH_NAME}'"
        }
        releaseStage = 'patch'
    }
    if (releaseScope != '' && releaseVersion == '') {
        releaseParams += " -Prelease.scope=${releaseScope}"
    }

    String releaseCommand = ''
    if (doRelease) {
        if (params.COMMIT_TO_RELEASE == 'NONE') {
            doMerge = true
        }
        if (releaseVersion != '') {
            releaseParams += " -Prelease.version=${releaseVersion}"
        } else {
            releaseParams += " -Prelease.stage=${releaseStage}"
        }
        if (!doMerge && releaseStage == 'final') {
            error 'Release without merge selected with "final" stage. Merging into master is mandatory for a final release.'
        }
        switch (releaseStage) {
            case 'final':
                releaseCommand = 'final'
                break
            case 'rc':
                releaseCommand = 'candidate'
                break
            case 'dev':
                releaseCommand = 'devSnapshot'
                break
            case 'SNAPSHOT':
                releaseCommand = 'snapshot'
                break
        }
    }

    String branchName = env.CHANGE_ID ? env.CHANGE_BRANCH : env.BRANCH_NAME
    stage('checkout') {
        checkout scm
        // Fetch to retrieve tags AND being on right branch is necessary to infer version

        sshagent(['github-deploy-ssh-key']) {
            sh "git fetch --prune-tags --tags --force"
            if (doRelease) {
                sh 'git remote set-url origin $(git remote -v | grep origin | head -1 | awk "{print \\$2}" | sed -r "s|^https://(.+)/([^/]+/[^/]+\\.git)$|git@\\1:\\2|")'
                version = releaseVersion
                if (releaseVersion == '') {
                    String inferredVersion = sh returnStdout: true, script: "sh ./gradlew properties -q -Prelease.stage=${releaseStage} | grep '^version:' | awk '{print \$2}'"
                    version = inferredVersion.trim()
                }
                if (params.COMMIT_TO_RELEASE != 'NONE') {
                    sh "git checkout ${env.COMMIT_TO_RELEASE}"
                } else if (doMerge) {
                    if (releaseVersion == '') {
                        releaseParams += " -Prelease.version=${version}"
                    }
                    wrap([$class: 'BuildUser']) {
                        sh "git config user.name 'Jenkins (on behalf of ${env.BUILD_USER})'"
                        sh "git config user.email '${env.BUILD_USER_EMAIL}'"
                    }
                    sh "git checkout ${branchName}"
                    sh "git fetch --force origin master:master"
                    sh "git checkout master"
                    sh "git merge ${branchName} --no-ff -m 'REL ${version}'"
                }
            }
        }
    }

    stage('build') {
        sh "./gradlew clean ${releaseParams} assemble"
    }

    stage('test') {
        try {
            sh "./gradlew ${releaseParams} check"
        } finally {
            junit '**/build/test-results/**/*.xml'
        }
    }

    stage('publication') {
        withCredentials([usernamePassword(credentialsId: 'github-jenkins-package-token', passwordVariable: 'GPR_PACKAGE_KEY', usernameVariable: 'GPR_PACKAGE_USER')]) {
            sh "./gradlew publish"
        }
        archiveArtifacts artifacts: '**/build/libs/**/*'
    }

    if (doRelease) {
        stage('release') {
            sshagent(['github-deploy-ssh-key']) {
                sh "./gradlew ${releaseCommand} ${releaseParams}"
                if (doMerge) {
                    sh "git push origin master"
                }
            }
        }
    }
}
