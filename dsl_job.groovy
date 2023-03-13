job('ejemplo_Primer_JobDSL'){
	description("Jod DSL to explain our fist setps in Jenkins with DSL")
    
  	parameters{
      stringParam('name', defaultValue = 'Marco AG', description = 'String PArameter for the Job DSL')
      choiceParam('Planet',['Tierra (default)', 'Mercurio', 'Venus', 'Marte', 'Jupiter'])
      booleanParam('Agent', false)
    }
  
    scm{
        git('https://github.com/macloujulian/jenkins.job.parametrizado.git', 'main') { node ->
            node / gitConfigName('Marco Antonio AG')
            node / gitConfigEmail('marcontonio.98@hotmail.com')
        }
      }
  	
    triggers{
      cron('H/7 * * * *')
    }
  
    steps{
      shell("bash jobscript.sh")
    }
    
    publishers {
      mailer('macloujulian@gmail.com', false, true)
      slackNotifier {
        notifyAborted(true)
        notifyEveryFailure(true)
        notifyNotBuilt(true)
        notifyUnstable(false)
        notifyBackToNormal(true)
        notifySuccess(true)
        notifyRepeatedFailure(false)
        startNotification(false)
        includeTestSummary(false)
        includeCustomMessage(false)
        customMessage(null)
        sendAs(null)
        commitInfoChoice('NONE')
        teamDomain(null)
        authToken(null)
      }
    }
  
}
