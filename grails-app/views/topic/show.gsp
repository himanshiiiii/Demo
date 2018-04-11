<%@ page import="domainLinksharing.Topic; domainLinksharing.Subscription; domainLinksharing.Topic; topic.Topic; domainLinksharing.Subscription" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title></title>

    <asset:link rel="stylesheet" href="application.css"></asset:link>
    <meta name="layout" content="main">

</head>

<body>

<div class="container">
    <div class="col-lg-6">
        <div class="col-lg-12">
            <div class=" panel panel-default     ">
                <div class="panel-heading " style="background: #007efc">
                    <p>

                    <h3 style="color:white">Topic:"${topicVO.topicName}"</h3></p>
                </div>

                <div class="panel-body  ">
                    <div class="col-lg-12">
                        <div class="col-sm-3">
                            <ls:userImage username="${topicVO.ownerUsername}" height="100" width="100"/>
                        </div>
                        <div class="col-lg-9">
                            <div class="col-lg-12">
                                <h5><a href="#">${topicVO.topicName}</a> <span
                                        STYLE="color: #007efc ">(${topicVO.topicVisibility})</span></h5>
                            </div>

                            <div class="col-lg-12">
                                <div class="col-lg-5">
                                    <small
                                            class="text-muted">@${topicVO.ownerUsername}</small>
                                    <g:if test="${session.user}">
                                        <% domainLinksharing.Topic topic = domainLinksharing.Topic.findById(topicVO.topicId)
                                        subscription.Subscription subscription = domainLinksharing.Subscription.findByUserAndTopic(session.user, topic) %>
                                        <g:if test="${subscription}">
                                            <g:link controller="subscription" action="delete"
                                                    params="${[topicId: topicVO.topicId]}">Unsubscribe</g:link>
                                        </g:if>
                                        <g:else>
                                            <g:link controller="subscription" action="save"
                                                    params="${[topicId: topicVO.topicId]}">Subscribe</g:link>
                                        </g:else>
                                    </g:if>
                                </div>

                                <div class="col-lg-5">
                                    <p style="color: #007efc">Subscriptions</p>
                                    <p1 style="color: #2e6da4">${topicVO.subscriptionCount}</p1>

                                </div>

                                <div class="col-lg-2">
                                    <p style="color: #007efc">Post</p>
                                    <p1 style="color: #2e6da4">${topicVO.resourcesCount}</p1>
                                </div>

                            </div>

                        </div>
                    </div>

                </div>
            </div>

        </div>

        <div class="col-lg-12">
            <div class=" panel panel-default     ">
                <div class="panel-heading " style="background: #007efc">
                    <p>

                    <h3 style="color:white">User:"${topicVO.topicName}"</h3></p>
                </div>

                <div class="panel-body  ">
                    <g:each in="${subscribedUserList}" var="subscribedUser">
                        <div class="col-lg-12">
                            <div class="col-sm-3">
                                <ls:userImage username="${subscribedUser.username}" height="100" width="100"/>
                            </div>

                            <div class="col-lg-9">
                                <div class="col-lg-12">
                                    <h5>${subscribedUser.name}</h5>

                                    <p class="text-muted">@${subscribedUser.username}</p>
                                </div>

                                <div class="col-lg-12">
                                    <div class="col-lg-6">
                                        <p style="color: #007efc">Subscriptions</p>
                                        <p1 style="color: #007efc">${subscribedUser.subscriptionCount}</p1>
                                    </div>

                                    <div class="col-lg-6">
                                        <p style="color: #007efc">Topics</p>
                                        <p1 style="color: #2e6da4">${subscribedUser.topicCount}</p1>

                                    </div>

                                </div>

                            </div>
                        </div>

                        <div class="col-lg-12">
                            <hr>
                        </div>
                    </g:each>
                </div>
            </div>

        </div>
    </div>

    <div class="col-lg-6">
        <div class="col-lg-12">
            <div class="col-lg-12">
                <div class=" panel panel-default     ">
                    <div class="panel-heading col-lg-12" style="background: #007efc;">
                        <div class="col-lg-4">
                            <h3 style="color:white">Posts:"${topicVO.topicName}"</h3>
                        </div>

                        <div class="col-lg-8">
                            <div class="topnav pull-rigth " style="background-color: #007efc">

                                <div class="search-container">
                                    <form action="/action_page.php">
                                        <input type="text" placeholder="Search.." name="search">
                                        <button type="submit" style="background-color: #007efc;margin-right: 0px"><i
                                                class="fa fa-search"
                                                style="font-size:25px;color:white;background-color: #007efc"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="panel-body  ">
                        <g:each in="${resourceList}" var="resource">
                            <div class="col-lg-12">
                                <div class="col-sm-3">
                                    <ls:userImage username="${resource.ownerUsername}" height="100" width="100"/>
                                </div>

                                <div class="col-lg-9">
                                    <div class="col-sm-12">
                                        <br>

                                        <span>${resource.ownerName} &nbsp;&nbsp;&nbsp;&nbsp;<small
                                                class="text-muted">@${resource.ownerUsername}</small>

                                            <a href="#" class="pull-right">${topicVO.topicName}</a>
                                            <br><br>

                                            <div class="col-lg-12">
                                                <p>${resource.resourceDescription}</p>
                                            </div>

                                        </span>

                                        <div>
                                            <i class="fa fa-facebook-square fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-google-plus fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-twitter-square fa-lg" aria-hidden="true"></i>
                                            <span class="pull-right" style="margin-right: 0px;color: #007efc">
                                                <a href="${createLink(controller:'resource',action:'downloadDocument',id:resource.resourceId)}">Download</a>
                                                <a href="${createLink(controller: 'resource', action: 'showPost', id: resource.resourceId)}">View Post</a>
                                            </span>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </g:each>
                    </div>
                </div>

            </div>

        </div>
    </div>

</div>

</body>
</html>