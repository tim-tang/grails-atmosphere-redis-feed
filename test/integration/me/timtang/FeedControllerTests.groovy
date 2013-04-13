package me.timtang

import static org.junit.Assert.*
import me.timtang.FeedController;

import org.junit.*

class FeedControllerTests extends GroovyTestCase {
    def userInfoService
    def msgInfoService

    @Before
    void setUp() {
        def userInfo = userInfoService.saveUserInfo("tim.tang")
        msgInfoService.saveMsgInfo(userInfo.id, "123")
        /*
        def userInfo = new UserInfo(name: "tim.tang")
        userInfo.save()
        def msgInfo = new MsgInfo(user: userInfo, content: "123")
        msgInfo.save()
        */
    }

    @Test
    void testIndex() {
        def feedController = new FeedController()
        feedController.index()
        assert '{"msgInfos":[{"content":"123"}]}' == feedController.response.text
        assert "123" == feedController.response.json.msgInfos[0].content
    }
}
