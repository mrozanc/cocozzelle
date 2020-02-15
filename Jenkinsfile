node {
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
