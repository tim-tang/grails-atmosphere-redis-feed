package me.timtang

class UserInfo {
    
    String userName

    static hasMany = [msgInfos: MsgInfo]

    //static mappedBy = [userRelations:'user']

    /*static mapping = {
        table 'people'
        version false
    }*/

    static constraints = {
    }
}
