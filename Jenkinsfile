node {
    stage('checkout') {
        checkout scm
    }

    stage('build') {
        sh 'chmod +x ./gradlew'
        sh './gradlew build'
    }
}
