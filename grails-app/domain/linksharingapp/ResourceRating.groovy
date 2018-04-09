package linksharingapp

class ResourceRating {

    Resource resource
    User user
    Integer score
    Date dateCreated
    Date lastUpdated

    static belongsTo = [resource:Resource]
    static constraints = {
        resource(nullable: false,unique: 'user')
        user(nullable: false)
        score(nullable: false,min:3 , max:5)
    }
}
