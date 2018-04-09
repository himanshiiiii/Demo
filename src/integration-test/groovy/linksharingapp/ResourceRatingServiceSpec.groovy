package linksharingapp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ResourceRatingServiceSpec extends Specification {

    ResourceRatingService resourceRatingService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ResourceRating(...).save(flush: true, failOnError: true)
        //new ResourceRating(...).save(flush: true, failOnError: true)
        //ResourceRating resourceRating = new ResourceRating(...).save(flush: true, failOnError: true)
        //new ResourceRating(...).save(flush: true, failOnError: true)
        //new ResourceRating(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //resourceRating.id
    }

    void "test get"() {
        setupData()

        expect:
        resourceRatingService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ResourceRating> resourceRatingList = resourceRatingService.list(max: 2, offset: 2)

        then:
        resourceRatingList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        resourceRatingService.count() == 5
    }

    void "test delete"() {
        Long resourceRatingId = setupData()

        expect:
        resourceRatingService.count() == 5

        when:
        resourceRatingService.delete(resourceRatingId)
        sessionFactory.currentSession.flush()

        then:
        resourceRatingService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ResourceRating resourceRating = new ResourceRating()
        resourceRatingService.save(resourceRating)

        then:
        resourceRating.id != null
    }
}
