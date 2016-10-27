



node{
    
    notify('Started');

    try{

        stage('checkout') {
            git 'https://github.com/nagabhushanamn/spring-boot.git'
        }
        
        def project_path='spring-boot-samples/spring-boot-sample-atmosphere';
        
        dir(project_path) {
        
            stage('compile,test and packaging') {
            sh "mvn clean package"
            }

            stage('archival') {
                step([$class:'ArtifactArchiver',
                artifacts:"target/*.jar",
                excludes:null])
            }
        
        }

        notify('success');

    }catch(err){
        notify("Error ${err}");
        currentBuild.result = 'FAILURE'
    }
    


}



def notify(status){
    emailext (
      to: 'wesmdemos@gmail.com',
      subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body:"""" <p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'</p>"
                <p>Check console output at <a href='${env.BUILD_URL}'> ${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>"""
    )
}
