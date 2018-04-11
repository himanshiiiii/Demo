<%@ page import="domainLinksharing.Resource" %>
<div class="panel panel-primary ">
    <div class="panel-heading">
        <h3 class="panel-title">
            Trending topics
        </h3>
    </div>

    <g:each in="${trendingTopicsList}" var="trendingTopics">
        <div class="panel-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-3">
                            <ls:userImage username="${trendingTopics.ownerUsername}" height="100" width="100"/>
                        </div>

                        <div class="col-sm-9">
                            <a href="${createLink(controller: 'topic', action: 'show', id: trendingTopics.topicId)}"
                               class="pull-left">${trendingTopics.topicName}</a>
                            <br>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-muted">@${trendingTopics.ownerName}</h6>
                                    <g:if test="${session.user}">
                                        <g:if test="${trendingTopics.subscriptionId && trendingTopics.ownerUsername != session.user.username}">
                                            <g:link controller="subscription" action="delete"
                                                    params="${[subscriptionId: trendingTopics.subscriptionId]}">Unsubscribe</g:link>
                                        </g:if>
                                        <g:elseif
                                                test="${trendingTopics.subscriptionId && trendingTopics.ownerUsername == session.user.username}">
                                        </g:elseif>
                                        <g:else>
                                            <g:link controller="subscription" action="save"
                                                    params="${[topicId: trendingTopics.topicId]}">Subscribe</g:link>
                                        </g:else>
                                    </g:if>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted pull-left">Subscriptions</h6>
                                    <h6 class="text-primary">${trendingTopics.subscriptionCount}</h6>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted  pull-right">Post
                                        <br>
                                        <br>

                                        <p class="text-primary">${trendingTopics.resourcesCount}</p>
                                    </h6>
                                </div>
                                <g:if test="${session.user}">
                                    <g:if test="${trendingTopics.ownerUsername == session.user.username || session.user.admin}">
                                        <a href=""
                                           onclick="return deleteSubscription(${trendingTopics.subscriptionId})">
                                            <span type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                                                  style="margin-left: 10px;color: #007efc;"></span></a>
                                        <a href="#"><span type="img" class="fa fa-file pull-right fa-2x"
                                                          style="margin-left: 10px;  margin-right: 5px;color: #007efc;"></span>
                                        </a>
                                    </g:if>
                                    <a href="#"><span type="img" class="fa fa-envelope pull-right fa-2x"
                                                      style="margin-left: 10px;color: #007efc;"></span></a>

                                    <select class="pull-right" name="subscriptionSeriousness"
                                            id="subscriptionSeriousness"
                                            onchange="changeSeriousness(${trendingTopics.subscriptionId}, this.value)">
                                        <option class="placeholder" selected disabled
                                                value="">${trendingTopics.subscriptionSeriousness}</option>
                                        <option value="${enumeration.Seriousness.VERYSERIOUS}">Very Serious</option>
                                        <option value="${enumeration.Seriousness.SERIOUS}">Serious</option>
                                        <option value="${enumeration.Seriousness.CASUAL}">Casual</option>
                                    </select>


                                    <g:if test="${trendingTopics.ownerUsername == session.user.username || session.user?.admin}">
                                        <div>
                                            <select class="pull-right" name="topicVisibility"
                                                    id="topic_Visibility"
                                                    onchange="changeVisibility(${trendingTopics.topicId}, this.value)">
                                                <option class="placeholder" selected disabled
                                                        value="">${trendingTopics.topicVisibility}</option>
                                                <option value="${enumeration.Visibility.PRIVATE}">Private</option>
                                                <option value="${enumeration.Visibility.PUBLIC}">Public</option>
                                            </select>
                                        </div>
                                    </g:if>
                                </g:if>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <div class="col-lg-12">
            <hr>
        </div>
    </g:each>
</div>