package domainLinksharing

class ReadingItem {
    User user
    Boolean isRead
    Resource resource
    Date lastUpdated
    Date dateCreated

    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        user(nullable: false)
        isRead(nullable: false)
        resource(nullable: false, unique: 'user')
    }

}
