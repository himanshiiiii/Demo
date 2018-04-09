package linksharingapp

import grails.artefact.Controller
import vo.TopPostVO

import javax.servlet.http.HttpSession

class LogInController {


    def index() {
        HttpSession session = request.getSession()
        if(session.getAttribute('userId')){
            forward(controller: 'user',action: 'index')
        }
        else {
            render(view: 'index')
        }
    }

    def logInHandler() {
        HttpSession session = request.getSession()
        String userName = params.userName
        String password = params.password
        User user = User.findByUserNameAndPassword(userName,password)
        if (user){
//            session.user = user.id
            session.setAttribute('userId',user.id)
            if(user.active){
//                session.setAttribute('user',user)
                redirect(controller: "user", action: 'index')
            }
            else {
                flash.error = "User is not active"
                render("User not active")
            }
        }
        else {
            flash.error ="User not found"
            render("${flash.error}")
        }
    }

    def logOut() {
        session.invalidate()
        forward(controller : 'logIn', action :'index')
    }

    def register() {
        User user = new User(firstName: params.firstName, lastName: params.lastName, email: params.email,
                    userName: params.userName , password: params.password,confirmPassword: params.confirmPassword)
        if(user.validate()){
            log.info("User registered successfully! ${user.save(flush:true, failOnError : true)}")
            render("Success")
        }
        else {
            flash.message = "flash message is set"
            def msg = message(code: 'usernotsaved',null)
            render(msg)
        }
    }

    def showTopTopics() {
        List<TopPostVO> topPostVOList = Resource.getTopPosts()
        render(view: 'showTopTopics',model: [lists:topPostVOList])
    }
}
