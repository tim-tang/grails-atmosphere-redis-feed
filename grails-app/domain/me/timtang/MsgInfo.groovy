package me.timtang


class MsgInfo {
	
    UserInfo user
    String content

    static constraints = {
        content blank: false
    }
	
}
