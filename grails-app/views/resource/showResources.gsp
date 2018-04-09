<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 3/4/18
  Time: 5:26 PM
--%>
<%@ page import="linksharingapp.User" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="layout" content="application">
</head>

<body>

<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-lg-2">
                    <img>
                </div>

                <div class="col-lg-10">
                    <span>${resource.createdBy.firstName}</span>
                    <span class="pull-right text-primary">
                        <a class="hyperlink">${resource.topic.name}</a>
                    </span>
                    <br>
                    <span class="text-muted">@${resource.createdBy.userName}</span>
                    <span class="pull-right">${resource.dateCreated}</span>
                    <br>
                    <span class="pull-right">
                        <g:if test="${!userId || user.getScore(resource)==1}">
                            <g:form url="[controller: 'resourceRating',action: 'save', params: [id: resource.id]]">
                                <g:select name="resource.rating" from="${1..5}" value="rating"></g:select>
                                <g:submitButton name="vote"></g:submitButton>
                            </g:form>
                        </g:if>
                        <g:else>
                            <%         linksharingapp.User user = linksharingapp.User.findById(session.userId.toLong())    %>
                            ${user.getScore(resource)}
                        </g:else>
                        <a href="#">
                            <i class="fas fa-heart"></i>
                        </a>
                        <a href="#">
                            <i class="fas fa-heart"></i>
                        </a>
                        <a href="#">
                            <i class="fas fa-heart"></i>
                        </a>
                        <a href="#">
                            <i class="far fa-heart"></i>
                        </a>
                        <a href="#">
                            <i class="far fa-heart"></i>
                        </a>
                    </span>
                </div>
            </div>

            <div class="row">
                <div class="text-justify">
                    ${resource.description}
                    %{--A blog (a truncation of the expression "weblog")[1] is a discussion or informational website published on the World Wide Web consisting of discrete, often informal diary-style text entries ("posts"). Posts are typically displayed in reverse chronological order, so that the most recent post appears first, at the top of the web page. Until 2009, blogs were usually the work of a single individual,[citation needed] occasionally of a small group, and often covered a single subject or topic. In the 2010s, "multi-author blogs" (MABs) have developed, with posts written by large numbers of authors and sometimes professionally edited. MABs from newspapers, other media outlets, universities, think tanks, advocacy groups, and similar institutions account for an increasing quantity of blog traffic. The rise of Twitter and other "microblogging" systems helps integrate MABs and single-author blogs into the news media. Blog can also be used as a verb, meaning to maintain or add content to a blog.--}%

                    %{--The emergence and growth of blogs in the late 1990s coincided with the advent of web publishing tools that facilitated the posting of content by non-technical users who did not have much experience with HTML or computer programming. Previously, a knowledge of such technologies as HTML and File Transfer Protocol had been required to publish content on the Web, and as such, early Web users tended to be hackers and computer enthusiasts. In the 2010s, the majority are interactive Web 2.0 websites, allowing visitors to leave online comments, and it is this interactivity that distinguishes them from other static websites.[2] In that sense, blogging can be seen as a form of social networking service. Indeed, bloggers do not only produce content to post on their blogs, but also often build social relations with their readers and other bloggers.[3] However, there are high-readership blogs which do not allow comments.--}%
                </div>

                <div>
                    <div class="col-lg-2" style="padding-left: 0px;padding-right: 0px">
                        <i class="fab fa-facebook fa-2x"></i>
                        <i class="fab fa-tumblr fa-2x"></i>
                        <i class="fab fa-google-plus-g fa-2x"></i>
                    </div>

                    <div class="col-lg-2">
                        <a href="${createLink(controller: 'resource',action: 'delete', id: resource.id)}" class="text-primary" style="text-decoration: underline;"><ls:canDeleteResource resourceId="${resource.id}"></ls:canDeleteResource></a>
                    </div>

                    <div class="col-lg-2">
                        <a href="#" class="text-primary" style="text-decoration: underline;">Edit</a>
                    </div>

                    <div class="col-lg-2">
                        <a href="#" class="text-primary" style="text-decoration: underline;"><ls:checkRead resource="${resource}"></ls:checkRead></a>
                    </div>

                    <g:if test="${resourceType.equals("DocumentResource")}">
                        <div class="col-lg-2">
                            <a href="#" class="text-primary" style="text-decoration: underline;">Download</a>
                        </div>
                    </g:if>

                    <g:if test="${resourceType.equals("LinkResource")}">
                        <div class="col-lg-2">
                            <a href="${resource.url}" class="text-primary" target="_blank" style="text-decoration: underline;">View Full Site</a>
                        </div>
                    </g:if>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="col-lg-5">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            Trending Topics
        </div>
        <ls:getSubscribedTrendingTopics></ls:getSubscribedTrendingTopics>
        <ls:getUnsubscribedTrendingTopics></ls:getUnsubscribedTrendingTopics>
    </div>
</div>

</body>
</html>