package linksharingapp

import enumeration.Seriousness
import enumeration.Visibility
import grails.util.Holders
import password.ConstantPassword

class BootStrap {

    def init = { servletContext ->
        if(User.count()==0){createUsers()}
        createTopic()
        createResources()
        subscribeTopic()
        createReadingItems()
        createResourceRatings()
    }
    def destroy = {
    }


    void allTopic(){
        List<Topic> topics = Topic.findAll()
        topics.each {Topic topic->
            println(topic)
        }
    }

    void allUser(){
        List<User> users = User.findAll()
        users.each {User user->
            println("${user} ${user.id}")
        }
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
        User user = new User()
        user.setFirstName("neha")
        user.setLastName("gupta")
        user.setEmail("neha.gupta@tothenew.com")
        user.setUserName("neha")
        user.setPassword("12345")
        user.setConfirmPassword('12345')
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
                    LinkResource linkResource = new LinkResource('url': "https://www.${topic.name}.${topic.id}.co.in",topic: topic,createdBy: topic.createdBy,description: "This resource is of Topic : ${topic.name} created by : ${topic.createdBy} on ${topic.dateCreated} with topic id : ${topic.id} LinkResource")
                    DocumentResource documentResource = new DocumentResource('filePath': "Document${it}", topic: topic,createdBy: topic.createdBy,description: "This resource is of Topic : ${topic.name} created by : ${topic.createdBy} on ${topic.dateCreated} DocumentResource")
                    if(linkResource.validate()){
                        linkResource.save(flush:true)
                        log.info("Link Resource created successfully!")
                    }
                    else {
                        log.info("Link Resource not saved ${linkResource.hasErrors()}")
                    }
                    if(documentResource.validate()){
                        documentResource.save(flush:true)
                        log.info("Document Resource successfully created!")
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
        users.each {
            User user = it
            user.readingItems.each {
                Resource resource = it.resource
                ResourceRating resourceRating = new ResourceRating(user: user,resource: resource,'score':10)
                println(resourceRating.save())
            }

        }
    }


}


