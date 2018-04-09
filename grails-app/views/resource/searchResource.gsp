<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 5/4/18
  Time: 10:38 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="col-lg-5">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            <span class="text-primary">Trending Topics</span>
        </div>
        <ls:getSubscribedTrendingTopics></ls:getSubscribedTrendingTopics>
        <ls:getUnsubscribedTrendingTopics></ls:getUnsubscribedTrendingTopics>
    </div>


    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            <span class="text-primary">Top Posts</span>
        </div>

        <div class="panel-body">
            <ls:getTopPosts/>
        </div>
    </div>
</div>


<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            <span class="text-primary">Search for : ${resourceList[0].topic.name}</span>
        </div>
        <div class="panel-body">
            <g:each in="${resourceList}" var="resource">
                <div class="row" style="padding-bottom: 10px">
                    <div class="col-lg-2">
                        <img class="img-responsive" src="#" alt="himanshi">
                    </div>
                    <div class="col-lg-10">
                        <span class="text">${resource.createdBy.firstName}</span>
                        <span class="text-muted">@${resource.createdBy.userName} ${resource.lastUpdated}</span>
                        <span class="text-primary" style="float: right">${resource.topic.name}</span>
                        <div class="text">
                            <p>
                                ${resource.description}
                            </p>
                        </div>
                        <div>
                            <div class="col-lg-2" style="padding-left: 0px;padding-right: 0px">
                                <i class="fab fa-facebook fa-2x"> </i>
                                <i class="fab fa-tumblr fa-2x"> </i>
                                <i class="fab fa-google-plus-g fa-2x"> </i>
                            </div>
                            <div class="col-lg-2">
                                <a href="#" class="text-primary" style="text-decoration: underline;">Download</a>
                            </div>
                            <div class="col-lg-3">
                                <a href="" class="text-primary" style="text-decoration: underline;">View Full Size</a>
                            </div>
                            <div class="col-lg-3">
                                <a href="#" class="text-primary" style="text-decoration: underline;"><ls:checkRead resource="${resource}"></ls:checkRead></a>
                            </div>
                            <div class="col-lg-2">
                                <a href="#" class="text-primary" style="text-decoration: underline;">View Post</a>
                            </div>
                        </div>
                    </div>
                </div>
            </g:each>
        </div>
    </div>

</div>
</body>
</html>