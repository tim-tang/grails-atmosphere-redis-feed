import me.timtang.*

fixture {
		userInfo1(UserInfo, userName:"tim")
		userInfo2(UserInfo, userName:"andy")
		userRelation1(UserRelation, user:userInfo1, follower:userInfo2)
		userRelation2(UserRelation, user:userInfo2, follower:userInfo1)
}
