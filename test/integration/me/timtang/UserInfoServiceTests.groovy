package me.timtang

import static org.junit.Assert.*

import org.junit.*

class UserInfoServiceTests extends GroovyTestCase {

    def userInfoService
    def userRelationService
    def msgInfoService 
    def user
    def follower
    def msgInfo

    @Before
    void setUp() {
        user = userInfoService.saveUserInfo("tim.tang") 
        follower = userInfoService.saveUserInfo("tim-follower") 
        msgInfo = msgInfoService.saveMsgInfo(user.id, "123")
     }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSaveUserInfo() {
        assert userInfoService.saveUserInfo("tim.tang")
     }

    @Test
    void testFetchTimeLine(){
        userRelationService.saveUserRelation(user.id, follower.id)
        def result = userInfoService.fetchTimeLine(follower.id)
        assert result[0].content == "123"
    }
}
