package linksharingapp

import enumeration.Seriousness
import enumeration.Visibility
import grails.util.Holders
import password.ConstantPassword

class BootStrap {

    def init = { servletContext ->
        //demo()
        if(User.count()==0){createUsers()}
        createTopic()
        createResources()
        subscribeTopic()
        createReadingItems()
        createResourceRatings()
//        retrieveTopic()
//        retrieveUser()
    }
    def destroy = {
    }


    void createUsers(){
         User admin = new User()
        admin.setFirstName("himanshi")
        admin.setLastName("upta")
        admin.setEmail("himanshi.gupta@tothenew.com")
        admin.setUserName("himanshi123")
        admin.setPassword(ConstantPassword.userPassword)
        admin.setConfirmPassword(ConstantPassword.userPassword)
//        admin.setPhoto(null)
        admin.setAdmin(true)
        admin.setActive(true)

        if(admin.validate()){
            println(admin.save(failOnError : true,flush : true))
        }
        log.info("Admin is valid- ${admin.validate()}")
        log.info("Admin has errors while validating- ${admin.hasErrors()}")

        User user = new User()
        user.setFirstName("neha")
        user.setLastName("gupta")
        user.setEmail("neha.gupta@tothenew.com")
        user.setUserName("neha")
        user.setPassword("12345")
        user.setConfirmPassword('12345')
//        user.setPhoto(null)
        user.setAdmin(false)
        user.setActive(true)

        if (user.validate()){
            println(user.save(failOnError : true , flush: true))
        }

        log.info("User is valid- ${user.validate()}")
        log.info("User has errors while validating- ${user.hasErrors()}")

    }

    void createTopic(){
        List<User> allUsers = User.findAll()
        allUsers.each {
            if(!it.topics){
                User temp = it
                5.times {
                    Topic topic = new Topic("mytopic${it}",temp,Visibility.PUBLIC)
                    if(topic.validate()){
                        log.info("Topic saved successfully - ${topic.save(flush:true)}")
                        temp.addToTopics(topic)
                    }
                    else {
                        log.info("Topic has errors while saving -${topic.hasErrors()}")
                    }
                }
            }
        }
    }

    void createResources(){
        List<Topic> myTopics = Topic.findAll()
        myTopics.each {
            Topic topic = it
            if(!Resource.findByTopic(topic)){
                2.times {
                    //creator of resource is same as creator of topic i.e., topic: topic,createdBy: topic.createdBy
                    LinkResource linkResource = new LinkResource('url': "https://www.${topic.name}.${topic.id}.co.in",topic: topic,createdBy: topic.createdBy,description: "This resource is of Topic : ${topic.name} created by : ${topic.createdBy} on ${topic.dateCreated} with topic id : ${topic.id} LinkResource")
                    DocumentResource documentResource = new DocumentResource('filePath': "Document${it}", topic: topic,createdBy: topic.createdBy,description: "This resource is of Topic : ${topic.name} created by : ${topic.createdBy} on ${topic.dateCreated} DocumentResource")
                    if(linkResource.validate()){
                        linkResource.save(flush:true)
                        log.info("Link Resource created successfully!")
//                        topic.addToResources(linkResource)
                    }
                    else {
                        log.info("Link Resource not saved ${linkResource.hasErrors()}")
                    }
                    if(documentResource.validate()){
                        documentResource.save(flush:true)
                        log.info("Document Resource created successfully!")
//                        topic.addToResources(documentResource)
//                        documentResource.save(flush:true)
                    }
                    else {
                        log.info("Document Resource not saved ${documentResource.hasErrors()}")
                    }

                }
                println(topic.resources)
            }
        }
    }

    void subscribeTopic(){
        List<User> users = User.findAll()
        List<Topic> topics = Topic.findAll()
        users.each {
            User user = it
            topics.each {
                Topic topic = it
                if(topic.createdBy!=user){
                    //subscriptions created only if subscription not exists for user and topic i.e., usSubscription.findByUserAndTopic(user,topic)
                    if(!Subscription.findByUserAndTopic(user,topic)){
                        Subscription subscription = new Subscription(user: user,topic: topic,seriousness: Seriousness.SERIOUS)
                        if(subscription.validate()){
                            log.info("User subscribed successfully ${subscription.save(flush : true)}")
                            topic.addToSubscriptions(subscription)
                            user.addToSubscriptions(subscription)
                        }
                        else{
                            log.info("error occured while subscribing ${subscription.hasErrors()}")
                        }
                    }
                }
            }
            println(it.subscriptions)
        }

    }

    void createReadingItems(){
        List<User> users = User.findAll()
        List<Resource> resources = Resource.findAll()
        users.each {
            User user = it
            resources.each {
                Resource resource = it
                if(!user.topics.contains(resource.topic)){
                    ReadingItem readingItem = new ReadingItem(resource: resource,'user': user,isRead: true)
                    if(!ReadingItem.findByUserAndResource(user,resource)){
                        println(readingItem.save(flush:true))
                        user.addToReadingItems(readingItem)
                    }
                }
            }

        }

    }

    void createResourceRatings(){
        List<Resource> resources = Resource.findAll()
        List<User> users = User.findAll()

        /*resources.each {
            Resource resource = it
            users.each {
                User user = it
                ResourceRating resourceRating = new ResourceRating(resource: resource,user: user,'score': 3)
                println(resourceRating.save(flush:true))
//                ResourceRating resourceRating1 = new ResourceRating(resource: resource,user: user,'score': 5)
//                println(resourceRating1.save())
//                rating not allowed if already provided by same user, throwing duplicatekeyexception
            }
        }*/

        //user of resourceRating is user of readingItems
        //resource of userRating is resource of readingItems
        users.each {
            User user = it
            user.readingItems.each {
                Resource resource = it.resource
                ResourceRating resourceRating = new ResourceRating(user: user,resource: resource,'score':3)
                println(resourceRating.save())
            }

        }
    }

    void retrieveTopic(){
        List<Topic> topics = Topic.findAll()
        topics.each {Topic topic->
            println(topic)
        }
    }

    void retrieveUser(){
        List<User> users = User.findAll()
        users.each {User user->
            println("${user} ${user.id}")
        }
    }

    void demo(){
        User user2 = new User(firstName: "neha",lastName: "gupta",email: "neha@ttn.com",userName: "neha123",password: "12345",photo: 1,admin: true,active: null)
        Topic topic2= new Topic(name: "mytopic2",createdBy: user2,visibility: Visibility.PRIVATE)
        User user1 = new User(firstName: "himanshi",lastName: "gupta",email: "himanshi.gupta@ttn.com",userName: "himanshi123",password: "12345",photo: 1,admin: true,active: null)
        Topic topic1= new Topic(name: "mytopic1",createdBy: user1,visibility: Visibility.PRIVATE)
        Resource resource = new LinkResource(createdBy: user1, description: "google",topic:topic1 , url: "https://www.google.com")
        ResourceRating resourceRating = new ResourceRating(resource: resource,user: user1,score: 3)
        println(resourceRating.save())
        ResourceRating resourceRating2 = new ResourceRating(resource: resource,user: user2,score: 4)
        println(resourceRating2.save())
    }
}


