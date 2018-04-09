package linksharingapp

import co.ResourceSearchCO
import enumeration.Seriousness
import enumeration.Visibility
import vo.TopicVO


class Topic {

    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility
    List resources

    static belongsTo = [createdBy:User]
    static hasMany = [subscriptions:Subscription,resources:Resource]

    Topic(String name, User createdBy, Visibility visibility) {
        this.name = name
        this.createdBy = createdBy
        this.visibility = visibility
    }
    static constraints = {
        name(nullable: false,blank: false,unique: 'createdBy')
        visibility(nullable: false)
        createdBy(nullable: false)
    }

    static mapping = {
        sort name: 'asc'
    }

    static namedQueries = {
        search{ResourceSearchCO co ->
            eq('id',co.topicId)
        }
    }

    def afterInsert(){
        Topic.withNewSession {
            Subscription subscription = new Subscription(this,this.createdBy,Seriousness.VERY_SERIOUS)
            if (subscription.save(flush:true)){
                log.info("Subscription saved successfully - ${this.addToSubscriptions(subscription)}")
            }
            else {
                log.info("Subscription has errord while saving - ${subscription.hasErrors()}")
            }
        }
    }

    static List<TopicVO> getTrendingTopics(){
        List<TopicVO> topicVOList = Resource.createCriteria().list {
            projections{
                createAlias('topic','t')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                count('t.id','count17')
                property('t.createdBy')
            }
            eq('t.visibility',Visibility.PUBLIC)
            order('count17','desc')
            order('t.name','asc')
            maxResults(5)
        }.collect{it->
            new TopicVO(id: it[0],name: it[1],visibility: it[2],count: it[3],createdBy: it[4])
        }
        return topicVOList

    }

    List<User> getSubscribedUsers(){
        List<User> userList = []
        subscriptions.each {
            userList.add(it.user)
        }
        return userList
    }

    Boolean isPublic(){
        if(this.visibility == Visibility.PUBLIC){
            return true
        }
        else {
            return false
        }
    }

    Boolean canViewedBy(User user){
        if (this.isPublic() || user.subscriptions.contains(this) || user.admin) {
            return true
        }
        else {
            return false
        }
    }


    @Override
    public String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}';
    }
}
