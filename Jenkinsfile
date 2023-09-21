pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh(script: 'ls -al')
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
