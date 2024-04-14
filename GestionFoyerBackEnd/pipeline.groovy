pipeline {
    agent any
    stages {
        stage("Compile Application") {
            steps {
                dir('GestionFoyerBackEnd') {
                    script {
                        sh "mvn clean compile"
                    }
                }
            }
        }
        stage("Execute Tests and Capture Results") {
            steps {
                dir('GestionFoyerBackEnd') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    dir('GestionFoyerBackEnd') {
                        junit 'target/surefire-reports/TEST-*.xml'
                    }
                }
            }
        }
        stage("Code Quality and Analysis") {
            steps {
                dir('GestionFoyerBackEnd') {
                    script {
                        jacoco()
                        withSonarQubeEnv('sonar') {
                            sh "mvn verify sonar:sonar"
                        }
                    }
                }
            }
        }
        stage("Quality Gate Check and Jar Creation") {
            steps {
                script {
                    sleep(10)
                    timeout(time: 1, unit: 'HOURS') {
                        def gateResult = waitForQualityGate()
                        if (gateResult.status != 'OK') {
                            error "Failed at quality gate: ${gateResult.status}"
                        }
                    }
                    dir('GestionFoyerBackEnd') {
                        sh "mvn package"
                    }
                }
            }
        }
        stage("Publish to Nexus and Build Docker Images") {
            steps {
                dir('GestionFoyerBackEnd') {
                    script {
                        def artifact = 'target/GestionFoyerBackEnd-0.0.1-SNAPSHOT.jar'
                        nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: 'localhost:8081',
                            groupId: 'tn.esprit',
                            version: '1.0',
                            repository: 'maven-releases',
                            credentialsId: 'nexus',
                            artifacts: [
                                [artifactId: 'GestionFoyerBackEnd',
                                 classifier: '',
                                 file: artifact,
                                 type: 'jar']
                            ]
                        )
                        echo "Building and pushing Docker image for backend"
                        withCredentials([usernamePassword(credentialsId: "dockerhub", usernameVariable: "USER", passwordVariable: "PWD")]) {
                            sh "echo $PWD | docker login -u $USER --password-stdin"
                            sh "docker build -t anasbenouaghrem/gestionfoyer:1.0 ."
                            sh "docker push anasbenouaghrem/gestionfoyer:1.0"
                        }
                    }
                }
                dir('GestionFoyerFrontEnd') {
                    script {
                        echo "Building and pushing Docker image for frontend"
                        withCredentials([usernamePassword(credentialsId: "dockerhub", usernameVariable: "USER", passwordVariable: "PWD")]) {
                            sh "echo $PWD | docker login -u $USER --password-stdin"
                            sh "docker build -t anasbenouaghrem/gestionfoyerfront:1.0 ."
                            sh "docker push anasbenouaghrem/gestionfoyerfront:1.0"
                        }
                    }
                }
            }
        }
        stage('Manage Docker and Monitoring Services') {
            steps {
                echo 'Activating Docker Compose services...'
                sh 'docker compose up -d'
                script {
                    manageContainer("prometheus", 9090)
                    manageContainer("grafana", 3000)
                }
            }
        }
    }
}

def manageContainer(String containerName, int port) {
    def containerExists = sh(script: "docker ps -a --format '{{.Names}}' | grep ${containerName}", returnStatus: true)
    if (containerExists != 0) {
        echo "${containerName} container is being launched..."
        sh "docker run -d -p ${port}:${port} --name ${containerName} ${containerName}/${containerName}"
    } else {
        echo "Restarting ${containerName} container..."
        sh "docker stop ${containerName}"
        sleep(5)
        sh "docker start ${containerName}"
    }
}
