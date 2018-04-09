package linksharingapp

class UtilController {

    def index() {
        log.info("hello this is from log")
        log.warn("hello this is from warn")
        log.info("hello from info")
        render "hello"
        log.info "request params: $params"
    }
}
