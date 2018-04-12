package services

import grails.gorm.transactions.Transactional
import domainLinksharing.User

@Transactional
class LoginService {

    @Transactional
    Map registerUser(Map userData) {
        User checkuser = User.findByUsername(userData.username)
        Map data = [:]
        if (checkuser) {
            data = [errors: ["Username Already Exists"]]
            return data
        } else {
            User user = new User(firstname: userData.firstname, lastname: userData.lastname,
                    email: userData.email, username: userData.username, password: userData.password,
                    confirmpassword: userData.confirmpassword, active: true, photo: userData.photo.bytes)
            if (user.save(flush: true)) {
                log.info("User created : $user")
                data = [user: user]
                return data
            } else {
                log.error("Unable To Register User : $user")
                user.errors.allErrors.each { println(it) }
                data = [errors: user.errors.allErrors]
                return data
            }
        }
    }

    User loginUser(Map loginData) {
        User user = User.findByUsernameAndPassword(loginData.loginusername, loginData.loginpassword)
        if (user) {
            log.info("User logged in: $user")
            return user
        } else
            return null
    }

    User changePassword(Map changeData) {
        User user = User.findByUsername(changeData.username)
        if (user) {
            user.password = changeData.newPassword
            user.confirmpassword = changeData.confirmNewPassword
            if (user.save(flush: true)) {
                log.info("Password Successfully Changed for : $user")
                return user
            } else {
                log.error("Unable To Change Password : $user")
                user.errors.allErrors.each { println(it) }
                return null
            }
        } else {
            log.info("No Such User")
            return null
        }
    }
}
