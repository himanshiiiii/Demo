<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">
    <asset:link rel="stylesheet" href="application.css"></asset:link>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

    <style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:500,100,300,700,400);
    </style>

</head>

<body>
<div class="container">
    <div class="col-lg-7">

        <div class="col-lg-12">
            <div class="col-lg-12">
                <div class=" panel panel-default     ">

                    <div class="panel-body  ">
                        <div class="col-lg-12">
                            <div class="col-lg-3" style="margin-top: 25px">
                                <i class="fa fa-user-circle fa-5x" aria-hidden="true"></i>

                            </div>

                            <div class="col-lg-9">
                                <div class="col-sm-12">
                                    <br>

                                    <div class="col-lg-5">
                                        <span>${resource.ownerName} <small
                                                class="text-muted">@${resource.ownerUsername}</small>
                                        </span>

                                    </div>

                                    <div class="col-lg-7" style="text-align: right">
                                        <p><a href="${createLink(controller: "topic", action: "show", id: resource.topicId)}"
                                              class="pull-right">${resource.topicName}</a></p><br>
                                    </div>

                                    <g:if test="${session.user}">
                                        <div class="stars">
                                            <g:form controller="resource" action="storeRating" method="post">
                                                <input class="star star-5" id="star-5" type="radio" name="star"
                                                       value="5"/>
                                                <label class="star star-5" for="star-5"></label>
                                                <input class="star star-4" id="star-4" type="radio" name="star"
                                                       value="4"/>
                                                <label class="star star-4" for="star-4"></label>
                                                <input class="star star-3" id="star-3" type="radio" name="star"
                                                       value="3"/>
                                                <label class="star star-3" for="star-3"></label>
                                                <input class="star star-2" id="star-2" type="radio" name="star"
                                                       value="2"/>
                                                <label class="star star-2" for="star-2"></label>
                                                <input class="star star-1" id="star-1" type="radio" name="star"
                                                       value="1"/>
                                                <label class="star star-1" for="star-1"></label>
                                                <g:hiddenField name="id" value="${resource.resourceId}" />
                                                <input type="submit" value="Rate">
                                            </g:form>
                                        </div>
                                    </g:if>

                                    <div class="col-lg-12"><br><br></div>


                                    <div class="col-lg-12">
                                        <div class="col-lg-12">
                                            <p>${resource.resourceDescription}</p>
                                        </div>
                                    </div>

                                    <div class="col-lg-12"><br></div>

                                </div>

                            </div>

                            <div class="col-lg-12" style="text-align: ">
                                <i class="fa fa-facebook-square fa-lg" aria-hidden="true"></i>
                                <i class="fa fa-google-plus fa-lg" aria-hidden="true"></i>
                                <i class="fa fa-twitter-square fa-lg" aria-hidden="true"></i>
                                <span class="pull-right" style="margin-right: 0px;color: #007efc">
                                    <g:if test="${session.user}">
                                        <a href="#" style="color: #007efc;font-size: 90%">Download</a>
                                    </g:if>
                                </span>
                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </div>

    </div>

    <div class="col-lg-5">
        <g:render template="/topic/trendingTopics"></g:render>
    </div>
</div>

</body>
</html>