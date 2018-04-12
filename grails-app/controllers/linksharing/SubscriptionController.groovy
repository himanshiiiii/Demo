package linksharing

import domainLinksharing.Subscription
import enumeration.Seriousness
import services.SubscriptionService

class SubscriptionController {

    SubscriptionService subscriptionService

    def index() {}

    def save() {
        Map map = [topicId: new Integer(params.topicId), subscriber: session.user]
        if (subscriptionService.saveSubscription(map)) {
            flash.message = "SUBSCRIPTION ADDED SUCCESSFULLY"
        } else {
            flash.error = "SUBSCRIPTION NOT DONE"
        }
        redirect(controller: 'user', action: 'index')
    }

    def update(Integer subscriptionId, String seriousness) {
        seriousness = Seriousness.convert(seriousness)
        Subscription subscription = Subscription.findById(subscriptionId)
        if (subscription) {
            subscription.seriousness = seriousness
            if (subscription.save(flush: true)) {
                log.info("Saved Successfully : $subscription")
                render("SUCCESS")
            } else {
                log.error("Error while Saving : $subscription")
                render("FAILURE")
            }
        } else
            render("SUBSCRIPTION NOT FOUND")
    }

    def delete() {
        if (subscriptionService.deleteSubscription(params)) {
            flash.message = "SUBSCRIPTION DELETED SUCCESSFULLY"
            redirect(controller: 'user', action: 'index')
        } else {
            flash.error = "NO SUCH SUBSCRIPTION IN OUR DATABASE"
            render("NO SUCH SUBSCRIPTION IN OUR DATABASE")
        }
    }

    def changeSeriousness(){
        println "SubscriptionController.changeSeriousness"
        println "data ::" + params
        if(subscriptionService.changeSeriousness(params)){
            flash.message = "Updated"
        }else{
            flash.error = "Unable to Update"
        }
        redirect(controller: 'user', action: 'editProfile')
    }

    def subscribeThroughEmail(){
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>" + params
        if(subscriptionService.subscribeThroughEmail(params.id)){
            flash.message= "subscription done"
        }else
            flash.error= "not able to subscribe"
    }
}
