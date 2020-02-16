node('linux') {
    properties([
            parameters([
                    booleanParam(defaultValue: false, description: 'If true, perform a release.', name: 'DO_RELEASE'),
                    choice(choices: ['dev', 'final', 'rc', 'SNAPSHOT', 'snapshot'], description: 'Kind of release to create.', name: 'RELEASE_STAGE'),
                    choice(choices: ['', 'minor', 'major', 'patch'], description: 'Version number incrementation.', name: 'RELEASE_SCOPE'),
                    string(defaultValue: '', description: 'Version to release.', name: 'RELEASE_VERSION', trim: true)
            ])
    ])

    boolean doRelease = params.DO_RELEASE
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
        checkout scm
        sh "git checkout -B ${env.BRANCH_NAME} HEAD"
    }

    stage('build') {
        sh "sh ./gradlew ${releaseParams} assemble --info"
    }

    stage('test') {
        sh "sh ./gradlew ${releaseParams} test"
    }
}
