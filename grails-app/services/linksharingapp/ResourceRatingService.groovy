package linksharingapp

import grails.gorm.services.Service

@Service(ResourceRating)
interface ResourceRatingService {

    ResourceRating get(Serializable id)

    List<ResourceRating> list(Map args)

    Long count()

    void delete(Serializable id)

    ResourceRating save(ResourceRating resourceRating)

}