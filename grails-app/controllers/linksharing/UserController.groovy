package linksharing

import co.TrendingTopicsCO
import domainLinksharing.User
import services.UserService

import domainLinksharing.Topic
import vo.InboxVO
import vo.SubscriptionsVO
import vo.TrendingTopicsVO
import vo.UserVO

class UserController {

    UserService userService

    def assetResourceLocator

    def index() {
        if (session.user) {
            List<InboxVO> unReadResourcesList = session.user.getUnReadResources()
            List<SubscriptionsVO> subscriptionsList = session.user.getUserSubscriptions()
            List<TrendingTopicsVO> trendingTopicsList = Topic.getTrendingTopics(new TrendingTopicsCO(sessionUser: session.user))
            render(view: 'index', model: [unReadResourcesList: unReadResourcesList, subscriptionsList: subscriptionsList, trendingTopicsList: trendingTopicsList])
        } else
            redirect(controller: 'login', action: 'index')
    }

    def editProfile() {
        Map map = userService.showProfile(new String(session.user.username))
        render(view: 'editProfile', model: [user: map.userInformation, userTopics: map.userTopics, userPosts: map.userPosts])
    }

    def updateUser() {
        if (userService.changeUserCredentials(params, new String(session.user.username))) {
            flash.message = "Updation Successful"
        } else
            flash.error = "Unable To Update Credentials"
        redirect(controller: 'user', action: 'index')
    }

    def changePassword() {
        if (userService.changePassword(params, new String(session.user.username))) {
            flash.message = "Password Changed Successfully"
        } else
            flash.error = "Unable To Change password"

        redirect(controller: 'user', action: 'index')
    }

    def showUserListToAdmin(){
        List<UserVO> allUsers= userService.showAllUsers()
        render(view: 'userList', model: [allUsers:allUsers])
    }

    def changeState(){
        println "Printing params- $params.id"
        if(userService.activateDeactivate(new Integer(params.id))){
            flash.message= "State Changed"
        }else
            flash.error= "Unable To Change State"
        redirect(controller: 'user', action: 'showUserListToAdmin')
    }

    def fetchUserImage(){
        def user = User.findByUsername(params.username)
        byte[] photo
        if(!user?.photo){
            println("Photo Not Found")
            photo = assetResourceLocator.findAssetForURI('user.png').byteArray
        }else {
            println("Photo Found")
            photo= user.photo
        }
        OutputStream out = response.getOutputStream()
        out.write(photo)
        out.flush()
        out.close()
    }


}