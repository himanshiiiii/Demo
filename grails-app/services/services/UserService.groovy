package services

import domainLinksharing.User
import grails.gorm.transactions.Transactional
import domainLinksharing.Resource
import domainLinksharing.Topic
import vo.ResourceVO
import vo.TopicVO
import vo.UserVO

@Transactional
class UserService {

    def serviceMethod() {

    }

    def showProfile(String username) {
        User user = User.findByUsername(username)
        UserVO userInformation = new UserVO(name: user.getName(), username: user.username,
                subscriptionCount: user.subscriptions.size(), resourceCount: user.resources.size(),
                topicCount: user.topics.size())

        List<Topic> topicList = Topic.findAllByCreatedBy(user)
        List<TopicVO> userTopics = []
        topicList.each {
            userTopics.add(new TopicVO(topicId: it.id, topicName: it.name, topicVisibility: it.visibility,
                    ownerName: it.createdBy.getName(), ownerUsername: it.createdBy.username,
                    subscriptionCount: it.subscriptions.size(), resourcesCount: it.resources.size()))
        }

        List<Resource> resourceList = Resource.findAllByCreatedBy(user)
        List<ResourceVO> userPosts = []
        resourceList.each {
            userPosts.add(new ResourceVO(resourceId: it.id, topicId: it.topic.id, resourceDescription: it.description,
                    ownerName: it.createdBy.getName(), ownerUsername: it.createdBy.username, topicName: it.topic.name))
        }

        Map map = [userInformation: userInformation, userTopics: userTopics, userPosts: userPosts]
        return map
    }

    def changeUserCredentials(Map userData, String username) {
        User user = User.findByUsername(username)
        user.firstname = userData.updatedFirstname
        user.lastname = userData.updateLastname
        user.username = userData.updatedUsername
        if (userData.updatedPhoto)
            user.photo = userData.updatedPhoto.bytes
        if (user.save(flush: true)) {
            log.info("Credentials Updated : $user")
            return true
        } else {
            log.error("Error Updating Credentials : $user")
            user.errors.allErrors.each { println it }
            return false
        }
    }

    def changePassword(Map userData, String username) {
        User user = User.findByUsername(username)
        user.password = userData.updatedPassword
        user.confirmpassword = userData.updatedConfirmPassword
        if (user.save(flush: true)) {
            log.info("Password Changed : $user")
            return true
        } else {
            log.error("Error Changing Password : $user")
            user.errors.allErrors.each { println it }
            return false
        }
    }

    def showAllUsers() {
        List<User> userList = User.findAllByAdmin(false)
        if (userList) {
            List<UserVO> allUsers = []
            userList.each {
                allUsers.add(new UserVO(name: it.getName(), username: it.username,
                        subscriptionCount: it.subscriptions.size(), resourceCount: it.resources.size(),
                        topicCount: it.topics.size(), active: it.active, userId: it.id))
            }
            return allUsers
        } else
            return null
    }

    def activateDeactivate(Integer userId) {
        User user = User.findById(userId)
        if (user.active)
            user.active = false
        else
            user.active = true
        if (user.save(flush: true)) {
            log.info("State Successfully Changed : $user")
            return true
        } else {
            log.error("Unable to Change State : $user")
            user.errors.allErrors.each { println it }
            return false
        }

    }
}
