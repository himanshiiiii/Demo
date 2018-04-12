package domainLinksharing

import enumeration.Seriousness

class Subscription {
    User user
    Topic topic
    Seriousness seriousness
    Date lastUpdated
    Date dateCreated

    static belongsTo = [user:User, topic:Topic]

    static constraints = {
        user(nullable: false)
        topic(nullable: false)
        seriousness(nullable: false,default: Seriousness.SERIOUS)
    }

    static mapping = {
        topic fetch:'join'
        user fetch: 'join'
    }


}

