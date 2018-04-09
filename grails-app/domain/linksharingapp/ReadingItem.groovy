package linksharingapp

class ReadingItem {

    Resource resource
    User user
    Boolean isRead
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User,resource:Resource]

    static constraints = {
        isRead(nullable: false)
        user(nullable: false)
        resource(unique: 'user')
    }

    static mapping = {
        resource fetch: 'join'
    }
}
