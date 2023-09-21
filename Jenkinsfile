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
                sh(script: './gradlew bootRun')
                sh(script: 'curl localhost:8080')
                sh(script: 'curl localhost:8080/article')
                sh(script: 'curl localhost:8080/authors')
                sh(script: 'curl localhost:8080/categories')
                sh(script: 'sleep 10')
                sh(script: 'exit 0')
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
