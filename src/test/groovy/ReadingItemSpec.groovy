package readingItem

import domainLinksharing.DocumentResource
import domainLinksharing.LinkResource
import domainLinksharing.ReadingItem
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.User

@TestFor(ReadingItem)
@Mock([User])
class ReadingItemSpec extends Specification {

    @Unroll
    void "Testing validations on ReadingItem class"() {
        setup:
        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: isRead)

        when:
        Boolean result = readingItem.validate()

        then:
        result == isValid

        where:
        sno | resource               | user       | isRead | isValid
        0   | new LinkResource()     | new User() | true   | true
        1   | new DocumentResource() | new User() | true   | true
        2   | new DocumentResource() | null       | true   | false
        3   | new DocumentResource() | new User() | null   | false
        4   | new DocumentResource() | new User() | false  | true
    }

    @Unroll
    void "validating unique reading item"() {
        given:
        LinkResource resource = new LinkResource()
        User user = new User()
        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
        ReadingItem readingItem1 = new ReadingItem(resource: resource, user: user, isRead: false)

        when:
        readingItem.save(flush: true)
        readingItem1.save(flush: true)

        then:
        !readingItem.errors.allErrors.size()
        readingItem1.errors.allErrors.size()
        readingItem1.errors.getFieldError('resource')

    }

    @Unroll
    void "testing toString of reading Item"() {
        given:
        User user = new User(firstName: "ABC", lastName: "ABC", email: "abc@gmail.com")
        user.save()
        ReadingItem readingItem = new ReadingItem(user: user, isRead: false, resource: new LinkResource())
        when:
        def output = "${readingItem}"
        then:
        output == "User:ABC,Read:false"
    }

}