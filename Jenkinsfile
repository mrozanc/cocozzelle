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
                            tagFilter: '*', type: 'PT_REVISION'),
                    choice(choices: ['snapshot', 'rc', 'final'], description: 'Kind of release to create.',
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
        if (releaseVersion != '') {
            releaseParams += " -Prelease.version=${releaseVersion}"
        } else {
            releaseParams += " -Prelease.stage=${releaseStage}"
        }
        if (params.COMMIT_TO_RELEASE == 'NONE') {
            doMerge = true
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
            case 'snapshot':
                releaseCommand = 'devSnapshot'
                break
            case 'SNAPSHOT':
                releaseCommand = 'snapshot'
                break
        }
    }

    stage('checkout') {
        // replace git remote URL by SSH version
        checkout scm: [
                $class           : 'GitSCM',
                branches         : [[name: "refs/remotes/origin/${env.BRANCH_NAME}"]],
                userRemoteConfigs: [
                        [credentialsId: 'github-mrozanc-login',
                         name         : 'origin',
                         refspec      : '+refs/heads/*:refs/remotes/origin/*',
                         url          : 'https://github.com/mrozanc/cocozzelle.git']
                ],
                extensions       : [
                        [$class: 'UserIdentity',
                         email : "${env.CHANGE_AUTHOR_EMAIL}",
                         name  : "Jenkins (on behalf of ${env.CHANGE_AUTHOR_DISPLAY_NAME})"],
//                        [$class : 'PreBuildMerge',
//                                     options: [fastForwardMode: 'NO_FF',
//                                               mergeRemote    : 'origin',
//                                               mergeTarget    : 'master']]
                ]
        ]
        if (doRelease) {
            sh 'git remote set-url origin $(git remote -v | grep origin | head -1 | awk "{print \\$2}" | sed -r "s|^https://(.+)/([^/]+/[^/]+\\.git)$|git@\\1:\\2|")'
        }
//        sh "git checkout -B ${env.BRANCH_NAME} HEAD"
    }

    stage('build') {
        sh "sh ./gradlew ${releaseParams} assemble"
    }

    stage('test') {
        sh "sh ./gradlew ${releaseParams} test"
    }

    if (doRelease) {
        stage('release') {
            sshagent(['github-deploy-ssh-key']) {
                sh "sh ./gradlew ${releaseCommand} ${releaseParams}"
            }
        }
    }
}
