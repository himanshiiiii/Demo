package linksharing

import services.LoginService
import domainLinksharing.Resource
import domainLinksharing.User
import vo.RecentSharesVO
import vo.TopPostsVO

class LoginController {

    LoginService loginService

    def index() {
        if (session.user) {
            log.info("REDIRECTING TO USER INDEX")
            forward(controller: "User", action: "index")
        } else {
            List<RecentSharesVO> recentSharesList = Resource.getRecentShares()
            List<TopPostsVO> topPostsList = Resource.getTopPost()
            log.info("NO SESSION USER FOUND")
            render(view: 'index', model: [recentSharesList: recentSharesList, topPostsList: topPostsList])
        }
    }

    def loginhandler() {
        User user = loginService.loginUser(params)
        if (user) {
            if (user.active) {
                session.user = user
                redirect(controller: "user", action: "index")
            } else {
                flash.error = "YOUR ACCOUNT IS INACTIVE"
                render(view: 'error')
            }
        } else {
            flash.error = "INCORRECT USERNAME OR PASSWORD"
            redirect(controller: 'Login', action: "index")
        }
    }

    def register() {
        Map data = loginService.registerUser(params)
        if (data.errors) {
            flash.error = "Unable to Register User. Reason: [${data.errors}]"
            redirect(controller: 'login', action: 'index')
        } else if (data.user) {
            flash.message = "SUCCESSFULLY REGISTERED"
            session.username = data.user.username
            forward(controller: 'User', action: 'index')
        }
    }

    def logout() {
        session.invalidate()
        flash.error = "USER LOGGED OUT"
        redirect(controller: 'login', action: 'index')
    }

    def forgotPassword() {
        User user = User.findByUsername(params.username)
        if (user) {
            session.user = user
            flash.message = "PASSWORD CHANGED SUCCESSFULLY"
            forward(controller: 'User', action: 'index')
        } else {
            flash.error = "UNABLE TO CHANGE THE PASSWORD"
            render(view: 'forgotPassword')
        }
    }

}
