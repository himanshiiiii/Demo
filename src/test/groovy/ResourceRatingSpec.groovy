package resourceRating

import domainLinksharing.DocumentResource
import domainLinksharing.LinkResource
import domainLinksharing.ResourceRating
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import domainLinksharing.User

@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    @Unroll
    void "Testing constraints"() {

        setup:
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: score)

        when:
        Boolean result = resourceRating.validate()

        then:
        result == valid

        where:
        sno | resource               | user       | score | valid
        0   | new DocumentResource() | new User() | 3     | true
        1   | new LinkResource()     | new User() | 3     | true
        2   | null                   | new User() | 3     | false
        3   | new LinkResource()     | null       | 3     | false
        4   | new LinkResource()     | new User() | 0     | false
        5   | new LinkResource()     | new User() | 6     | false
        6   | new LinkResource()     | new User() | 1     | true
        7   | new LinkResource()     | new User() | 5     | true


    }


    @Unroll
    def "validate unique resource rating"() {
        given:
        LinkResource resource = new LinkResource()
        User user = new User()
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: 3)
        ResourceRating resourceRating1 = new ResourceRating(resource: resource, user: user, score: 4)

        when:
        resourceRating.save(flush: true)
        resourceRating1.save(flush: true)

        then:
        !resourceRating.errors.allErrors.size()
        resourceRating1.errors.allErrors.size()
        resourceRating1.errors.getFieldError('resource')


    }

}