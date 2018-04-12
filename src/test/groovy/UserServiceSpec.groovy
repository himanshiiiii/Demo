package user

import domainLinksharing.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import domainLinksharing.ReadingItem
import domainLinksharing.DocumentResource
import domainLinksharing.Resource
import services.UserService
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User, Resource, ReadingItem])
class UserServiceSpec extends Specification {

    private User user = Mock()
    private Resource resource = Mock()
    private UserService userService = Mock()
    private EmailService emailService = Mock()

    @Unroll
    void "test getUnreadResources method"() {
        given:
        List<Resource> resources = [new DocumentResource()]
        user.unreadResources() >> { return resources }


        expect:
        service.getUnreadResources(user) == resources
    }

    @Unroll
    void "test getUserWithUnreadItems method"() {
        given:
        List<User> users = [new User()]
        Resource.metaClass.static.usersWithUnreadResources = { return users }
        expect:
        service.getUsersWithUnreadItems() == users
    }

    @Unroll
    void "test sendUnreadItemsMail method in case of zero users"() {
        given:
        emailService.sendUnreadResourcesMail(_, _) >> { return true }
        service.emailService = emailService

        when:
        UserService.metaClass.getUsersWithUnreadItems = { return [] }
        service.sendUnreadItemsMail()

        then:
        0 * emailService.sendUnreadResourcesMail(_, _)

        when:
        UserService.metaClass.getUsersWithUnreadItems = { return [new User(), new User()] }
        service.sendUnreadItemsMail()

        then:
        2 * emailService.sendUnreadResourcesMail(_, _)

    }
}


