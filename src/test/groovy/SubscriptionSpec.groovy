package subscription

import domainLinksharing.Subscription
import enumeration.Seriousness
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.Topic
import domainLinksharing.User

@TestFor(Subscription)
@Mock([User, Topic])
class SubscriptionSpec extends Specification {

    @Unroll
    void "Testing Constraints on Subscription"() {
        setup:
        Subscription subscription = new Subscription(topic: topic, user: user, seriousness: seriousness)

        when:
        Boolean result = subscription.validate()

        then:
        result == valid

        where:
        sno | topic       | user       | seriousness              | valid
        0   | new Topic() | new User() | Seriousness.CASUAL       | true
        1   | new Topic() | new User() | Seriousness.SERIOUS      | true
        2   | new Topic() | new User() | Seriousness.VERY_SERIOUS | true
        3   | null        | new User() | Seriousness.VERY_SERIOUS | false
        4   | new Topic() | null       | Seriousness.SERIOUS      | false
        5   | new Topic() | new User() | null                     | false


    }

    def "validating duplicate subscription"() {
        given:
        User user = new User()
        Topic topic = new Topic()
        Subscription sub = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERYSERIOUS)
        Subscription sub1 = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERYSERIOUS)

        when:
        sub.save(flush: true)
        sub1.save(flush: true)

        then:
        !sub.errors.allErrors.size()
        sub1.errors.allErrors.size()
        sub1.errors.getFieldError('user')
    }
}