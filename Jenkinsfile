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
                    choice(choices: ['dev', 'final', 'rc', 'SNAPSHOT', 'snapshot'], description: 'Kind of release to create.', name: 'RELEASE_STAGE'),
                    choice(choices: ['', 'minor', 'major', 'patch'], description: 'Version number incrementation.', name: 'RELEASE_SCOPE'),
                    string(defaultValue: '', description: 'Version to release.', name: 'RELEASE_VERSION', trim: true)
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
    if (doRelease) {
        if (releaseVersion != '') {
            releaseParams += " -Prelease.version=${releaseVersion}"
        } else if (releaseScope != '') {
            releaseParams += " -Prelease.scope=${releaseScope}"
        }
        releaseParams += " -Prelease.stage=${releaseStage}"
    }

    stage('checkout') {
        // replace git remote URL by SSH version
//        sh 'git remote set-url origin $(git remote -v | grep origin | head -1 | awk "{print \\$2}" | sed -r "s|^https://(.+)/([^/]+/[^/]+\\.git)$|git@\\1:\\2|")'
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
                         email: "${env.CHANGE_AUTHOR_EMAIL}",
                         name: "Jenkins (on behalf of ${env.CHANGE_AUTHOR_DISPLAY_NAME})"],
                        [$class : 'PreBuildMerge',
                                     options: [fastForwardMode: 'NO_FF',
                                               mergeRemote    : 'origin',
                                               mergeTarget    : 'master']]
                ]
        ]
//        sh "git checkout -B ${env.BRANCH_NAME} HEAD"
    }

    stage('build') {
        sh "sh ./gradlew ${releaseParams} assemble"
    }

    stage('test') {
        sh "sh ./gradlew ${releaseParams} test"
    }
}
