package me.timtang

import me.timtang.MsgInfo;

import org.springframework.transaction.annotation.Transactional


/**
 * Service for message information.
 * 
 * @author tim.tang
 *
 */
class MsgInfoService {
	
    @Transactional
    def MsgInfo saveMsgInfo(long userId, String content) {
       def msgInfo = new MsgInfo(user: UserInfo.load(userId), content: content) 
       msgInfo.save()
    }
}
