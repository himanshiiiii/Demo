package resource

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

//@TestFor(ResourceController)
//@Mock([ApplicationFilters])
class ResourceControllerSpec extends Specification {
    @Unroll
    void "testing application filter on Resource delete action"() {
        given:
        Long id = 1
        when:
        withFilters(action: "delete") {
            controller.delete(id)
        }
        then:
        response.redirectedUrl == "/"

    }


}