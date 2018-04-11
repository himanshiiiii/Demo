package domainLinksharing

import vo.InboxVO
import vo.SubscriptionsVO

class User {
    String firstname
    String lastname
    String username
    String password
    String confirmpassword
    String email
    byte[] photo
    Boolean admin
    Boolean active
    String name
    Date lastUpdated
    Date dateCreated
    List<ReadingItem> readingItems = []


    String getName() {
        this.name = this.firstname + " " + this.lastname
        return name
    }

    static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

    static constraints = {
        email(email: true, nullable: false, blank: false, unique: true)
        password(nullable: false, blank: false, size: 5..15, validator: { password, obj ->
            println "runnig password validation > " + obj.confirmpassword
            if (obj.confirmpassword) {
                println "inside validation"
                def password2 = obj.confirmpassword
                return (password == password2) ? true : ['invalid.matchingpasswords']
            }
            else
                return true
        })
        firstname(nullable: false, blank: false)
        lastname(nullable: false, blank: false)
        username(nullable: false, blank: false,unique: true)
        photo(nullable: true, sqlType: 'longBlob')
        admin(nullable: true)
        active(nullable: true)
    }

    static mapping = {
        sort id: 'desc'
        subscriptions lazy: false
    }

    static transients = ['name', 'confirmpassword']

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    List<InboxVO> getUnReadResources() {

        List<ReadingItem> unReadItems = ReadingItem.createCriteria().list(max: 10, offset: 0) {
            eq('isRead', false)
            eq('user', this)
        }
        List<InboxVO> unReadItemsList = []
        unReadItems.each{
            unReadItemsList.add(new InboxVO(ownerName: it.resource.createdBy.getName(),
                    ownerUsername: it.resource.createdBy.username,topicName: it.resource.topic.name,
                    topicId: it.resource.topic.id,resourceDescription: it.resource.description,
                    resourceId: it.resource.id, readingItemId: it.id))
        }
        return unReadItemsList
    }

    List<Topic> getSubscribedTopic() {
        List<Topic> subscribedTopics = []
        if (this.subscriptions) {
            this.subscriptions.each {
                    subscribedTopics.add(it.topic)
            }
        }
        return subscribedTopics
    }

    Integer getSubscriptionCount() {
        if (this.subscriptions)
            return this.subscriptions.size()
        else
            return 0
    }

    Integer getTopicCount() {
        if (topics)
            return this.topics.size()
        else
            return 0
    }

    List<String> getUserTopics() {
        List<String> userTopics = []
        if (this.topics) {
            this.topics.each {
                userTopics.add(it.name)
            }
        }
        return userTopics
    }

    List<SubscriptionsVO> getUserSubscriptions(){

        if (this.subscriptions){
            List<SubscriptionsVO> subscriptionList =[]
            this.subscriptions.each{
                subscriptionList.add(new SubscriptionsVO(ownerName: it.topic.createdBy.getName(),
                        ownerUsername: it.topic.createdBy.username, subscriptionId: it.id, topicId: it.topic.id,
                        resourcesCount: it.topic.resources.size(), subscriptionCount: it.topic.subscriptions.size(),
                        subscriptionSeriousness: it.seriousness, topicVisibility: it.topic.visibility, topicName: it.topic.name))
            }
            return subscriptionList
        }
    }

}
