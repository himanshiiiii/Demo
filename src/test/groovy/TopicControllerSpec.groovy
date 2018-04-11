package topic
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
//@TestFor(TopicController)
//@Mock([Topic, User, Subscription, Resource])
//class TopicControllerSpec extends Specification {
//
//    @Unroll
//    void "testing show method When topic does not exist"() {
//        given:
//        ResourceSearchCO co = new ResourceSearchCO(topicId: 1000)
//
//        when:
//        controller.show(co)
//
//        then:
//        flash.error == "Error: Topic does not exist"
//        response.redirectedUrl == "/"
//    }
//
//    @Unroll
//    def "check if topic exists and visibility is private and not subscribed"() {
//        given:
//        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: new User())
//        topic.save(flush: true)
//        ResourceSearchCO co = new ResourceSearchCO(topicId: 1)
//
//        when:
//        controller.show(co)
//
//        then:
//        flash.error == "Error: Subscription not found"
//        response.redirectedUrl == "/"
//    }
//
//
//    @Unroll
//    def "check if topic exists and visibility is private and subscribed"() {
//        given:
//        User user = new User()
//        session["user"] = user
//        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: user)
//        topic.save(flush: true)
//        ResourceSearchCO co = new ResourceSearchCO(topicId: 1)
//
//        when:
//        controller.show(co)
//
//        then:
//        view == "/topic/topicShow"
//    }
//
//    @Unroll
//    void "test whether topic is being saved or not"() {
//        given:
//        session.user = new User()
//
//        when:
//        controller.topicSave("MyTopic", "PUBLIC")
//
//        then:
//        flash.message == "MyTopic saved Successfully"
//
//
//    }
//
//    @Unroll
//    void "test topicSave does not save topic when save fails"() {
//        given:
//        session.user = new User()
//
//        when:
//        controller.topicSave("" as String, "PUBLIC")
//
//        then:
//        flash.error == "Error saving null Not saved"
//    }
//}