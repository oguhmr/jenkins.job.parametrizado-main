job('ejemplo3-job-DSL') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  	scm {
      		git('https://github.com/oguhmr/jenkins.job.parametrizado-main.git', 'develop') { node ->
        		node / gitConfigName('oguhmr')
        		node / gitConfigEmail('oguhmr@gmail.com')
      		}
    	} 
  	parameters {
   		stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el Job Booleano')
      		choiceParam('planeta', ['Mercurio', 'Venus', 'Tierrra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      		booleanParam('agente', false)
    	}
  	triggers {
    		cron('H/7 * * * *')
        	githubPush()
    	}
  	steps {
    		shell("bash jobscript.sh")
    	}
  	publishers {
      		mailer('oguhmr@gmail.com', true, true)
      		slackNotifier {
		  notifyAborted(true)
		  notifyEveryFailure(true)
		  notifyNotBuilt(false)
		  notifyUnstable(false)
		  notifyBackToNormal(true)
		  notifySuccess(false)
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
