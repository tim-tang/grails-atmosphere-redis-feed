package me.timtang

import static org.junit.Assert.*

import org.junit.*

class UserRelationServiceTests extends GroovyTestCase{

    def userRelationService
    def userInfoService
    def user
    def follower

    @Before
    void setUp() {
        user = userInfoService.saveUserInfo("tim.tang") 
        follower = userInfoService.saveUserInfo("tim-follower") 
    }

    @Test
    void testSaveUserRelation() {
        assert userRelationService.saveUserRelation(user.id, follower.id)
    }

    @Test
    void testFetchAllFriends() {
        userRelationService.saveUserRelation(user.id, follower.id)
        def friends = userRelationService.fetchAllFriends(follower.id)
        for(friend in friends){
           assert friend.user.id == user.id 
        }
    }

    @Test
    void testFetchAllFollowers() {
        userRelationService.saveUserRelation(user.id, follower.id)
        def followers = userRelationService.fetchAllFollowers(user.id)
        for(follower in followers){
           assert follower.user.id == user.id 
        }
    }
}
