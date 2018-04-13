<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>searchPage</title>
    <meta name="layout" content="main">

</head>

<body>
<div class="container">
    <div class="col-lg-6">
        <div class="col-lg-12">
            <g:render template="/topic/trendingTopics"></g:render>
        </div>

        <div class="col-lg-12">
            <g:render template="/resource/topPosts"></g:render>

        </div>

    </div>

    <div class="col-lg-6">
        <div class="col-lg-12">
            <div class="col-lg-12">
                <div class=" panel panel-default     ">
                    <div class="panel-heading col-lg-12" style="background: #007efc;">
                        <div class="col-lg-12">
                            <h3 style="color:white">Search For :"${searchObject}"</h3>
                        </div>

                        <div class="col-lg-12">
                            %{--empty--}%
                        </div>

                    </div>
                    <g:each in="${searchResults}" var="topic">
                        <div class="panel-body  ">
                            <div class="col-lg-12">
                                <div class="col-sm-3">
                                    <ls:userImage username="${topic.ownerUsername}" height="100" width="100"/>
                                </div>

                                <div class="col-lg-9">
                                    <div class="col-sm-12">
                                        <br>

                                        <span>${topic.ownerName} &nbsp;&nbsp;&nbsp;&nbsp;<small
                                                class="text-muted">@${topic.ownerUsername}</small>

                                            <a href="${createLink(controller:'topic',action: 'show',id: topic.topicId)}" class="pull-right">${topic.topicName}</a>
                                            <br><br>


                                        </span>

                                        <div>
                                            <i class="fa fa-facebook-square fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-google-plus fa-lg" aria-hidden="true"></i>
                                            <i class="fa fa-twitter-square fa-lg" aria-hidden="true"></i>
                                            <span class="pull-right" style="margin-right: 0px;color: #007efc">
                                                <g:if test="${session.user}">
                                                <a href="#" style="color: #007efc;font-size: 75%">Download</a>
                                                </g:if>
                                                <a href="${createLink(controller:'topic',action: 'show',id: topic.topicId)}" style="color: #007efc;font-size: 75%">View Topic</a>
                                            </span>
                                        </div>
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

</body>
</html>