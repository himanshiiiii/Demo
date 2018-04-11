package util

import groovy.util.logging.Log4j


@Log4j
class Utility {
    static void saving(Object object){
        if(object.save(flush:true)){
            log.error("Error while saving- $object")
            object.errors.allErrors.each { println it }
        }
        else
        {
            log.info("Saved successfully- $object")
        }
    }
}
