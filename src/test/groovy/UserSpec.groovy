package user

import domainLinksharing.User
import grails.test.mixin.TestFor
import org.apache.commons.logging.impl.Log4JLogger
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(User)
class UserSpec extends Specification {

    private Log4JLogger logger = Mock(Log4JLogger)

    @Unroll
    def "Testing unique email id"() {
        setup:
        User user = new User(email: "test@test.com", username: "dummy", password: "dummy", firstName: "dummy", lastName: "dummy", admin: true, active: true, photo: "abcd".bytes, confirmPassword: "dummy")
        User user1 = new User(email: "test@test.com", username: "pushkar", password: "pushkar", firstName: "pushkar", lastName: "singh", admin: true, active: true, photo: "abcd".bytes, confirmPassword: "pushkar")

        when:
        user.save(flush: true)
        user1.save(flush: true)

        then:
        !user.errors.allErrors.size()
        user1.errors.allErrors.size()
        user1.errors.getFieldError('email')

    }

    @Unroll
    def "Testing unique username"() {
        setup:
        User user = new User(email: "test@test.com", username: "pushkar", password: "dummy", firstName: "dummy", lastName: "dummy", admin: true, active: true, photo: "abcd".bytes, confirmPassword: "dummy")
        User user1 = new User(email: "test1@test.com", username: "pushkar", password: "pushkar", firstName: "pushkar", lastName: "singh", admin: true, active: true, photo: "abcd".bytes, confirmPassword: "pushkar")

        when:
        user.save(flush: true)
        user1.save(flush: true)

        then:
        !user.errors.allErrors.size()
        user1.errors.allErrors.size()
        user1.errors.getFieldError('username')

    }


    @Unroll
    def "Testing user constraints"() {
        setup:
        User user = new User(email: email, username: username, password: password, firstName: firstName, lastName: lastName, admin: admin, active: active, photo: photo,
                confirmPassword: confirmPassword)

        when:
        Boolean result = user.validate()

        then:
        result == valid

        where:
        sno | email           | username   | password   | confirmPassword | firstName   | lastName   | admin | active | photo         | valid
        0   | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | true
        1   | "test"          | "username" | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        2   | ""              | "username" | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        3   | null            | "username" | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        4   | "test@test.com" | ""         | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        5   | "test@test.com" | null       | "password" | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        6   | "test@test.com" | "username" | ""         | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        7   | "test@test.com" | "username" | "123"      | "password"      | "firstName" | "lastName" | true  | true   | "photo".bytes | false
        8   | "test@test.com" | "username" | "password" | "password"      | ""          | "lastName" | true  | true   | "photo".bytes | false
        9   | "test@test.com" | "username" | "password" | "password"      | null        | "lastName" | true  | true   | "photo".bytes | false
        10  | "test@test.com" | "username" | "password" | "password"      | "firstName" | ""         | true  | true   | "photo".bytes | false
        11  | "test@test.com" | "username" | "password" | "password"      | "firstName" | null       | true  | true   | "photo".bytes | false
        12  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | null  | true   | "photo".bytes | true
        13  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | false | true   | "photo".bytes | true
        14  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | ""    | true   | "photo".bytes | true
        15  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | true  | null   | "photo".bytes | true
        16  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | true  | false  | "photo".bytes | true
        17  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | true  | ""     | "photo".bytes | true
        18  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | true  | true   | null          | true
        19  | "test@test.com" | "username" | "password" | "password"      | "firstName" | "lastname" | true  | true   | "photo".bytes | true
        20  | "test@test.com" | "username" | "password" | "password123"   | "firstName" | "lastname" | true  | true   | "photo".bytes | false
        21  | "test@test.com" | "username" | "password" | ""              | "firstName" | "lastname" | true  | true   | "photo".bytes | false


    }

    @Unroll
    def "testing toString() of User"() {
        given:
        User user = new User(email: "user@gmail.com", username: "dummyuser", password: "dummypassword", firstName: "FIRSTNAME",
                lastName: "LASTNAME", admin: true, active: true, photo: "ABCD".getBytes())
        user.save()

        when:
        String result = "${user}"

        then:
        result == "Username: dummyuser"

    }

    @Unroll
    def "testing saveInstance method when atleast one constraint doesnt satisfy"() {
        given:
        User user = new User(email: "user@gmail.com", username: "dummyuser", password: "dummypassword", firstName: "FIRSTNAME",
                lastName: "LASTNAME", admin: true, active: true, photo: "ABCD".getBytes())
        user.save()
        user.password = "1"

        when:

        Boolean output = user.saveInstance()

        then:
        output == null

    }

    @Unroll
    def "testing saveInstance method when all constraints satisfy"() {

        given:
        User user = new User(email: "user@gmail.com", username: "dummyuser", password: "dummypassword", firstName: "FIRSTNAME",
                lastName: "LASTNAME", admin: true, active: true, photo: "ABCD".getBytes())
        user.save()
        user.password = "112343443"
        user.confirmPassword = "112343443"
        user.log = logger

        logger.info(_) >> { true }

        when:
        user.saveInstance()

        then:

        1 * logger.info(_)
    }


}