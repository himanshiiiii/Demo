package linksharing


class ApplicationInterceptor {

    public ApplicationInterceptor(){
        matchAll()
    }

    boolean before() {
        log.info("INCOMING REQUEST: ${params.toString()}")
        true
    }

    boolean after() {true}

    void afterView() {
        // no-op
    }
}
