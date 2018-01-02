@Library('jenkins-helpers@java-8')_

pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        buildStep()
        doCheckstyle()
        doSpotbugs()
        doPmd()
      }
    }
    stage('Document and Deploy') {
      // run this stage only when on master in the original repository and build is successful
      when {
        environment name: 'CHANGE_FORK', value: ''
        expression { GIT_URL ==~ 'https://github.com/sw4j-org/.*' }
        expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
      }
      steps {
        doDeploy()
        publishSite()
      }
    }
  }
  post {
    always {
      resultMailer()
    }
  }
}
