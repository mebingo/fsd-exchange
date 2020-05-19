pipeline {
  agent none
  environment {
    DOCKERHUBNAME = "ssn717"
  }
  stages {
    stage('maven Build') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '-v /root/.m2:/root/.m2'
        }
      }
      steps {
        sh 'mvn -B -DskipTests clean package'
        // sh 'mvn package -Dmaven.test.skip=true'
        // sh 'mvn clean package'
      }
    }

    stage('docker build & push & run') {
      agent any
      steps {
        script {
          def REMOVE_FLAG_C = sh(returnStdout: true, script: "docker container ls -q --filter name=.*FSD-Exchange.*") != ""
          echo "REMOVE_FLAG_C: ${REMOVE_FLAG_C}"
          if(REMOVE_FLAG_C){
            sh 'docker container rm -f $(docker container ls -q --filter name=.*FSD-Exchange.*)'
          }
          def REMOVE_FLAG = sh(returnStdout: true, script: "docker image ls -q *${DOCKERHUBNAME}/exchange*") != ""
          echo "REMOVE_FLAG: ${REMOVE_FLAG}"
          if(REMOVE_FLAG){
            sh 'docker image rm -f $(docker image ls -q *${DOCKERHUBNAME}/exchange*)'
          }
        }

        withCredentials([usernamePassword(credentialsId: 'ssn717ID', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
          // sh 'docker login -u $USERNAME -p $PASSWORD'
          sh 'docker image build -t ${DOCKERHUBNAME}/exchange .'
          // sh 'docker push ${DOCKERHUBNAME}/exchange'
          // sh 'docker run -d -p 8754:8754 --network fsd-net --name fsdexchange ${DOCKERHUBNAME}/exchange'
          sh 'docker run -d -p 8754:8754 --memory=400M --network fsd-net --name FSD-Exchange ${DOCKERHUBNAME}/exchange'
        }
      }
    }

    stage('clean workspace') {
      agent any
      steps {
        // clean workspace after job finished
        cleanWs()
      }
    }
  }
}
