package linksharingapp

import enumeration.Seriousness
import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class SubscriptionSpec extends Specification implements DomainUnitTest<Subscription> {

    def setup() {
    }

    def cleanup() {
    }

    void "validating with true cases"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)

        when:
        Subscription subscription1 = new Subscription(topic: topic1,user: user1,seriousness: Seriousness.CASUAL )
        boolean result = subscription1.validate()

        then:
        result == true
    }

    void "validating user not null"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)

        when:
        Subscription subscription1 = new Subscription(topic: topic1,user: null,seriousness: Seriousness.CASUAL )
        boolean result = subscription1.validate()

        then:
        result == false
    }

    void "validating topic not null"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)

        when:
        Subscription subscription1 = new Subscription(topic: null,user: null,seriousness: Seriousness.CASUAL )
        boolean result = subscription1.validate()

        then:
        result == false
    }

    void "validating seriousness not null"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)

        when:
        Subscription subscription1 = new Subscription(topic: topic1,user: user1,seriousness: null )
        boolean result = subscription1.validate()

        then:
        result == false
    }

    void "validating uniqueness of topic in user"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)

        when:
        Subscription subscription1 = new Subscription(topic: topic1,user: user1,seriousness: Seriousness.CASUAL )
        subscription1.save()

        then:
        subscription1.count() == 1

        when:
        Subscription subscription2 = new Subscription(topic: topic1,user: user1,seriousness: Seriousness.CASUAL )
        subscription2.save()

        then:
        subscription2.count() == 1
        subscription2.errors.allErrors.size() == 1
        subscription2.errors.getFieldErrorCount('user')==1
    }
}
