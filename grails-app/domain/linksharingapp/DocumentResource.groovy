package linksharingapp

class DocumentResource extends Resource {

    String filePath
    Date dateCreated
    Date lastUpdated
    static constraints = {
    }

    @Override
    public String toString() {
        return "DocumentResource{" +
                "filePath='" + filePath + '\'' +
                '}';
    }
}
