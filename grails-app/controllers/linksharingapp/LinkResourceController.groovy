package linksharingapp

import vo.TopicVO

class LinkResourceController {

    def index() { }

    def save(String url,String description,Long topicId){
        User user = User.findById(session.userId.toLong())
        if (user){
            Topic topic = Topic.findById(topicId)
            LinkResource linkResource = new LinkResource(createdBy: user,description: description,topic:topic,url: url)
            if(linkResource.validate()){
                linkResource.save()
                flash.message = "Resource saved successfully"
//                render("successful")
                redirect(controller:'user',action : 'index')
            }
            else {
                flash.error = "Resource not saved"
                render("Resource not saved ${linkResource.errors.allErrors}")
            }
        }
        else {
            redirect(controller: 'logIn',action: 'index')
        }
    }
}
