pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh(script: 'ls -al')
                sh(script: 'java --version')
                echo 'Building..'
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
