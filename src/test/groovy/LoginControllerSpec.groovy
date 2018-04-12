package linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import domainLinksharing.User

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([User])
class LoginControllerSpec extends Specification {

    def "check when user sessions exists in index method"() {
        given: "a session user exists"
        session["user"] = new User()

        when:
        controller.index()

        then:
        response.forwardedUrl == '/user/index'
    }

    def "check if user session exists in index metho"() {
        given: "a session user exists"
        session["user"] = null

        when:
        controller.index()

        then:
        response.contentAsString == "Failure: User Not Found."
    }

    def "if user is logged out"() {
        when:
        controller.logout()
        then:
        !session.user
        response.forwardedUrl == '/login/index'
    }

    def "check if user doesn't exist"() {
        given:
        String username = "default"
        String password = "default"

        when:
        controller.loginhandler(username, password)

        then:
        response.contentAsString == "User not found"
    }

    def "check if user exists but not active"() {
        given:
        User user = new User(email: "nitin@gmail.com", username: "nitinrajput", password: "dummypassword",
                firstName: "Nitin", lastName: "Singh", admin: false, active: false, photo: "12345".getBytes(),
                confirmPassword: "dummypassword")
        user.save(flush: true)

        when:
        controller.loginhandler(user.username, user.password)

        then:
        response.contentAsString == "Your account is not Active"
    }

    def "check if user exists and is active"() {
        given:
        User user = new User(email: "nitin@gmail.com", username: "nitinrajput", password: "dummypassword",
                firstName: "Nitin", lastName: "Singh", admin: false, active: true, photo: "12345".getBytes(),
                confirmPassword: "dummypassword")
        user.save(flush: true)

        when:
        controller.loginhandler(user.username, user.password)

        then:
        response.redirectedUrl == "/user/index"
    }


}