package linksharingapp

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class LogInCheckInterceptorSpec extends Specification implements InterceptorUnitTest<LogInCheckInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test logInCheck interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"logInCheck")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
