package resource

import domainLinksharing.LinkResource
import domainLinksharing.Resource
import enumeration.Visibility
import domainLinksharing.ReadingItem
import domainLinksharing.Subscription
import domainLinksharing.Topic
import domainLinksharing.User

class LinksharingTagLib {

    static namespace = "ls"
    static defaultEncodeAs = [taglib: 'text']

    def userImage = { attrs, body ->
        out << "<img src='${createLink(controller: 'user', action: 'fetchUserImage', params: [username: attrs.username])}' " +
                " height='${attrs.height}' width='${attrs.width}'>"
    }

    def markRead = { attrs, body ->
        if (session.user && attrs.resource) {
            ReadingItem readingItem = ReadingItem.findByUserAndResource(session.user, attrs.resource)
            if (readingItem) {
                if (readingItem.isRead) {
                    body = "<u>Mark As Unread</u>"

                } else {
                    body = "<u>Mark As Read</u>"
                }
                out << g.link(name: "readingItem", class: "ajaxifiedLink", readingItemId: readingItem.id,
                        id: readingItem.id, body)

            }
        }

    }


    def trendingTopics = {

        out << render(template: '/topic/trendingTopics', model: [trendingTopics: Topic.getTrendingTopics()])
    }

    def topPosts = {
        out << render(template: '/resource/topPosts', model: [topPosts: Resource.topPost()])
    }


    def downloadOrView = { attrs, body ->
        Resource resource = Resource.get(attrs.resourceId)
        if (resource.resourceType()) {
            body = "<u><b>Download</b></u>"
            out << g.link(body, controller: "documentResource", action: "download", params: [resourceId: resource.id])
        } else {
            body = "<u><b>View Page</b></u>"
            LinkResource linkResource = resource as LinkResource
            out << "<a href='${linkResource.url}' target='_blank'>View Page</a>"

        }

    }

    def canDeleteResource = { attrs, body ->
        if (session.user) {
            User user = session.user
            Long id = attrs.resourceId
            if (user.canDeleteResource(id)) {
                body = "Delete"
                out << g.link(body, controller: "resource", action: "delete", params: [id: id])
            }
        }
    }

    def showSubscribe = { attrs, body ->
        User user = session.user
        if (user && attrs.topicId) {
            if (user.isSubscribed(attrs.topicId)) {
                Subscription subscription = Subscription.findByUserAndTopic(user, Topic.get(attrs.topicId))
                body = "<u>Unsubscribe</u>"
                out << g.link([controller: "subscription", action: "delete", params: [id: subscription.id]], body)
            } else {
                body = "<u>Subscribe</u>"
                out << g.link([controller: "subscription", action: "save", params: [id: attrs.topicId]], body)
            }

        }
    }

    def subscriptionCount = { attrs, body ->
        Long userId = attrs.userId
        Long topicId = attrs.topicId
        if (userId) {
            out << Subscription.countByUser(User.load(userId))

        } else if (topicId) {

            out << Subscription.countByTopic(Topic.load(topicId))
        } else {
            out << "error"
        }


    }

    def resourceCount = { attrs, body ->
        Topic topic = Topic.load(attrs.topicId)
        if (topic) {
            out << Resource.countByTopic(topic)
        } else {
            out << "error"
        }
    }

    def topicCount = { attrs, body ->
        User user = User.load(attrs.userId)
        if (user) {
            out << Topic.countByCreatedBy(user)
        }

    }


    def canUpdateTopic = { attrs, body ->
        User user = User.get(attrs.userId)
        Topic topic = Topic.get(attrs.topicId)
        if (user?.admin || user?.isCreatorOf(topic)) {
            out << body()
        } else {
            out << ""
        }
    }

    def showSeriousness = { attrs, body ->
        User user = User.get(attrs.userId)
        Long topicId = attrs.topicId
        Subscription subscription = user?.getSubscription(topicId)

        if (subscription) {
            out << g.select(name: "seriousness", from: Seriousness.values(), value: subscription.seriousness, class: "btn btn-sm btn-default dropdown-toggle seriousness", id: "serious", topicId: topicId)
        }
    }

    def showVisibility = { attrs, body ->
        Long topicId = attrs.topicId
        Topic topic = Topic.get(topicId)
        out << g.select(name: "visibility", from: Visibility.values(), value: topic.visibility, class: "btn btn-sm btn-default dropdown-toggle visibility", id: "visibility", topicName: topic.name)

    }

    def showSubscribedTopics = { attrs, body ->
        User user = session.user
        out << g.select(name: "topic", from: user.getSubscribedTopics(), optionKey: "id", class: "form-control")
    }

}
