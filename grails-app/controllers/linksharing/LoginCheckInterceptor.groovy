package linksharing


class LoginCheckInterceptor {

    public LoginCheckInterceptor() {
        //matchAll().excludes(controller: 'login')
    }

    boolean before() {
        if (!session.user) {
            flash.error = "NO ACTIVE SESSION"
            redirect(controller: 'login', action: 'index')
        }
        true
    }

    boolean after() {true}

    void afterView() {
        // no-op
    }
}
