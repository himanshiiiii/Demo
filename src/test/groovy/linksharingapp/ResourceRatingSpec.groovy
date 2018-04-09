package linksharingapp

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ResourceRatingSpec extends Specification implements DomainUnitTest<ResourceRating> {

    def setup() {
    }

    def cleanup() {
    }

    void "testing"(){
        setup:
        User user2 = new User(firstName: "neha",lastName: "gupta",email: "neha@ttn.com",userName: "neha123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic2= new Topic(name: "mytopic2",createdBy: user2,visibility: Visibility.PRIVATE)
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1= new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)
        Resource resource = new LinkResource(createdBy: user1, description: "njkasjks",topic:topic1 , url: "https://www.google.com")

        when:
        ResourceRating resourceRating = new ResourceRating(resource: resource,user: user1,score: 3)
        boolean  b =resourceRating.validate()

        then:
        b == true

        when:
        ResourceRating resourceRating2 = new ResourceRating(resource: resource,user: user1,score: 4)
        b = resourceRating2.validate()

        then:
        b==false

    }
}
