package vo

class ResourceVO {
    Integer resourceId
    Integer topicId
    String resourceDescription
    String ownerName
    String ownerUsername
    String topicName


    @Override
    public String toString() {
        return "ResourceVO{" +
                "id=" + id +
                ", count=" + count +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                '}';
    }
}
