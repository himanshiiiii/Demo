package vo

import enumeration.Visibility
import linksharingapp.User

class TopicVO {
    Integer id
    String name
    Visibility visibility
    Integer count
    User createdBy
}
