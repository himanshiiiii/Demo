package linksharingapp

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import password.ConstantPassword
import spock.lang.Specification

class DocumentResourceSpec extends Specification implements DomainUnitTest<DocumentResource> {

    def setup() {
    }

    def cleanup() {
    }

    void "testing filePath field"(){
        when:
        DocumentResource documentResource = new DocumentResource(filePath: "/home/file/himanshi")
        boolean result = documentResource.validate()

        then:
        result == true
    }

    void "testing filePath field to null"(){
        when:
        DocumentResource documentResource = new DocumentResource(filePath: "")
        boolean result = documentResource.validate()

        then:
        result == false
    }

    void "validating toString()"(){
        setup:
        String email = "himanshi.gupta@tothenew.com"
        String password = ConstantPassword.userPassword
        User user = new User(email: email,userName:"himanshi123",password:password, firstName: "himanshi", lastName: "gupta",admin:false,active:true)
        Topic topic = new Topic('name': "my1",'createdBy': user,'visibility': Visibility.PRIVATE)

        when:
        DocumentResource documentResource = new DocumentResource(createdBy: user,topic: topic,description: "hello",filePath: "filePath")
        documentResource.save(deepValidate :false)

        then:
        documentResource.toString() == "DocumentResource{filePath='${documentResource.filePath}'}"
    }
}
