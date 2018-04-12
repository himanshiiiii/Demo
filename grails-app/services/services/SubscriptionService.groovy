package services

import domainLinksharing.Subscription
import domainLinksharing.User
import enumeration.Seriousness
import grails.gorm.transactions.Transactional
import domainLinksharing.Topic

@Transactional
class SubscriptionService {

    def serviceMethod() {

    }

    def saveSubscription(Map subscriptionData) {

        Topic topic = Topic.findById(subscriptionData.topicId)
        Subscription subscription = new Subscription(user: subscriptionData.subscriber, topic: topic, seriousness: Seriousness.SERIOUS)
        if (subscription.save(flush: true)) {
            log.info("Saved Successfully : $subscription")
//            session.user.addToSubscriptions(subscription)
            topic.addToSubscriptions(subscription)
            topic.save(flush: true)
            return subscription
        } else {
            log.error("Error while saving- $subscription")
            subscription.errors.allErrors.each { println it }
            return null
        }
    }

    def deleteSubscription(Map params){
        Subscription subscription = Subscription.findById(params.subscriptionId)
        if (!subscription) {
            return false
        } else {
//            subscription.removeFrom(Subscription)
//            subscription.removeFrom(subscription);
            subscription.discard()
            subscription.delete(flush: true)
            return true
        }
    }

    def changeSeriousness(Map subscriptionData){
        Subscription subscription = Subscription.findById(subscriptionData.id)
        subscription.seriousness= subscriptionData.seriousness
        if(subscription.save(flush:true)){
            log.info("Seriousness Changed : $subscription")
            return true
        }else{
            log.error("Unable to Change Seriousness : $subscription")
            subscription.errors.allErrors.each {println it}
            return false
        }
    }

    def subscribeThroughEmail(String topicName){
        Topic inviteTopic = Topic.findByName(topicName)
        Subscription subscription = new Subscription(user: session.user, topic: inviteTopic,seriousness: Seriousness.SERIOUS )
        if(subscription.save(flush:true)){
            log.info("Subscription added Successfully")
            return true
        }else{
            log.error("Unable to add Subscription")
            subscription.errors.allErrors.each {println it}
            return false
        }
    }
}
