<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 3/4/18
  Time: 12:59 PM
--%>

<%@ page import="linksharingapp.User" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="application">
</head>

<body>
<div class="col-lg-5">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="col-lg-3">
                <img src="#" class="img-responsive">
            </div>
            <%         linksharingapp.User user = linksharingapp.User.findById(session.userId.toLong())    %>

            <div class="col-lg-9">
                <div class="text">
                    <h4>${user.name}</h4>
                </div>

                <div class="text-muted">
                    @${user.userName}
                </div>

                <div class="col-lg-4">
                    <span class="text-muted">Subscriptions</span>
                    <span class="text-primary"><ls:subscriptionCount></ls:subscriptionCount></span>
                </div>

                <div class="col-lg-3">
                    <span class="text-muted">Topics</span>
                    <span class="text-primary"><ls:topicCount></ls:topicCount></span>
                </div>

            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            <span class="text-primary">Subscriptions</span>
            <span style="float: right">
                <a href="#" class="hyperlink">View All</a>
            </span>
        </div>

        <div class="panel-body">
            <div class="row">

                <div class="col-lg-2">
                    <img src="#" class="img-responsive">
                </div>

                <div class="col-lg-10">
                    <div class="text-primary">
                        <a class="hyperlink" href="#">Grails</a>
                    </div>

                    <div class="col-lg-4" style="padding-left: 0px">
                        <span class="text-muted">@himanshi</span>
                        <a href="#" class="hyperlink">Unsubscribe</a>
                    </div>

                    <div class="col-lg-4">
                        <span class="text-muted">Subscriptions</span>
                        <span class="text-primary">50</span>
                    </div>

                    <div class="col-lg-2">
                        <span class="text-muted">Topics</span>
                        <span class="text-primary">50</span>
                    </div>
                </div>


                <span class="dropdown col-lg-offset-2">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Seriousness
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Very Serious</a></li>
                        <li><a href="#">Serious</a></li>
                        <li><a href="#">Less Serious</a></li>
                    </ul>
                </span>

                <span class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Scope
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">Private</a></li>
                        <li><a href="#">Public</a></li>
                    </ul>
                </span>
                <i class="far fa-envelope fa-2x"></i>
                <i class="far fa-file fa-2x"></i>
                <i class="far fa-trash-alt fa-2x"></i>

            </div>

            <div class="row">
                <div class="col-lg-2">
                    <img src="#" class="img-responsive">
                </div>

                <div class="col-lg-10">
                    <div class="text-primary">
                        <a class="hyperlink" href="#">Grails</a>
                    </div>

                    <div class="col-lg-4" style="padding-left: 0px">
                        <span class="text-muted">@himanshi</span>
                        <a href="#" class="hyperlink">Unsubscribe</a>
                    </div>

                    <div class="col-lg-4">
                        <span class="text-muted">Subscriptions</span>
                        <span class="text-primary">50</span>
                    </div>

                    <div class="col-lg-2">
                        <span class="text-muted">Topics</span>
                        <span class="text-primary">50</span>
                    </div>
                </div>


                <div style="float:right; padding-right: 15px">
                    <span class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Seriousness
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Very Serious</a></li>
                            <li><a href="#">Serious</a></li>
                            <li><a href="#">Less Serious</a></li>
                        </ul>
                    </span>
                    <i class="far fa-envelope fa-2x"></i>
                </div>

            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            Trending Topics
        </div>
        <ls:getSubscribedTrendingTopics></ls:getSubscribedTrendingTopics>
        <ls:getUnsubscribedTrendingTopics></ls:getUnsubscribedTrendingTopics>
    </div>
</div>

<div class="col-lg-7">
    <div class="panel panel-default">
        <div class="panel-heading" style="background: salmon">
            Inbox
        </div>

        <div class="panel-body">
            <g:render template="inbox" collection="${unReadItems}" var="unReadResource"></g:render>
        </div>
    </div>
</div>
</body>
</html>