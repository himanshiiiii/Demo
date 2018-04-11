package resource

import com.sun.deploy.association.utility.AppConstants
import domainLinksharing.LinkResource
import domainLinksharing.Resource
import enumeration.Visibility
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.Topic
import domainLinksharing.User

//@TestFor(LinkResourceController)
//@Mock([ApplicationFilters, LinkResource, Resource, User])
class LinkResourceControllerSpec extends Specification {

    @Unroll
    void "testing application filter on Resource delete action"() {
        given:
        LinkResource linkResource = new LinkResource()
        when:
        withFilters(action: "save") {
            controller.save(linkResource)
        }
        then:
        response.redirectedUrl == "/"

    }

    @Unroll
    void "testing update"() {
        given:
        LinkResource linkResource = new LinkResource(description: "1223")
        linkResource.save()
        Long id = 1
        String description = "1223"
        when:
        controller.update(id, description)

        then:
        response.redirectedUrl == "/resource/show"
        flash.error == "Failed to update  Resource"

    }


    def "check save link resource failure"() {

        given:
        User user = new User(id: 1)
        Topic topic = new Topic(id: 1, createdBy: user)
        Resource linkresource = new LinkResource(description: "link resource",
                createdBy: user, topic: topic, url: "https://www.googlealkfdg/")

        when:
        controller.save(linkresource)

        then:
        flash.error == "Error saving resource"
        response.redirectedUrl == "/"

    }

    @Unroll
    def "check link resource success"() {

        given:
        User user = new User(email: "himanshi.gupta@tothenew.com", username: "himanshi", password: AppConstants.PASSWORD,
                firstName: "himanshi", lastName: "gupta", admin: true, active: true, confirmpassword: AppConstants.PASSWORD)
        user.save(flush: true)
        session["user"] = user

        and:
        Topic topic = new Topic(name: "topic", visibility: Visibility.PUBLIC, createdBy: user)
        topic.save(flush: true)

        and:
        Resource linkresource = new LinkResource(description: "link resource",
                createdBy: user, topic: topic, url: "https://www.google.com")

        when:
        controller.save(linkresource)

        then:
        flash.message == "Resource saved Successfully"
        response.redirectedUrl == "/"

    }

}