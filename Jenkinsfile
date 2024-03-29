pipeline {
    agent any
    
    tools {
        maven 'maven'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    git branch: 'master', url: 'https://github.com/jglick/simple-maven-project-with-tests.git'
                    bat "mvn -Dmaven.test.failure.ignore=true clean package"
                }
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to QA") {
            steps {
                echo "Deploy to QA"
            }
        }

        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    script {
                        git branch: 'master', url: 'https://github.com/Sugumaraja/AutomationFlipkartSample.git'
                        bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/testng_regression.xml"
                    }
                }
            }
        }

        stage('Publish Allure Reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }

        stage('Publish Extent Report') {
            steps {
                script {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'TestExecutionReport.html',
                        reportName: 'HTML Regression Extent Report',
                        reportTitles: ''
                    ])
                }
            }
        }

        stage("Deploy to Stage") {
            steps {
                echo "Deploy to Stage"
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    script {
                        git branch: 'master', url: 'https://github.com/Sugumaraja/AutomationFlipkartSample.git'
                        bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/testng_sanity.xml"
                    }
                }
            }
        }

        stage('Publish Sanity Extent Report') {
            steps {
                script {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'TestExecutionReport.html',
                        reportName: 'HTML Sanity Extent Report',
                        reportTitles: ''
                    ])
                }
            }
        }

        stage("Deploy to PROD") {
            steps {
                echo "Deploy to PROD"
            }
        }
    }
}
