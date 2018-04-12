package linksharing

import domainLinksharing.User
import services.SearchService

//import domainLinksharing.User

class SearchController {

    SearchService searchService

    def index() {
        User user
        if (session.user)
            user = session.user
        else
            user = null
        Map map = searchService.showSearchResults(new String(params.searchObject), user)
        render(view: 'search', model: [searchObject: new String(params.searchObject), searchResults: map.searchResults, topPostsList: map.topPostsList, trendingTopicsList: map.trendingTopicsList])
    }
}
