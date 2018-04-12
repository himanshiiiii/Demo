package vo

import enumeration.Seriousness
import enumeration.Visibility

class TrendingTopicsVO {
    String ownerName
    String ownerUsername
    Integer subscriptionId
    String topicName
    Integer topicId
    Integer resourcesCount
    Integer subscriptionCount
    Seriousness subscriptionSeriousness
    Visibility topicVisibility
}
