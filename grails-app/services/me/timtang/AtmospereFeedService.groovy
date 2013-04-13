package me.timtang

import java.io.IOException;

import me.timtang.UserInfo;

import org.atmosphere.cpr.AtmosphereHandler
import org.atmosphere.cpr.AtmosphereResource
import org.atmosphere.cpr.AtmosphereResourceEvent
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory
import org.atmosphere.cpr.AtmosphereResource.TRANSPORT
import org.atmosphere.cpr.AtmosphereResponse
import org.atmosphere.cpr.BroadcasterLifeCyclePolicy
import org.atmosphere.cpr.MetaBroadcaster
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.atmosphere.cpr.AtmosphereRequest
import org.atmosphere.plugin.redis.RedisBroadcaster

/**
 * Feed service use atmosphere integrate with Redis pub/sub. 
 * 
 * @author tim.tang
 */
class AtmospereFeedService {
	
	static atmosphere = [mapping: '/pubsub/*']
	
	def msgInfoService
	def userRelationService
	def userInfoService

	/**
	 * Handle get/post request.
	 * 
	 * @param event
	 * @throws IOException
	 */
	void onRequest(AtmosphereResource event) throws IOException {
		AtmosphereRequest req = event.request
		def pathInfo = req.pathInfo
		if (req.method.equalsIgnoreCase("GET")) {
			//setup personal broadcaster channel
				UserInfo userInfo = userInfoService.fetchUserInfoByName(decodePath(pathInfo))
				def RedisBroadcaster redisBroadcaster = BroadcasterFactory.default.lookup(userInfo.id, true);
				println "======================="
				println "Suscribed broadcaster ID ==>" + redisBroadcaster.getID()
				println "======================="
				redisBroadcaster.setBroadcasterLifeCyclePolicy(BroadcasterLifeCyclePolicy.EMPTY)
				event.setBroadcaster(redisBroadcaster)
				event.suspend()
		} else if (req.method.equalsIgnoreCase("POST")) {
			//TODO: need to process JSON message in future
			String message = req.reader.readLine().trim()
			broadcastToAllFriends(pathInfo, message)
		}
	}

	/**
	 * Handle response.
	 * 
	 * @param event
	 * @throws IOException
	 */
	void onStateChange(AtmosphereResourceEvent event) throws IOException {
		AtmosphereResource resource = event.resource
		AtmosphereResponse response = resource.response
		try{
			if (event.isSuspended()) {
				response.writer.write(event.message)
				println "~~~~~~~~~~~~~~~~~~~~~~~~"
				println event.message
				println "~~~~~~~~~~~~~~~~~~~~~~~~"
				switch (resource.transport()) {
					case TRANSPORT.JSONP:
					case TRANSPORT.LONG_POLLING:
						event.resource.resume()
						break
					default:
						response.writer.flush()
				}
			}
		}catch(Exception e){
			println "ERROR in onStateChange. UUID: ${resource.uuid()} ;  $e"
			println e.getStackTrace()
		}
	}
	
	/**
	 * Broadcast to all friends.
	 * 
	 * @param pathInfo
	 * @param message
	 */
	def broadcastToAllFriends(String pathInfo, String message){
		UserInfo userInfo = userInfoService.fetchUserInfoByName(decodePath(pathInfo))
		// persist message into database
		msgInfoService.saveMsgInfo(userInfo.id, message)
		// broadcast to publisher
		BroadcasterFactory.default.lookup(userInfo.id, true).broadcast(message);
		def friends = userRelationService.fetchAllFriends(userInfo.id)
		 for(friend in friends){
			 println "Friend name ==>" + friend.user.userName
			 // broadcast to friends
			 def redisBroadcaster = BroadcasterFactory.default.lookup(friend.user.id, true);
			 redisBroadcaster.broadcast(message)
			 println "Broadcast to friends => "+redisBroadcaster.getID() + " with data ==> "+ message
		 }
	}
	
	/**
	 * Decode request path to get user name.
	 * 
	 * @param pathInfo
	 * @return user name
	 */
	def String decodePath(String pathInfo){
		String[] decodedPath = pathInfo.split("/");
		decodedPath[decodedPath.length - 1]
	}
}
