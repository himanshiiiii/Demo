package linksharing

import grails.plugins.mail.MailService

class InvitationController {

    MailService mailService

    def sendMail() {
        println("printing params : " + params)
        println "InvitationController sendMail >> " + params.recieverEmail
        println "InvitationController bean >> " + mailService

        //

        mailService.sendMail {

            to "${params.recieverEmail}"
            subject "Regarding Invitation For Topic : ${params.topicName}"
            text """send mail 
<a href = "${createLink(controller: 'subscription', action: 'subscribeThroughEmail', id: params.topicName)}">Click Here</a>
"""
        }
        flash.message= "invitation send successfully"
        redirect(controller: 'user', action: 'index')
    }
}
