package linksharingapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TopicControllerSpec extends Specification implements ControllerUnitTest<TopicController> {

    def setup() {
    }

    def cleanup() {
    }

    //java.lang.IllegalStateException: Either class [linksharingapp.Topic] is not a
    // domain class or GORM has not been initialized correctly or has already been shutdown.
    // Ensure GORM is loaded and configured correctly before calling any methods on a
    // GORM entity.

    /*void "test case for delete"(){
        when:
        controller.delete(1L)

        then:
        response.text == "Topic Deleted Successfully"
    }*/
}
