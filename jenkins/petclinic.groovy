node {
    
    notify('Started')
    
    try {
        stage 'checkout'
        git 'https://github.com/nagabhushanamn/jenkins2-course-spring-petclinic'

        stage 'compiling, test, packaging'
        // on windows use: bat 'mvn clean verify'
        sh 'mvn clean verify'
    
        
    } catch (err) {
        notify("Error ${err}")
        currentBuild.result = 'FAILURE'
    }
    
    stage 'archival'
    // publishHTML(target: [allowMissing: true, 
    //                 alwaysLinkToLastBuild: false, 
    //                 keepAll: true, 
    //                 reportDir: 'target/site/jacoco/', 
    //                 reportFiles: 'index.html', 
    //                 reportName: 'Code Coverage'])
    
    step([$class: 'JUnitResultArchiver', 
          testResults: 'target/surefire-reports/TEST-*.xml'])

    step([$class: 'ArtifactArchiver', 
           artifacts: "target/*.?ar", 
           excludes: null])
    
}

def notify(status){
    emailext (
      to: "wesmdemos@gmail.com",
      subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """<p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
        <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
    )
}


       

