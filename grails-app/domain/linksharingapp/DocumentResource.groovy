package linksharingapp

class DocumentResource extends Resource {

    String filePath
    Date dateCreated
    Date lastUpdated
    //filePath already exists
    static constraints = {
    }

    @Override
    public String toString() {
        return "DocumentResource{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
