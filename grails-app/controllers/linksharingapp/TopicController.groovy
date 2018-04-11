package linksharingapp

import co.ResourceSearchCO
import enumeration.Visibility

import javax.servlet.http.HttpSession


class TopicController {

    def index() {
            Resource resource = Resource.findById(params.id)
            Topic topic = Topic.findById(resource.topic.id)
            println("id")
            render(view: 'index',model: [subscribedUsers:topic.getSubscribedUsers(),resources:topic.resources])
    }


    def save(String topic,String visibility){
        User user = User.findById(session.userId.toLong())
        if(user){
            println(visibility)
            Topic topic1 = new Topic(name: topic,createdBy: user,visibility: Visibility.isVisibility(visibility))
            if(topic1.validate()){
                topic1.save(flush:true)
                flash.message = "Topic is saved ${topic1}"
                render("Topic saved Successfully")
            }
            else {
                flash.error = "topic not saved"
                redirect(controller: 'user',action: 'index')
            }
        }
        else {
            redirect(controller: 'logIn',action: 'index')
        }
    }


    def show(){
        ResourceSearchCO co = new ResourceSearchCO()
        if(params.id){
            Long l = new Long(params.id)
            co.setTopicId(l)
        }
        else if(params.name){
            Topic topic1 = Topic.findByName(params.name)
            co.setTopicId(topic1.id)
            println(topic1)
        }
        List<Topic> topic = Topic.search(co).list()
        log.info("${topic}")
        if (!topic) {
            flash.error = " no topic found"
            redirect(controller: 'logIn', action: 'index')
        }
        else {
                if(topic[0].visibility == Visibility.PUBLIC){
                    render(view: 'index',model: [subscribedUsers:topic[0].getSubscribedUsers(),resources:topic[0].resources])
                }
                else if(topic[0].visibility == Visibility.PRIVATE){
                    User user = User.findById(session.userId.toLong())
                    Subscription subscription = Subscription.findByUserAndTopic(user,topic)
                    if (subscription){
                        render("succcess")
                    }
                    else {
                        flash.error = "flash error set"
                        redirect(controller: 'logIn',action: 'index')
                    }
                }

        }
    }

    def delete(Long id){
        Topic topic = Topic.load(id)
        topic.delete(flush:true)
        render("Topic Deleted Successfully")
    }



    def showSubscribedUsers(){
        Topic topic = Topic.findById(1)
        render(view: 'showSubscribedUsers' , model: [userList : topic.getSubscribedUsers()])
    }
}
