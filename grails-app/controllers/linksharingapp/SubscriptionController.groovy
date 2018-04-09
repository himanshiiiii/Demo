package linksharingapp

import enumeration.Seriousness

class SubscriptionController {

    def index() { }

    def save() {
        Topic topic = Topic.get(params.id.toLong())
        User user = User.findById(session.userId.toLong())
        Subscription subscription = new Subscription(topic: topic,user: user,seriousness: Seriousness.VERY_SERIOUS)
        if(subscription.validate()){
            subscription.save()
            redirect(controller : 'logIn',action: 'index')
        }else {
            flash.error = "Subscription not saved."
//            render("${subscription.errors.allErrors}")
            redirect(controller : 'logIn',action: 'index')
        }

    }

    def update(Long id,String seriousness) {
        Subscription subscription = Subscription.findByIdAndSeriousness(id,Seriousness.isSeriousness(seriousness))

        if (subscription){
            if(subscription.validate()){
                subscription.save(flush:true)
                render("Subscription saved successfully")
            }
            else {
                render("Subscription not saved ${subscription.errors.allErrors}")
            }

        }else {
            render("Subscription not found")
        }
    }

    def delete() {
        Topic topic = Topic.findById(params.id.toLong())
        User user = User.findById(session.userId.toLong())
        Subscription subscription = Subscription.findByUserAndTopic(user,topic)
        if(user.subscriptions.contains(subscription) && user!=topic.createdBy){
            user.removeFromSubscriptions(subscription)
            topic.removeFromSubscriptions(subscription)
            try{
                subscription.delete(flush:true)
                flash.message = "subscription deleted successfully"
                redirect(controller : 'logIn',action: 'index')
            }catch (RuntimeException ex){
                flash.error = "error while deleting subscription ${subscription.errors.allErrors} ${subscription}"
                render("error while deleting subscription ${subscription.errors.allErrors} ${subscription}")
                redirect(controller : 'logIn',action: 'index')
            }
        }
    }
}
