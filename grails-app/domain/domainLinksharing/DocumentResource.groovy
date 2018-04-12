package domainLinksharing

class DocumentResource extends Resource {
    String filepath
    String fileName


    static transients = ['fileName']

    static constraints = {
        filepath(blank:false,nullable:false)
    }

    String toString() {
        "Document Resource filepath: ${filepath}"
    }

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
