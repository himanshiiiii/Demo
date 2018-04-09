package linksharingapp

import co.ResourceSearchCO
import co.SearchCO
import enumeration.Visibility
import vo.RatingInfoVO
import vo.TopPostVO
import vo.TopicVO

class ResourceController {

    def index(Long id) {
        Resource resource = Resource.findById(id)
        if (session.userId) {
            User user = User.findById(session.userId.toLong())
            if (resource.canViewBy(user)) {
                String resourceType = Resource.findTypeOfResource(id)
                render(view: 'showResources', model: [resource: resource, resourceType: resourceType])
            }
        } else if (resource.topic.isPublic()) {
            String resourceType = Resource.findTypeOfResource(id)
            render(view: 'showResources', model: [resource: resource, resourceType: resourceType])
        } else {
            flash.error = "User cannot view this resource"
            redirect(controller: 'logIn', action: 'index')
        }
    }

    def delete() {
        Long id = new Long(params.id)
        println(id)
        Resource resource = Resource.load(id)
        def listOfReadingItems = ReadingItem.findAllByResource(resource)
        def users = User.findAll()
        println(resource)
        try {
            users.each {
                println(it)
                User user = it
                listOfReadingItems.each {
                    if (user.readingItems.contains(it)) {
                        user.removeFromReadingItems(it)
                    }
                }
                user.removeFromResources(resource)
            }
            resource.topic.removeFromResources(resource)
//                println("after topic.resources"+resource.topic.resources.size())
            ReadingItem.executeUpdate("delete from ReadingItem where resource_id=:id", [id: resource.id])
            ResourceRating.executeUpdate("delete from ResourceRating where resource_id=:id", [id: resource.id])
            Resource.executeUpdate("delete from Resource where id=:id", [id: resource.id])
            flash.message = "Resource deleted successfully"
            redirect(controller: 'logIn', action: 'index')
        }
        catch (RuntimeException ex) {
            flash.error = "Resource not deleted"
            redirect(controller: 'logIn', action: 'index')
        }
    }

    def searchResource() {
        Topic topic = Topic.findByName(params.searchKey)
        if (!topic) {
            flash.error = "Search not found"
            redirect(controller: 'logIn', action: 'index')
        } else {
            SearchCO co = new ResourceSearchCO()
            co.topicId = topic.id
            co.q = topic.visibility
            if (co.q) {
                co.setVisibility(topic.visibility)
            }
            println(co.topicId)
            List<Resource> resources = Resource.search(co).list()
            render(view: 'searchResource', model: [resourceList: resources])
        }
    }

    def showResources(Long id) {
        Resource resource = Resource.findById(id)
        RatingInfoVO ratingInfoVO = resource.method()
//        render("${ratingInfoVO.totalVotes} ${ratingInfoVO.averageScore} ${ratingInfoVO.totalScore}")
        render("hello")
    }

    def showTrendingTopics() {
        List<TopicVO> topicVOList = Topic.getTrendingTopics()
        render("${topicVOList}")
    }

    def findTypeOfResource() {
        Resource.findTypeOfResource(2)
        render("sucess")
    }
}
