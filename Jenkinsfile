node('linux') {
    properties([parameters([booleanParam(defaultValue: false, description: 'If true, perform a release.', name: 'DO_RELEASE'),
                            string(defaultValue: '', description: 'Version to release.', name: 'RELEASE_VERSION', trim: false),
                            choice(choices: ['devSnapshot', 'final', 'candidate', 'snapshot'], description: 'Kind of release to create.', name: 'RELEASE_STAGE')])])

    stage('checkout') {
        checkout scm: [$class: 'GitSCM', extensions: [[$class: 'LocalBranch', localBranch: "${env.BRANCH_NAME}"]], userRemoteConfigs: [[refspec: '+refs/heads/*:refs/remotes/origin/*']]]
    }

    stage('build') {
        sh "sh ./gradlew -Prelease.stage='${params.RELEASE_STAGE}' assemble"
    }

    stage('test') {
        sh './gradlew test'
    }
}
