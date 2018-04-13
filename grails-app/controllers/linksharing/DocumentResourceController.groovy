package linksharing

import domainLinksharing.DocumentResource
import services.ResourceService
import domainLinksharing.Topic

class DocumentResourceController {

    ResourceService resourceService

    def index() {}

    def save() {
        def file = params.file
        if (!file) {
            flash.message = "File cannot be empty"
        } else {
            DocumentResource documentInstance = new DocumentResource()
            documentInstance.fileName = file.originalFilename
            documentInstance.filepath = "/home/himanshi/Documents/LinkSharingApp/grails-app/assets/"+ documentInstance.fileName
            documentInstance.createdBy = session.user
            documentInstance.description = params.documentResourceDescription
            Topic newtopic = Topic.findByName(params.topicName)
            println("topic found : >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>. $newtopic")
            documentInstance.topic = newtopic
            file.transferTo(new File(documentInstance.filepath))
            if(documentInstance.save(flush:true)){
                log.info("Document Saved Successfully : $documentInstance")
            }else{
                log.error("Unable to Save : $documentInstance")
                documentInstance.errors.allErrors.each {println it}
            }
        }
        redirect(controller: 'user', action: 'index')
    }

    def list() {
        params.max = 10
        [documentInstanceList: DocumentResource.list(params), documentInstanceTotal: DocumentResource.count()]
    }
}