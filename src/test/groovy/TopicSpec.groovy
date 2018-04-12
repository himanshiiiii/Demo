

import enumeration.Visibility
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.Subscription
import domainLinksharing.Topic
import domainLinksharing.User

@TestFor(Topic)
@Mock([User, Subscription])
class TopicSpec extends Specification {

    @Unroll
    void "Test Topic Constraints"() {
        setup:
        Topic topic = new Topic(name: name, createdBy: createdBy, visibility: visibility)

        when:
        Boolean result = topic.validate()

        then:

        result == valid

        where:

        sno | name    | createdBy  | visibility         | valid
        0   | "Topic" | new User() | Visibility.PRIVATE | true
        1   | null    | new User() | Visibility.PRIVATE | false
        2   | ""      | new User() | Visibility.PRIVATE | false
        3   | "Topic" | null       | Visibility.PRIVATE | false
        4   | "Topic" | new User() | Visibility.PUBLIC  | true
        5   | "Topic" | new User() | null               | false


    }

    @Unroll
    void "Topic name should be unique per user"() {
        setup:
        User user = new User(email: "abc@gmail.com", username: "nitin", password: "password", firstName: "nitin", lastName: "singh", admin: true, active: true, photo: "photo".bytes)
        Topic topic1 = new Topic(name: "Topic1", createdBy: user, visibility: Visibility.PUBLIC)
        Topic topic2 = new Topic(name: "Topic1", createdBy: user, visibility: Visibility.PUBLIC)

        when:
        user.save(flush: true)
        topic1.save(flush: true)
        topic2.save(flush: true)

        then:
        !topic1.errors.allErrors.size()
        topic2.errors.allErrors.size()
        topic2.errors.getFieldError('name')


    }

    @Unroll
    def "Testing toString() of Topic"() {
        given:
        Topic topic = new Topic(name: "Topic1", createdBy: new User(), visibility: Visibility.PUBLIC)

        when:
        String result = "${topic}"

        then:
        result == "Topic1"
    }

    @Unroll
    def "testing isPublic method"() {
        given:
        User user = new User(email: "user@gmail.com", username: "dummyuser", password: "dummypassword", firstName: "FIRSTNAME",
                lastName: "LASTNAME", admin: true, active: true, photo: "ABCD".getBytes())
        user.save()
        Topic topic = new Topic(name: "Dummy topic", visibility: Visibility.PUBLIC, createdBy: user)
        when:
        Boolean output = topic.isPublic()
        then:
        output == true
    }

}