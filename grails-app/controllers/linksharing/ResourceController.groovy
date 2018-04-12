package linksharing

import co.ResourceSearchCo
import co.TrendingTopicsCO
import enumeration.Visibility
import domainLinksharing.DocumentResource
import domainLinksharing.Resource
import services.ResourceService

//import domainLinksharing.Topic
import vo.RatingInfoVO
import vo.ResourceVO
import vo.TrendingTopicsVO

class ResourceController {

    ResourceService resourceService

    def index() {}

    def delete() {
        if (resourceService.deleteResource(new Integer(params.resourceId))) {
            flash.message = "Deleted Successfully"
        } else {
            flash.error = "Unable To Delete"
        }

    }

    def search(ResourceSearchCo resourceSearchCo) {
        if (resourceSearchCo.q) {
            resourceSearchCo.visibility = Visibility.PUBLIC
        }
    }

    def showScore(Integer resourceId) {
        Resource resource = Resource.findById(resourceId)
        RatingInfoVO ratingInfoVO = resource.getResourceRatingInformation()
        render("TOTAL VOTES- $ratingInfoVO.totalVotes + TOTAL SCORE- $ratingInfoVO.totalScore + AVERAGE SCORE- $ratingInfoVO.averageScore")
    }


    def showPost() {
        Long resourceId = new Long(params.id)
        ResourceVO resource = resourceService.showResourcePage(resourceId)
        List<TrendingTopicsVO> trendingTopicsList = []
        if (resource) {
            if (session.user) {
                trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: session.user))
            } else {
                trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: null))
            }
            render(view: 'show', model: [resource: resource, trendingTopicsList: trendingTopicsList])
        } else
            render("RESOURCE NOT FOUND")
    }

    def storeRating() {
        Map map = [score: params.star,ratedBy: session.user, resourceId: params.id]
        if (resourceService.saveRating(map)) {
            flash.message = "Saved Succesfully"
        } else {
            flash.eror = "Rating Not Saved"
        }
        redirect(controller: 'resource', action: 'showPost', model: [params])
    }

    def changeDesciption(){
        if(resourceService.changeResourceDescription(params)){
            flash.message= "Description Changed Successfully"
        }else{
            flash.error = "Unable To Resource Description"
        }
        redirect(controller: 'user', action: 'editProfile')
    }

    def list() {
        params.max = 10
        [documentInstanceList: DocumentResource.list(params), documentInstanceTotal: DocumentResource.count()]
    }

    def downloadDocument(){
        DocumentResource documentInstance = Resource.get(params.id)
        if ( documentInstance == null) {
            flash.message = "Document not found."
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM")
            response.setHeader("Content-Disposition", "Attachment;Filename=\"${documentInstance.fileName}\"")
            def file = new File(documentInstance.filepath)
            def fileInputStream = new FileInputStream(file)
            def outputStream = response.getOutputStream()
            byte[] buffer = new byte[4096];
            int len;
            while ((len = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush()
            outputStream.close()
            fileInputStream.close()
            flash.message = "Document Downloaded."
        }
        redirect (controller: 'user', action: 'index')

    }

}
