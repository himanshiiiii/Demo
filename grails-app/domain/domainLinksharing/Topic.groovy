package domainLinksharing

import co.TrendingTopicsCO
import enumeration.Seriousness
import enumeration.Visibility
import vo.TrendingTopicsVO

class Topic {
    String name
    Visibility visibility
    User createdBy
    Date lastUpdated
    Date dateCreated

    static belongsTo = [createdBy: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]

    static constraints = {
        name(unique: true, blank: false, nullable: false,)
        visibility(nullable: false)
        createdBy(nullable: false)
    }

    static mapping = {
        sort name: 'asc'
        subscriptions lazy: false
    }

    def afterInsert() {
        Topic.withNewSession {
            this.subscriptions = []
            Subscription subscription = new Subscription(user: createdBy, topic: this, seriousness: Seriousness.VERYSERIOUS)
            if (subscription.save(flush: true)) {
                log.info("Saved Successfully- $subscription")
//                this.addToSubscriptions(subscription)
//                this.createdBy.addToSubscriptions(subscription)
            } else
                log.error("Subscription has errors while validating- $subscription")
        }
    }


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }

//    static getTrendingTopics() {
//        List<Topic> trendingTopics = Resource.createCriteria().list() {
//            projections {
//                createAlias('topic', 't')
//                groupProperty('t.id')
//                property('t.name')
//                property('t.visibility')
//                count('t.id', 'count')
//                property('t.createdBy')
//            }
//            eq('t.visibility', Visibility.PUBLIC)
//            order('count', 'desc')
//            order('t.name', 'asc')
//            maxResults(5)
//        }
//
//        List<Topic> result=[]
//        trendingTopics.each {result.add(new Topic(name: it[1],visibility: it[2],createdBy: it[4]))}
//
//        return result
//    }

    static List<TrendingTopicsVO> getTrendingTopics(TrendingTopicsCO trendingTopicsCO) {
        List<Topic> list = Resource.createCriteria().list() {
            projections {
                groupProperty('topic')
                count('topic.id', 'count')
            }
            order('count', 'desc')
            maxResults(5)
        }
        list.each {println(it[0])}
        List<TrendingTopicsVO> trendingTopicsList = []
        list.each {
            if (trendingTopicsCO.sessionUser) {
                Subscription subscription = Subscription.findByUserAndTopic(trendingTopicsCO.sessionUser, it[0])
                if (subscription)
                    trendingTopicsList.add(new TrendingTopicsVO(ownerName: it[0].createdBy.getName(),
                            ownerUsername: it[0].createdBy.username, subscriptionId: subscription.id,
                            topicId: it[0].id, topicName: it[0].name, resourcesCount: it[0].resources.size(),
                            subscriptionCount: it[0].subscriptions.size(), subscriptionSeriousness: subscription.seriousness,
                            topicVisibility: it[0].visibility))
                else
                    trendingTopicsList.add(new TrendingTopicsVO(ownerName: it[0].createdBy.getName(),
                            ownerUsername: it[0].createdBy.username, topicId: it[0].id, topicName: it[0].name, resourcesCount: it[0].resources.size(),
                            subscriptionCount: it[0].subscriptions.size(), topicVisibility: it[0].visibility))
            } else
                trendingTopicsList.add(new TrendingTopicsVO(ownerName: it[0].createdBy.getName(),
                        ownerUsername: it[0].createdBy.username, topicId: it[0].id, topicName: it[0].name, resourcesCount: it[0].resources.size(),
                        subscriptionCount: it[0].subscriptions.size(), topicVisibility: it[0].visibility))

        }

        return trendingTopicsList
    }

    List<User> getSubscribedUsers() {
        List<User> subscribedUsers = this.subscriptions.user.toList()
        return subscribedUsers
    }

    Integer getSubscriptionCount() {
        if (subscriptions)
            return subscriptions.size()
        else
            return 0
    }
}