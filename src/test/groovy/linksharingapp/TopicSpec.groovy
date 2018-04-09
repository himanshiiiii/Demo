package linksharingapp

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import password.ConstantPassword
import spock.lang.Specification
import linksharingapp.User

class TopicSpec extends Specification implements DomainUnitTest<Topic> {

    def setup() {
    }

    def cleanup() {
    }



    void "Testing all constraints"(){

        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        Topic topic1 = new Topic(name: name1,createdBy: user1,visibility: visibility)
        when:
        boolean result = topic1.validate()

        then:
        result == value

        where:
        name1       | visibility        |value

        "mytopic1"  | Visibility.PUBLIC |true

        //name constraints
        null        |Visibility.PUBLIC  |false
        ""          |Visibility.PUBLIC  |false

        //visibility constraints
        "mytopic1"  | null              |false
    }

    void "Unique Topic per user Validation"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        when:
        Topic topic1 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PUBLIC)
        topic1.save()
        then:
        topic1.count() == 1

        when:
        Topic topic2 = new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)
        topic2.save()
        then:
        topic2.count() == 1
        topic2.errors.allErrors.size() == 1
        topic2.errors.getFieldErrorCount('name') == 1

    }

    def "validating toString "(){

        setup:
        String email = "himanshi.gupta@tothenew.com"
        String password = ConstantPassword.userPassword
        User user = new User(email: email,userName:"himanshi123",password:password, firstName: "himanshi", lastName: "gupta",admin:false,active:true)
        Topic topic = new Topic('name': "my1",'createdBy': user,'visibility': Visibility.PRIVATE)

        when:
        topic.save()

        then:
        topic.toString() == "Topic{name='${topic.name}'}"
    }



}
