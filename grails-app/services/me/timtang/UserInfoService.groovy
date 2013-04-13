package me.timtang

import me.timtang.MsgInfo;
import me.timtang.UserInfo;

import org.springframework.transaction.annotation.Transactional


/**
 * Service for user information. 
 * 
 * @author tim.tang
 *
 */
class UserInfoService {

    def userRelationService

    /**
     * Save user information.
     * 
     * @param userName
     * @return UserInfo
     */
    @Transactional
    def saveUserInfo(String userName) {
       def userInfo = new UserInfo(userName: userName)
       userInfo.save()
    }
	
	/**
	 * Fetch user information by user name.
	 * 
	 * @param userName
	 * @return UserInfo
	 */
	@Transactional(readOnly=true)
	def UserInfo fetchUserInfoByName(String userName){
		def userInfo = UserInfo.findByUserName(userName)
		userInfo
	}

    /**
     * Fetch user time line.
     * 
     * @param userId
     * @return List<MsgInfo>
     */
    @Transactional(readOnly=true)
    def fetchTimeLine(long userId){
        MsgInfo.executeQuery("SELECT msg FROM MsgInfo msg, UserRelation relation WHERE relation.follower.id = ? AND relation.user = msg.user", [userId])
    }
}
