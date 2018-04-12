package linksharing

import services.ResourceService

class LinkResourceController {

    ResourceService resourceService

    def index() {}

    def save() {
        params.createdBy=session.user
        if(resourceService.saveLinkResource(params)){
            flash.message = "Link resource save"
        }
        else{
            flash.message = "errror"
        }
        redirect(controller: 'user', action: 'index')
    }
}
