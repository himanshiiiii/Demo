package resource

import domainLinksharing.LinkResource
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.Topic
import domainLinksharing.User

@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    @Unroll
    void "Test All LinkResource Validations"() {
        setup:
        LinkResource linkResource = new LinkResource(url: url, createdBy: createdBy, description: description, topic: topic)
        when:
        Boolean result = linkResource.validate()

        then:
        result == isValid

        where:
        sno | url                                        | createdBy  | description   | topic       | isValid
        0   | "http://www.example.com/space%20here.html" | new User() | "description" | new Topic() | true
        1   | ""                                         | new User() | "description" | new Topic() | false
        2   | null                                       | new User() | "description" | new Topic() | false
        3   | "http://www.example.com/space%20here.html" | null       | "description" | new Topic() | false
        4   | "http://www.example.com/space%20here.html" | new User() | ""            | new Topic() | false
        5   | "http://www.example.com/space%20here.html" | new User() | null          | new Topic() | false
        6   | "http://www.example.com/space%20here.html" | new User() | "description" | null        | false

    }

    @Unroll
    def "testing toString() of Link Resource"() {
        given:
        LinkResource linkResource = new LinkResource(url: "http://www.example.com/space%20here.html", createdBy: new User(), description: "Dummy", topic: new Topic())

        when:
        String result = "${linkResource}"

        then:
        result == "Link Resource url: http://www.example.com/space%20here.html"


    }

}