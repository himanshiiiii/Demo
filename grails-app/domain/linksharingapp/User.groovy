package linksharingapp

import co.SearchCO

class User {

    String firstName
    String lastName
    String email
    String userName
    String password
    String confirmPassword
    byte photo
    Boolean admin
    Boolean active
    Date dateCreated
    Date lastUpdated
    String name

    String getName() {
        this.name = this.firstName + " " + this.lastName
        return name
    }

    static hasMany = [topics:Topic , subscriptions:Subscription , readingItems:ReadingItem, resources:Resource]

    User(String firstName, String lastName, String email, String userName, String password, byte photo, Boolean admin, Boolean active) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.userName = userName
        this.password = password
        this.photo = photo
        this.admin = admin
        this.active = active
    }
    static constraints = {
        email(email: true,nullable: false,blank: false,unique: true)//Nullable : Allows a property to be set to null - defaults to false.
        password(nullable: false,blank: false,minSize: 5 ,validator: { password, obj ->
                        def password2 = obj.confirmPassword
                        password == password2 ? true : ['invalid.matchingpasswords']
        })
        firstName(nullable: false,blank: false)
        lastName(nullable: false,blank: false)
        userName(unique: true)
        photo(nullable: true,sqlType: 'longBlob')
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(nullable: false, blank: false)
    }
    static transients = ['name','confirmPassword']

    static mapping = {
        sort id: 'desc'
//        topics batchSize: 2
        subscriptions fetch: 'join'
        readingItems fetch: 'join'
        topics fetch: 'join'
    }

    static List<ReadingItem> getUnReadResources(SearchCO searchCO){
        List<ReadingItem> readingItems=[]
        if(!searchCO.q.equals(null)){
            readingItems = ReadingItem.createCriteria().list(max:searchCO.max,offset:searchCO.offset) {
                resource{
                    ilike('description',"%${searchCO.q}%")
                }
                eq('isRead',true)
            }
        }
        return readingItems
    }

    List<Topic> getSubscribedTopics(){
        List<Topic> topicList = []
        subscriptions.each {
            topicList.add(it.topic)
        }
        return topicList
    }

    Integer getScore(Resource resource){
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(this,resource)
        if (resourceRating){
            return resourceRating.score
        }else {
            return 1
        }
    }

    Boolean isSubscribed(Long topicId){
        Topic topic = Topic.findById(topicId)
        Integer count = Subscription.createCriteria().count{
            eq('user',this)
            eq('topic',topic)
        }
        if(count){
            return true
        }
        else {
            return false
        }
    }

    Boolean canDeleteResourceMethod(Resource resource){
        if(this.admin || this == resource.createdBy){
            return true
        }else {
            return false
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
