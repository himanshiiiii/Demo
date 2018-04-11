package linksharing

import grails.util.Holders
import domainLinksharing.ReadingItem
import domainLinksharing.ResourceRating
import domainLinksharing.Subscription
import enumeration.Seriousness
import enumeration.Visibility
import domainLinksharing.DocumentResource
import domainLinksharing.LinkResource
import domainLinksharing.Resource
import domainLinksharing.Topic
import domainLinksharing.User

class BootStrap {
    def grailsApplication

    def init = { servletContext ->

        createResources()
        createReadingItems()
        createResourceRatings()


        String fileUploadLocation = Holders.grailsApplication.config.file.uploadLocation
        println "fileUploadLocation=> $fileUploadLocation"
        //Admin-

        User admin = new User()
        admin.firstname = "Himanshi"
        admin.lastname = "Gupta"
        admin.username = "himanshigupta"
        admin.password = "12345"
        admin.confirmpassword = "12345"
        admin.email = "himanashi.gupta@tothenew.com"
        admin.photo = null
        admin.admin = true
        admin.active = true
        if (admin.save(flush: true)) {
            log.info("Saved Successfully : $admin")
        } else {
            log.error("Error while saving : $admin")
        }

//Users-

        User user1 = new User()
        user1.firstname = "Abhishek"
        user1.lastname = "kunwar"
        user1.username = "abhishekkunwar"
        user1.password = "12345"
        user1.confirmpassword = "12345"
        user1.email = "abhishek.kunwar@tothenew.com"
        user1.photo = null
        user1.admin = false
        user1.active = true
        if (user1.save(flush: true)) {
            log.info("Saved Successfully : $user1")
        } else {
            log.error("Error while saving : $user1")
        }


        User user2 = new User()
        user2.firstname = "Arpan"
        user2.lastname = "Kedia"
        user2.username = "arpankedia"
        user2.password = "12345"
        user2.confirmpassword = "12345"
        user2.email = "arpan.kedia@tothenew.com"
        user2.photo = null
        user2.admin = false
        user2.active = true
        if (user2.save(flush: true)) {
            log.info("Saved Successfully : $user2")
        } else {
            log.error("Error while saving : $user2")
        }


        User user3 = new User()
        user3.firstname = "Neha"
        user3.lastname = "vijay"
        user3.username = "nehavijay"
        user3.password = "12345"
        user3.confirmpassword = "12345"
        user3.email = "neha.vijay@tothenew.com"
        user3.photo = null
        user3.admin = false
        user3.active = true
        if (user3.save(flush: true)) {
            log.info("Saved Successfully : $user3")
        } else {
            log.error("Error while saving : $user3")
        }


        User user4 = new User()
        user4.firstname = "Mithu"
        user4.lastname = "Das"
        user4.username = "mithudas"
        user4.password = "12345"
        user4.confirmpassword = "12345"
        user4.email = "mithu.das@tothenew.com"
        user4.photo = null
        user4.admin = false
        user4.active = true
        if (user4.save(flush: true)) {
            log.info("Saved Successfully : $user4")
        } else {
            log.error("Error while saving : $user4")
        }

//        User user5 = new User()
//        user5.firstname = "Ajay"
//        user5.lastname = "Jodha"
//        user5.username = "ajay.jodha"
//        user5.password = "ajay1234"
//        user5.confirmpassword = "ajay1234"
//        user5.email = "ajay.jodha@tothenew.com"
//        user5.photo = null
//        user5.admin = false
//        user5.active = false
//        if (user5.save(flush: true)) {
//            log.info("Saved Successfully : $user5")
//        } else {
//            log.error("Error while saving : $user5")
//        }
//
//        User user6 = new User()
//        user6.firstname = "Rahul"
//        user6.lastname = "Babu"
//        user6.username = "rahul.babu"
//        user6.password = "rahul"
//        user6.confirmpassword = "rahul"
//        user6.email = "rahul.babu@tothenew.com"
//        user6.photo = null
//        user6.admin = false
//        user6.active = true
//        if (user6.save(flush: true)) {
//            log.info("Saved Successfully : $user6")
//        } else {
//            log.error("Error while saving : $user6")
//        }

//Topics-

        Topic topic1 = new Topic(name: "Photography", visibility: Visibility.PUBLIC, createdBy: user1)
        if (topic1.save(flush: true)) {
            log.info("Saved Successfully : $topic1")
            user1.addToTopics(topic1)
        } else {
            log.error("Error while saving- $topic1")
        }


        Topic topic2 = new Topic(name: "Dancing", visibility: Visibility.PRIVATE, createdBy: user1)
        if (topic2.save(flush: true)) {
            log.info("Saved Successfully : $topic2")
            user1.addToTopics(topic2)
        } else {
            log.error("Error while saving- $topic2")
        }


        Topic topic3 = new Topic(name: "FunPage", visibility: Visibility.PUBLIC, createdBy: user2)
        if (topic3.save(flush: true)) {
            log.info("Saved Successfully : $topic3")
            user2.addToTopics(topic3)
        } else {
            log.error("Error while saving- $topic3")
        }


        Topic topic4 = new Topic(name: "JokesApart", visibility: Visibility.PUBLIC, createdBy: user2)
        if (topic4.save(flush: true)) {
            log.info("Saved Successfully : $topic4")
            user2.addToTopics(topic4)
        } else {
            log.error("Error while saving- $topic4")
        }


        Topic topic5 = new Topic(name: "Friendship", visibility: Visibility.PUBLIC, createdBy: user3)
        if (topic5.save(flush: true)) {
            log.info("Saved Successfully : $topic5")
            user3.addToTopics(topic5)
        } else {
            log.error("Error while saving- $topic5")
        }


        Topic topic6 = new Topic(name: "songs", visibility: Visibility.PUBLIC, createdBy: user3)
        if (topic6.save(flush: true)) {
            log.info("Saved Successfully : $topic6")
            user3.addToTopics(topic6)
        } else {
            log.error("Error while saving- $topic6")
        }


        Topic topic7 = new Topic(name: "Gyming", visibility: Visibility.PUBLIC, createdBy: user4)
        if (topic7.save(flush: true)) {
            log.info("Saved Successfully : $topic7")
            user4.addToTopics(topic7)
        } else {
            log.error("Error while saving- $topic7")
        }


        Topic topic8 = new Topic(name: "NGO", visibility: Visibility.PRIVATE, createdBy: user4)
        if (topic8.save(flush: true)) {
            log.info("Saved Successfully : $topic8")
            user4.addToTopics(topic8)
        } else {
            log.error("Error while saving- $topic8")
        }

//
//        Topic topic9 = new Topic(name: "BeautyPlus", visibility: Visibility.PUBLIC, createdBy: user5)
//        if (topic9.save(flush: true)) {
//            log.info("Saved Successfully : $topic9")
//            user5.addToTopics(topic9)
//        } else {
//            log.error("Error while saving- $topic9")
//        }
//
//
//        Topic topic10 = new Topic(name: "", visibility: Visibility.PRIVATE, createdBy: user5)
//        if (topic10.save(flush: true)) {
//            log.info("Saved Successfully : $topic10")
//            user5.addToTopics(topic10)
//        } else {
//            log.error("Error while saving- $topic10")
//        }

        Subscription subscription1 = new Subscription(user: user1, topic: topic3, seriousness: Seriousness.SERIOUS)
        if (subscription1.save(flush: true)) {
            log.info("Saved Successfully : $subscription1")
            topic3.addToSubscriptions(subscription1)
            user1.addToSubscriptions(subscription1)
        } else {
            log.error("Error while saving- $subscription1")
        }

        Subscription subscription2 = new Subscription(user: user1, topic: topic4, seriousness: Seriousness.SERIOUS)
        if (subscription2.save(flush: true)) {
            log.info("Saved Successfully : $subscription2")
            topic4.addToSubscriptions(subscription2)
            user1.addToSubscriptions(subscription2)
        } else {
            log.error("Error while saving- $subscription2")
        }

        Subscription subscription3 = new Subscription(user: user2, topic: topic5, seriousness: Seriousness.SERIOUS)
        if (subscription3.save(flush: true)) {
            log.info("Saved Successfully : $subscription3")
            topic5.addToSubscriptions(subscription3)
            user2.addToSubscriptions(subscription3)
        } else {
            log.error("Error while saving- $subscription3")
        }

        Subscription subscription4 = new Subscription(user: user2, topic: topic6, seriousness: Seriousness.SERIOUS)
        if (subscription4.save(flush: true)) {
            log.info("Saved Successfully : $subscription4")
            topic6.addToSubscriptions(subscription4)
            user2.addToSubscriptions(subscription4)
        } else {
            log.error("Error while saving- $subscription4")
        }

//        Subscription subscription5 = new Subscription(user: user3, topic: topic7, seriousness: Seriousness.SERIOUS)
//        if (subscription5.save(flush: true)) {
//            log.info("Saved Successfully : $subscription5")
//            topic7.addToSubscriptions(subscription5)
//            user3.addToSubscriptions(subscription5)
//        } else {
//            log.error("Error while saving- $subscription5")
//        }
//
//        Subscription subscription6 = new Subscription(user: user3, topic: topic8, seriousness: Seriousness.VERYSERIOUS)
//        if (subscription6.save(flush: true)) {
//            log.info("Saved Successfully : $subscription6")
//            topic8.addToSubscriptions(subscription6)
//            user3.addToSubscriptions(subscription6)
//        } else {
//            log.error("Error while saving- $subscription6")
//        }
//
//        Subscription subscription7 = new Subscription(user: user4, topic: topic9, seriousness: Seriousness.VERYSERIOUS)
//        if (subscription7.save(flush: true)) {
//            log.info("Saved Successfully : $subscription7")
//            topic9.addToSubscriptions(subscription7)
//            user4.addToSubscriptions(subscription7)
//        } else {
//            log.error("Error while saving- $subscription7")
//        }
//
//        Subscription subscription8 = new Subscription(user: user4, topic: topic10, seriousness: Seriousness.VERYSERIOUS)
//        if (subscription8.save(flush: true)) {
//            log.info("Saved Successfully : $subscription8")
//            topic10.addToSubscriptions(subscription8)
//            user4.addToSubscriptions(subscription8)
//        } else {
//            log.error("Error while saving- $subscription8")
//        }

//        Subscription subscription9 = new Subscription(user: user5, topic: topic1, seriousness: Seriousness.CASUAL)
//        if (subscription9.save(flush: true)) {
//            log.info("Saved Successfully : $subscription9")
//            topic1.addToSubscriptions(subscription9)
//            user5.addToSubscriptions(subscription9)
//        } else {
//            log.error("Error while saving- $subscription9")
//        }
//
//        Subscription subscription10 = new Subscription(user: user5, topic: topic2, seriousness: Seriousness.CASUAL)
//        if (subscription10.save(flush: true)) {
//            log.info("Saved Successfully : $subscription10")
//            topic2.addToSubscriptions(subscription10)
//            user5.addToSubscriptions(subscription10)
//        } else {
//            log.error("Error while saving- $subscription10")
//        }


    }

    void createResources() {

        List<Topic> topicList = Topic.findAll()
        topicList.each {
            Topic temp = it

            if (!Resource.findByTopic(temp)) {
                (1..1).each {
                    LinkResource linkResource = new LinkResource(createdBy: temp.createdBy, description: "This resource is created by ${temp.createdBy.getName()} for topic ${temp.name}", topic: temp, url: "www.${temp.createdBy.username}.com/${temp.name}")
                    DocumentResource documentResource = new DocumentResource(createdBy: temp.createdBy, description: "This resource is created by ${temp.createdBy.getName()} for topic ${temp.name}", topic: temp, filepath: "/${temp.createdBy.username}/${temp.name}")
                    if (linkResource.save(flush: true)) {
                        temp.addToResources(linkResource)
                        temp.createdBy.addToResources(linkResource)
                        log.info("Saved Successfully : $linkResource")
                    } else
                        log.error("Error while saving : $linkResource")

                    if (documentResource.save(flush: true)) {
                        temp.addToResources(documentResource)
                        temp.createdBy.addToResources(documentResource)
                        log.info("Saved Successfully : $documentResource")
                    } else {
                        log.error("Error while saving : $documentResource")
                        documentResource.errors.allErrors.each {println it}
                    }
                }
            }
            temp.save(flush: true)
        }
    }


    void createReadingItems() {
        Random random = new Random()
        List<Subscription> subscriptionList = Subscription.findAll()
        List<User> userList = User.findAll()
        subscriptionList.each {
            for (User user : userList) {
                if (it.user == user && !user.topics.contains(it.topic)) {
                    for (Resource resource : it.topic.resources) {
                        ReadingItem readingItem = new ReadingItem(user: user, isRead: (random.nextInt(6) < 4), resource: resource)
                        if (!user.readingItems.contains(readingItem)) {
                            if (!readingItem.save(flush: true)) {
                                log.error("Error while saving : $readingItem")
                            } else {
                                log.info("Saved Succesfully: $readingItem")
//                                user.addToReadingItems(readingItem)
//                                it.topic.resources.get(resource)addToReadingItems(readingItem)
                            }
                        }
                    }
                }
            }
        }
    }


    void createResourceRatings() {
        Random random = new Random()
        List<ReadingItem> readingItemList = ReadingItem.findAll()
        println readingItemList.size()
        readingItemList.each {
            if (it.isRead) {
                ResourceRating resourceRating = new ResourceRating(resource: it.resource, createdBy: it.user, score: random.nextInt(6))
                if (!resourceRating.save(flush: true)) {
                    log.error("Error while saving : $resourceRating")
                    resourceRating.errors.allErrors.each { println it }
                } else
                    log.info("Saved Successfully : $resourceRating")
            }
        }
    }


    def destroy = {
    }


}
