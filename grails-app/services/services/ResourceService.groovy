package services

import domainLinksharing.DocumentResource
import domainLinksharing.LinkResource
import domainLinksharing.Resource
import grails.gorm.transactions.Transactional
import domainLinksharing.ResourceRating
import domainLinksharing.Topic
import vo.ResourceVO

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    def saveLinkResource(Map linkResourceData) {
        Resource linkResource = new LinkResource(createdBy: linkResourceData.createdBy, description: linkResourceData.linkTopicDescription, topic: Topic.findByName(linkResourceData.topicName), url: linkResourceData.link)
        if (linkResource.save(flush: true)) {
            log.info("Saved Successfully : $linkResource")
            return linkResourceData
        } else {
            log.error("Error While Saving : $linkResource")
            return null
        }

    }

    def saveDocumentResource(Map documentResourceData) {
        Resource documentResource = new DocumentResource(createdBy: documentResourceData.createdBy, description: documentResourceData.documentResourceDescription, topic: Topic.findByName(documentResourceData.topicName))
        if (documentResource.save(flush: true)) {
            log.info("Saved Successfully : $documentResource")
            return documentResource
        } else {
            log.error("Error While Saving : $documentResource")
            return null
        }
    }

    def showResourcePage(Long resourceId) {
        Resource resource = Resource.findById(resourceId)
        if (resource) {
            ResourceVO resourceVO = new ResourceVO(resourceId: resourceId, topicId: resource.topic.id,
                    resourceDescription: resource.description, ownerName: resource.createdBy.getName(),
                    ownerUsername: resource.createdBy.username, topicName: resource.topic.name)
            return resourceVO
        } else
            return null

    }

    def saveRating(Map resourceData) {
        Resource resource = Resource.findById(resourceData.resourceId)
        ResourceRating resourceRating = ResourceRating.findByCreatedByAndResource(resourceData.ratedBy, resource)
        if (resourceRating) {
            resourceRating.score = resourceData.score
            if (resourceRating.save(flush: true)) {
                log.info("Score Updated Successfully : $resourceRating")
                return resourceRating
            } else {
                log.error("Score Updation Failed : $resourceRating")
                resourceRating.errors.allErrors.each { println it }
                return null
            }
        } else {
            resourceRating = new ResourceRating(createdBy: resourceData.ratedBy, resource: resource, score: resourceData.score)
            if (resourceRating.save(flush: true)) {
                log.info("Score Saved Successfully : $resourceRating")
                return resourceRating
            } else {
                log.error("Error while saving : $resourceRating")
                resourceRating.errors.allErrors.each { println(it) }
                return null
            }
        }
    }

    def deleteResource(Integer resourceId) {
        Resource proxyresource = Resource.load(resourceId)
        if (proxyresource) {
            proxyresource.discard()
            if (proxyresource.delete(flush: true)) {
                log.info("Resource Deleted Successfully : $proxyresource")
                return true
            } else {
                log.error("Unable To Delete Resource : $proxyresource")
                proxyresource.errors.allErrors.each { println it }
                return false
            }
        } else {
            log.info("No Such Record In Our Database")
            return false
        }
    }

    def changeResourceDescription(Map resourceData){
        Resource resource = Resource.findById(resourceData.resourceId)
        resource.description = resourceData.updatedResourceDescription
        if(resource.save(flush:true)){
            log.info("Resource Description Changed Successfully : $resource")
            return true
        }else{
            log.info("Unable To Resource Description : $resource")
            resource.errors.allErrors.each {println it}
            return false
        }

    }


}
