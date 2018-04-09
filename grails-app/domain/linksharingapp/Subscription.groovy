package linksharingapp

import enumeration.Seriousness

class Subscription {
    Topic topic
    User user
    Date dateCreated
    Seriousness seriousness = Seriousness.SERIOUS
    Date lastUpdated

    static belongsTo = [user:User,topic:Topic]
    Subscription(Topic topic, User user, Seriousness seriousness) {
        this.topic = topic
        this.user = user
        this.seriousness = seriousness
    }
    static constraints = {
        user(nullable: false,unique: 'topic')
        topic(nullable: false)
        seriousness(nullable: false)
    }

    static mapping = {
        user fetch: 'join'
        topic fetch: 'join'
    }

}
