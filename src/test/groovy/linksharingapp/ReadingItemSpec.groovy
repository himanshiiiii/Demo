package linksharingapp

import enumeration.Visibility
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ReadingItemSpec extends Specification implements DomainUnitTest<ReadingItem> {

    def setup() {
    }

    def cleanup() {
    }

   void "validating"(){
       setup:
       User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "abcdef",photo: 1,admin: true,active: null)
       Topic topic1= new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)
       Resource resource = new LinkResource(createdBy: user1, description: "njkasjks",topic:topic1 , url: "https://www.google.com")

       when:
       ReadingItem readingItem = new ReadingItem(isRead: true,resource: resource,user: user1)
       Boolean b = readingItem.validate()

       then:
       b==true
   }
}
