pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh(script: 'ls -al')
                sh(script: 'java --version')
                echo 'Building..'
                sh(script: 'chmod +x gradlew')
                sh(script: './gradlew clean build')
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
