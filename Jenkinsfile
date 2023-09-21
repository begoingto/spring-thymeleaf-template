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
                sh(script: 'ls -al')
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh(script: './gradlew test')
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
