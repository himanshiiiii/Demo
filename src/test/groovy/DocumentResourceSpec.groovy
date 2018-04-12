package resource

import domainLinksharing.DocumentResource
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.Topic
import domainLinksharing.User

@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    @Unroll
    void "testing constraints of DocumentResource"() {
        setup:
        DocumentResource documentResource = new DocumentResource(filePath: filePath, createdBy: createdBy, description: description, topic: topic)
        when:
        Boolean result = documentResource.validate()

        then:
        result == isValid

        where:
        sno | filePath                                   | createdBy  | description   | topic       | isValid
        0   | "~/Desktop/user.txt"                       | new User() | "description" | new Topic() | true
        0   | "http://www.example.com/space%20here.html" | new User() | "description" | new Topic() | true
        1   | ""                                         | new User() | "description" | new Topic() | false
        2   | null                                       | new User() | "description" | new Topic() | false
        3   | "http://www.example.com/space%20here.html" | null       | "description" | new Topic() | false
        4   | "http://www.example.com/space%20here.html" | new User() | ""            | new Topic() | false
        5   | "http://www.example.com/space%20here.html" | new User() | null          | new Topic() | false
        6   | "http://www.example.com/space%20here.html" | new User() | "description" | null        | false

    }

    @Unroll
    def "testing toString() of DocumentResource"() {
        given:
        DocumentResource documentResource = new DocumentResource(filePath: "~/home/dummy.txt", createdBy: new User(), description: "Dummy Description", topic: new Topic())

        when:
        String result = "${documentResource}"

        then:
        result == "Document Resource filepath: ~/home/dummy.txt"


    }

    @Unroll
    def "testing getFileName"() {
        given:
        DocumentResource documentResource = new DocumentResource(filePath: "/a/b/c/file.pdf", contentType: '/contentType/pdf')

        when:
        String filePath = documentResource.getFileName()

        then:
        filePath == "/file.pdf"
    }

}