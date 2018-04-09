package linksharingapp

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import password.ConstantPassword
import spock.lang.Specification

class LinkResourceSpec extends Specification implements DomainUnitTest<LinkResource> {

    def setup() {
    }

    def cleanup() {
    }

    void "validating correct string form"(){
        when:
        LinkResource linkResource = new LinkResource(url: url1)
        boolean result = linkResource.validate()

        then:
        result == valid

        where:

        url1                        | valid
        "https://www.google.co.in"  | true
        "http://www.google.co.in"   | true
        "ftp://www.google.co.in"    | true
        "http://ww.google.com"      | true
        "https://www.google"        | true
        "https://google.com"        | true
        ""                          | false
        "http//www.google.com"      | false
        "http/www.google.com"       | false
        "google.com"                | false

    }

    void "validating toString()"(){
        setup:
        String email = "himanshi.gupta@tothenew.com"
        String password = ConstantPassword.userPassword
        User user = new User(email: email,userName:"himanshi123",password:password, firstName: "himanshi", lastName: "gupta",admin:false,active:true)
        Topic topic = new Topic('name': "my1",'createdBy': user,'visibility': Visibility.PRIVATE)

        when:
        LinkResource linkResource = new LinkResource(createdBy: user,topic: topic,url: "https://www.google.co.in")
        then:
        documentResource.toString() == "DocumentResource{filePath='${documentResource.filePath}'}"
    }
}
