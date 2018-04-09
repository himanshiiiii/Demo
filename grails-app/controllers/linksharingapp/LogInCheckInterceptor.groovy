package linksharingapp

import javax.servlet.http.HttpSession


class LogInCheckInterceptor {

    public LogInController(){
        matchAll().excludes(controller : 'logIn')
    }

    boolean before() {
        HttpSession session = request.getSession()
        log.info(" from logincheckinterceptor")
        if(!session.getAttribute('user')){
            redirect(controller: 'logIn', action: 'index')
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
