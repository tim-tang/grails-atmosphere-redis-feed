package me.timtang

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRelation implements Serializable{

    UserInfo user
    UserInfo follower

    boolean equals(other) {
       if (!(other instanceof UserRelation)) {
                return false
        }
        other.user.id == user.id && other.follower.id == follower.id
    }

    int hashCode() {
        def builder = new HashCodeBuilder()
            builder.append user.id
            builder.append follower.id
            builder.toHashCode()
    }

    static mapping = {
        id composite: ['user', 'follower']
    }

    static constraints = {
    }
}
