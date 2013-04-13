package me.timtang

import static org.junit.Assert.*

import org.junit.*

class MsgInfoServiceTests extends GroovyTestCase{
	
	def msgInfoService
	def userInfoService
	def feedPubSubService
	def user 
	
	@Before
	void setUp() {
		user = userInfoService.saveUserInfo("tim.tang")
	}
	
	@Test
	void testSaveMsgInfo(){
		assert msgInfoService.saveMsgInfo(user.id, "testing")
	}

}
