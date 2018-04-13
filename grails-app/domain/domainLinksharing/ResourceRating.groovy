package domainLinksharing

class ResourceRating {
    Resource resource
    User createdBy
    Integer score
    Date lastUpdated
    Date dateCreated

    static belongsTo = [resource: Resource,createdBy:User]

    static constraints = {
        resource(nullable: false)
        score(nullable: false, min: 0, max: 6)
        createdBy(nullable: false)
    }
}

