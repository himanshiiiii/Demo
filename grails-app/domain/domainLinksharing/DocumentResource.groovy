package domainLinksharing

class DocumentResource extends Resource {
    String filepath
    String fileName
//    String contentType


    static transients = ['fileName']

    static constraints = {
        filepath(blank:false,nullable:false)
        //fileName(blank:false,nullable:false)
        //contentType(matches: Constants.DOCUMENT_CONTENT_TYPE)
    }

    String toString() {
        "Document Resource filepath: ${filepath}"
    }

    /*String getFileName() {
        filepath.substring(filepath.lastIndexOf('/'))
    }*/

    def deleteResource() {
        String path = this.filepath
        Boolean status = new File(path).delete()
        if (status) {
            this.delete(flush: true)
            return true
        }
        return false
    }
}
