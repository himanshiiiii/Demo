package services

import co.TrendingTopicsCO
import grails.gorm.transactions.Transactional
import domainLinksharing.Resource
import domainLinksharing.Topic
import domainLinksharing.User
import vo.TopPostsVO
import vo.TopicVO
import vo.TrendingTopicsVO

@Transactional
class SearchService {

    def serviceMethod() {

    }

    def showSearchResults(String searchObject, User user) {
        List<Topic> topicList = Topic.findAllByNameIlike("%${searchObject}%")

        List<TopicVO> searchResults = []
        if(topicList) {
            topicList.each {
                searchResults.add(new TopicVO(topicId: it.id, topicName: it.name, topicVisibility: it.visibility,
                        ownerName: it.createdBy.getName(), ownerUsername: it.createdBy.username,
                        subscriptionCount: it.subscriptions.size(), resourcesCount: it.resources.size()))
            }
        }

        List<TopPostsVO> topPostsList = Resource.getTopPost()

        List<TrendingTopicsVO> trendingTopicsList = []

        if (user)
            trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: user))
        else
            trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: null))

        Map map = [searchResults: searchResults, topPostsList: topPostsList, trendingTopicsList: trendingTopicsList]

        return map
    }
}
