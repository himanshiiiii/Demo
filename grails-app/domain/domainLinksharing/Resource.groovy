package domainLinksharing

import co.ResourceSearchCo
import vo.RatingInfoVO
import vo.RecentSharesVO
import vo.TopPostsVO

abstract class Resource {

    User createdBy
    String description
    Topic topic
    Date lastUpdated
    Date dateCreated

    RatingInfoVO ratingInfo;

    void setRatingInfo() {
        this.ratingInfo = getResourceRatingInformation()
    }

    static transients = ['ratingInfo']

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItems: ReadingItem]

    static constraints = {
        description(type: 'text')
    }

    static namedQueries = {
        search { ResourceSearchCo resourceSearchCo ->
            if (resourceSearchCo.topicId)
                eq('topic.id', resourceSearchCo.topicId)
            if (resourceSearchCo.visibility)
                eq('topic.visibility', resourceSearchCo.visibility)
        }
    }

    Integer totalVotes() {
        Integer totalVotes = ResourceRating.createCriteria().count() {
            eq('resource', this)
        }
        return totalVotes
    }

    Integer totalScore() {
        Integer totalScore = ResourceRating.createCriteria().get() {
            projections {
                sum('score')
            }
            eq('resource', this)
        }
        return totalScore
    }

    Double averageScore() {
        Double averageScore = ResourceRating.createCriteria().get() {
            projections {
                avg('score')
            }
            eq('resource', this)
        }
        return averageScore
    }

    RatingInfoVO getResourceRatingInformation() {
        RatingInfoVO ratingInfoVO = new RatingInfoVO()
        ratingInfoVO.totalVotes = totalVotes()
        ratingInfoVO.totalScore = totalScore()
        ratingInfoVO.averageScore = averageScore()
        return ratingInfoVO
    }

    static List<TopPostsVO> getTopPost() {
        def list = ResourceRating.createCriteria().list {
            projections {
                createAlias('resource', 'r')
                groupProperty('r.id')
                property('r.createdBy')
                property('r.topic')
                count('r.id', 'count')
                property('r.description')
            }
            order('count', 'desc')
            maxResults(5)
        }
        List<TopPostsVO> topPostsList = []
        list.each {
            topPostsList.add(new TopPostsVO(ownerName: it[1].getName(), ownerUsername: it[1].username,
                    topicName: it[2].name, topicId: it[2].id, resourceId: it[0], resourceDescription: it[4]))
        }
        return topPostsList
    }

    static List<RecentSharesVO> getRecentShares() {

        List<Resource> resourceList = Resource.createCriteria().list {
            order("dateCreated", "desc")
            maxResults(2)

        }
        List<RecentSharesVO> recentSharesList = []
        resourceList.each {
            recentSharesList.add(new RecentSharesVO(ownerName: it.createdBy.getName(),
                    ownerUsername: it.createdBy.username, topicName: it.topic.name, topicId: it.topic.id,
                    resourceDescription: it.description, resourceId: it.id))
        }
        return recentSharesList
    }

}
