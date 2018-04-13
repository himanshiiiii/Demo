import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}

logger 'grails.app', INFO, ['STDOUT'], false
logger 'linksharing', INFO, ['STDOUT'], false
logger 'login', INFO, ['STDOUT'], false
logger 'user', INFO, ['STDOUT'], false
logger 'topic', INFO, ['STDOUT'], false
logger 'resource', INFO, ['STDOUT'], false
logger 'readingItem', INFO, ['STDOUT'], false
logger 'resourceRating', INFO, ['STDOUT'], false
logger 'subscription', INFO, ['STDOUT'], false
logger  'Util', INFO, ['STDOUT'],false
logger("grails.app.controllers.ApplicationInterceptor", INFO, ['STDOUT'], false)
root(ERROR, ['STDOUT'])
