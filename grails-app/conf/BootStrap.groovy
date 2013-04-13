import grails.util.GrailsUtil

import org.codehaus.groovy.grails.commons.GrailsApplication

class BootStrap {
	
	def fixtureLoader

    def init = { servletContext ->
		
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT){
			fixtureLoader.load("userinfo")
		}
    }
    def destroy = {
    }
}
