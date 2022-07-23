pipeline {
    
    environment{
        imageName=""
    }
    agent any

    stages {
        stage('Git pull') {
            steps {
                git branch: 'main', credentialsId: 'ansible', url: 'https://github.com/vishalgovil/BLOG-API.git'
            }
        }
        
        
        stage('Maven Build') {
            steps {
                script{
                    sh 'mvn clean install'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script{
                    imageName = docker.build "vishalgovil/blog_api:latest"
                    
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                script{
                    docker.withRegistry('','docker-jenkins')
                    {
                        imageName.push()
                    }
                }
            }
            }
            
        stage('Ansible Pull Docker Image') {
            steps {
                ansiblePlaybook becomeUser: null, colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'ansible/inventory.txt', playbook: 'ansible/playbook.yml', sudoUser: null
                }
            }    
            
        
    }
}
