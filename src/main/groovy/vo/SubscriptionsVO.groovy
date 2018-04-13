package vo

import enumeration.Seriousness
import enumeration.Visibility

class SubscriptionsVO {
    String ownerName
    String ownerUsername
    String topicName
    Integer subscriptionId
    Integer topicId
    Integer resourcesCount
    Integer subscriptionCount
    Seriousness subscriptionSeriousness
    Visibility topicVisibility
}
