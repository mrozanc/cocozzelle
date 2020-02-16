node('linux') {
    properties([parameters([string(defaultValue: '', description: 'Version to release', name: 'RELEASE_VERSION', trim: false),
                            choice(choices: ['final', 'candidate', 'snapshot', 'devSnapshot'], description: 'Kind of release to create', name: 'RELEASE_STAGE')])])

    stage('checkout') {
        checkout scm
    }

    stage('build') {
        sh 'chmod +x ./gradlew'
        sh './gradlew assemble'
    }

    stage('test') {
        sh './gradlew test'
    }
}
