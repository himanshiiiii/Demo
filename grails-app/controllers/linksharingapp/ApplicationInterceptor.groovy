package linksharingapp


import grails.interceptors.Matcher
import org.springframework.context.event.ApplicationEventMulticaster

import javax.servlet.http.HttpSession


class ApplicationInterceptor {

    public ApplicationInterceptor(){
        matchAll()
    }


    boolean before() {
        HttpSession session = request.getSession()
        log.info("from before of ApplicationInterceptor. Params : ${params}")
        if(!session.getAttribute('user')){
            true
        }
        else {
            true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
