node('linux') {
    properties([
            parameters([
                    booleanParam(defaultValue: false, description: 'If true, perform a release.', name: 'DO_RELEASE'),
                    choice(choices: ['dev', 'final', 'rc', 'SNAPSHOT', 'snapshot'], description: 'Kind of release to create.', name: 'RELEASE_STAGE'),
                    choice(choices: ['', 'minor', 'major', 'patch'], description: 'Version number incrementation.', name: 'RELEASE_SCOPE'),
                    string(defaultValue: '', description: 'Version to release.', name: 'RELEASE_VERSION', trim: true)
            ])
    ])

    String releaseParams = ''
    if (params.DO_RELEASE) {
        if (params.RELEASE_VERSION != '') {
            releaseParams += " -Prelease.version=${params.RELEASE_VERSION}"
        } else if (params.RELEASE_SCOPE != '') {
            releaseParams += " -Prelease.scope=${params.RELEASE_SCOPE}"
        }
        releaseParams += " -Prelease.stage=${params.RELEASE_STAGE}"
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
