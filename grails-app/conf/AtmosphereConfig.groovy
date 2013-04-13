atmospherePlugin {
    servlet {
    	// Servlet initialization parameters
    	// Example: initParams = ['org.atmosphere.useNative': 'true', 'org.atmosphere.useStream': 'false']
    	initParams = ['org.atmosphere.useNative': 'true', 'org.atmosphere.cpr.cometSupport': 'org.atmosphere.container.Tomcat7CometSupport', 'org.atmosphere.cpr.broadcasterClassName':'org.atmosphere.plugin.redis.RedisBroadcaster','org.atmosphere.plugin.redis.RedisBroadcaster.server':'http://localhost:6379']
    	urlPattern = '/pubsub/*'
    }
    handlers {
    	// This closure is used to generate the atmosphere.xml using a MarkupBuilder instance in META-INF folder
    	atmosphereDotXml = {
        	//'atmosphere-handler'('context-root': '/pubsub/*', 'class-name': 'me.timtang.FeedAtmosphereHandler')
    	}
    }
}