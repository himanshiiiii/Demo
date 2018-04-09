package linksharingapp

import co.ResourceSearchCO
import vo.RatingInfoVO
import vo.TopPostVO

abstract class Resource {

    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated
    //createdBy description topic already created
    static belongsTo = [createdBy:User,topic:Topic]
    static hasMany = [ratings:ResourceRating, readingItems:ReadingItem]
    static constraints = {
        description(type: "text")
    }

    static mapping = {
//        topic fetch: 'join'
        topic lazy: false
        createdBy lazy: false
        readingItems lazy: false
        readingItems cascade: 'delete'
        ratings cascade: 'delete'
    }

    static transients = ['ratingInfo']
    static namedQueries = {
        search{ ResourceSearchCO co ->
            'topic'{
                eq('visibility',co.visibility)
            }
            eq('topic.id',co.topicId)
        }
    }

    RatingInfoVO method(){
        RatingInfoVO vo = new RatingInfoVO()

        vo.totalVotes = ResourceRating.createCriteria().count{
            eq('resource',this)
        }
        List averageScore = ResourceRating.createCriteria().list{
            projections{
                avg('score')
            }
            eq('resource',this)
        }
        vo.averageScore = averageScore[0]


        List totalScore = ResourceRating.createCriteria().list {
            projections{
                sum('score')
            }
            eq('resource',this)
        }
        vo.totalScore = totalScore[0]


        return vo
    }

    static List<TopPostVO> getTopPosts(){
        List<TopPostVO> topPostVOList = ResourceRating.createCriteria().list {
            projections{
                createAlias('resource','res')
                groupProperty('res.id')
                property('res.createdBy')
                createAlias('res.topic','t')
                property('t.name')
                count('res.id','counts')
            }
            order('counts','desc')
            maxResults(5)
        }.collect { it ->
            new TopPostVO(id: it[0], createdBy: it[1], topicName: it[2], count: it[3])
        }
        return topPostVOList
    }

    Long totalVotes(){
        Long vote = ResourceRating.createCriteria().count(){
            eq('resource',this)
        }
        return vote
    }

    static String findTypeOfResource(Long id){
//        LinkResource linkResource = Resource.findById(id)
        Resource resource = Resource.findById(id)
        if(resource.class == LinkResource.class){
            return "LinkResource"
        }
        else if(resource.class == DocumentResource.class){
            return "DocumentResource"
        }
    }

    Boolean canViewBy(User user){
        if (this.topic.canViewedBy(user)){
            return true
        }
        else {
            return false
        }
    }

}
