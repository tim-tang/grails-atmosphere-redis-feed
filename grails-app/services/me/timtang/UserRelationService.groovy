package me.timtang
import me.timtang.UserInfo;
import me.timtang.UserRelation;

import org.springframework.transaction.annotation.Transactional


/**
 * Service for user relation operations.
 * 
 * @author tim.tang
 *
 */
class UserRelationService {

    /**
     * Save user relationship.
     * 
     * @param userId
     * @param followerId
     * @return
     */
    @Transactional
    def saveUserRelation(long userId, long followerId){
        def userRelation = new UserRelation(user: UserInfo.load(userId),follower: UserInfo.load(followerId))
        userRelation.save()
    }

    /**
     * Fetch all friends.
     * 
     * @param userId
     * @return List<UserRelation>
     */
    @Transactional(readOnly=true)
    def fetchAllFriends(long userId) {
        userId ? UserRelation.findByFollower(UserInfo.load(userId)):[]
    }

    /**
     * Fetch all followers
     * 
     * @param userId
     * @return List<UserRelation>
     */
    @Transactional(readOnly=true)
    def fetchAllFollowers(long userId){
        userId ? UserRelation.findByUser(UserInfo.load(userId)):[]
    }
}
