package linksharingapp

import grails.testing.gorm.DomainUnitTest
import password.ConstantPassword
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }


    void "validating user with every possible constraints"(){
        setup:
        User user = new User(firstName: fname,lastName: lname,email: email,userName: uname,password: pswd,photo: photo,admin: admin,active: active)
        when:
        Boolean result = user.validate()
        then:
        result == value

        where:
        fname       | lname     | email                         | uname     | pswd      | photo | admin | active | value
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 1     | true  | true   |  true
        //Email constraints
        "himanshi"   |"gupta"     |"himanshi.gupta@to"              |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        "himanshi"   |"gupta"     |  null                         |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        "himanshi"   |"gupta"     |""                             |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        //password constraints
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | null      | 1     | true  | true   |  false
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcd"    | 1     | true  | true   |  false
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | ""        | 1     | true  | true   |  false
        //firstname constraints
        null        |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        ""          |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        //lastname constraints
        "himanshi"   |null       |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        "himanshi"   |""         |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 1     | true  | true   |  false
        //photo constraint
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 0     | true  | true   |  true
        //admin constraint
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 0     | null  | true   |  true
//        active constraint
        "himanshi"   |"gupta"     |"himanshi.gupta@tothenew.com"    |"himanshi123" | "abcdef"  | 0     | true  | null   |  true
    }

    void "validating uniqueness of email"(){
        setup:
        String email = "himanshi.gupta@tothenew.com"

        when:
        User user1 = new User(firstName: "himanshi",lastName: "jain",email: email,userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
        user1.save()

        then:
        user1.count() == 1

        when:
        User user2 = new User(firstName: "neha",lastName: "gupta",email: email,userName: "neha123",password: "12345",photo: 1,admin: true,active: null)
        user2.save()

        then:
        user2.count() == 1
        user2.errors.allErrors.size() == 1
        user2.errors.getFieldErrorCount('email') == 1
    }

    void "validating uniqueness of username"(){
        setup:
        String uname = "himanshi123"

        when:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: uname,password: "abcdef",photo: 1,admin: true,active: null)
        user1.save()

        then:
        user1.count() == 1

        when:
        User user2 = new User(firstName: "neha",lastName: "gupta",email: "neha@ttn.com",userName: uname,password: "12345",photo: 1,admin: true,active: null)
        user2.save()

        then:
        user2.count() == 1
        user2.errors.allErrors.size() == 1
        user2.errors.getFieldErrorCount('userName') == 1
    }
    void "validating getName()"(){
        setup:
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)

        when:
        user1.save()

        then:
        user1.getName() == "himanshi gupta"
    }

    def "validating toString "(){

        setup:
        String email = "himanshi.gupta@tothenew.com"
        String password = ConstantPassword.userPassword
        User user = new User(email: email,userName:"himanshi123",password:password, firstName: fname, lastName: lname,admin:false,active:true)

        when:
        user.save()

        then:
        user.toString() == "User{name='${user.name}'}"

        where:
        fname       | lname
        "himanshi"   |   "gupta"
        ""          |   'gupta'
        null        |   'gupta'
        'himanshi'   |   null

    }

}
