//package user
//
//import co.SearchCO
//import co.UpdatePasswordCO
//import grails.test.GrailsMock
//import grails.test.mixin.Mock
//import grails.test.mixin.TestFor
//import spock.lang.Specification
//import spock.lang.Unroll
//import Topic
//
///**
// * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
// */
//@TestFor(UserController)
//@Mock([User, Subscription, Topic, ReadingItem, ApplicationFilters])
//class UserControllerSpec extends Specification {
//
//    void "test index method if user exists in session "() {
//        given:
//        User user = new User(email: "himanshigupta@gmail.com", username: "himanshigupta", password: "dummypassword",
//                firstName: "Himanshi", lastName: "Gupta", admin: false, active: true, photo: "12345".getBytes(),
//                confirmPassword: "dummypassword")
//        user.save(flush: true)
//        session["user"] = user
//        GrailsMock userMock = mockFor(User)
//        userMock.demand.getSubscribedTopics() {
//            return [new Topic(name: "1").save(validate: false),
//                    new Topic(name: "2").save(validate: false),
//                    new Topic(name: "3").save(validate: false)] as Set
//        }
//
//        when:
//        controller.index()
//
//        then:
//        view == "/user/dashboard"
//    }
//
//
//    @Unroll
//    void "test updatePassword action when old password doesnt match with entered password"() {
//        given:
//        User user = new User(email: "himanshi@gmail.com", username: "himanshigupta", password: "dummypassword",
//                firstName: "Himanshi", lastName: "Gupta", admin: false, active: true, photo: "12345".getBytes(),
//                confirmPassword: "dummypassword")
//        user.save()
//        UpdatePasswordCO co = new UpdatePasswordCO(oldPassword: "12345", password: "abcde", confirmPassword: "abcde", id: user.id)
//
//        when:
//        controller.updatePassword(co)
//        then:
//        flash.error == "Old Password do not match!"
//        response.redirectedUrl == "/user/showEditProfile"
//    }
//
//    @Unroll
//    void "when old password matched with enetered password and validation succeeds"() {
//        given:
//        User user = new User(email: "himanshi.gupta@gmail.com", username: "himanshigupta", password: "dummypassword",
//                firstName: "Himanshi", lastName: "gupta", admin: false, active: true, photo: "12345".getBytes(),
//                confirmPassword: "dummypassword")
//        user.save()
//        UpdatePasswordCO co = new UpdatePasswordCO(oldPassword: "dummypassword", password: "abcde", confirmPassword: "abcde", id: user.id)
//
//        when:
//        controller.updatePassword(co)
//        then:
//        !co.hasErrors()
//        user.equals(session.user)
//        flash.message == "Password changed successfully"
//        response.redirectedUrl == "/user/showEditProfile"
//
//    }
//
//    @Unroll
//    void "when entered new password is not valid"() {
//        given:
//        User user = new User(email: "himanshi@gmail.com", username: "himanshigupta", password: "dummypassword",
//                firstName: "Himanshi", lastName: "gupta", admin: false, active: true, photo: "12345".getBytes(),
//                confirmPassword: "dummypassword")
//        user.save()
//        UpdatePasswordCO co = new UpdatePasswordCO(oldPassword: "dummypassword", password: "1", confirmPassword: "abcde", id: user.id)
//
//        when:
//        controller.updatePassword(co)
//        then:
//        co.hasErrors()
//        flash.error == "Errors in form"
//        response.redirectedUrl == "/user/showEditProfile"
//
//    }
//
//    @Unroll
//    void "testing show edit profile"() {
//        when:
//        controller.showEditProfile()
//        then:
//        model.user == session.user
//        view == "/user/edit"
//    }
//
//    @Unroll
//    void "test filter"() {
//        when:
//        withFilters(action: "toggleActive") {
//            controller.toggleActive()
//        }
//
//        then:
//        response.redirectedUrl == '/'
//    }
//
//    @Unroll
//    void "test user index check filter"() {
//        given:
//        SearchCO co = new SearchCO()
//        when:
//        withFilters(action: "index") {
//            controller.index(co)
//        }
//
//        then:
//        response.redirectedUrl == "/"
//    }
//
//    @Unroll
//    void "test user showEditProfile filter"() {
//        when:
//        withFilters(action: "showEditProfile") {
//            controller.showEditProfile()
//        }
//
//        then:
//        response.redirectedUrl == "/"
//    }
//
//    @Unroll
//    void "test user updatePassword filter"() {
//        given:
//        UpdatePasswordCO co = new UpdatePasswordCO()
//        when:
//        withFilters(action: "updatePassword") {
//            controller.updatePassword(co)
//        }
//
//        then:
//        response.redirectedUrl == "/"
//    }
//
//}
