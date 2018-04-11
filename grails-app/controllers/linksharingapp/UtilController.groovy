package linksharingapp

class UtilController {

    def index() {
        log.warn("hello i'm  in warn")
        log.info("hello i'm in info")
        log.info "request params: $params"
    }
}
