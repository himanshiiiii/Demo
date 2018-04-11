<div class="panel panel-primary ">
    <div class="panel-heading"><h3 class="panel-title">
        Subscriptions
        %{--<a href="#" class="pull-right">View All</a>--}%
    </h3></div>

    <div class="panel-body">
        <div class="row">
            <div class="col-sm-12">
                <g:each in="${subscriptionsList}" var="subscribedTopics">
                    <div class="row">
                        <div class="col-sm-3">
                            <ls:userImage username="${subscribedTopics.ownerUsername}" height="100" width="100"/>
                        </div>

                        <div class="col-sm-9">
                            <a href="${createLink(controller: "topic", action: "show", id: subscribedTopics.topicId)}"
                               class="pull-left">${subscribedTopics.topicName}</a>
                            <br>

                            <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-muted">@${subscribedTopics.ownerUsername}</h6>
                                    <g:if test="${subscribedTopics.ownerUsername != session.user.username}">
                                        <g:link controller="subscription" action="delete"
                                                params="${[subscriptionId: subscribedTopics.subscriptionId]}">Unsubscribe</g:link>
                                    </g:if>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted pull-left">Subscriptions</h6>
                                    <h6 class="text-primary">${subscribedTopics.subscriptionCount}</h6>
                                </div>

                                <div class="col-sm-3">
                                    <h6 class="text-muted  pull-right">Post
                                        <br>
                                        <br>

                                        <p class="text-primary">${subscribedTopics.resourcesCount}</p>
                                    </h6>
                                </div>
                                <g:if test="${subscribedTopics.ownerUsername == session.user.username}">
                                    <a href=""
                                       onclick="return deleteSubscription(${subscribedTopics.subscriptionId})"><span
                                            type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                                            style="margin-left: 10px;color: #007efc;"></span></a>
                                    <a href="#"><span type="img" class="fa fa-file pull-right fa-2x"
                                                      style="margin-left: 10px;  margin-right: 5px;color: #007efc;"></span>
                                    </a>
                                </g:if>
                                <a href="#"><span type="img" class="fa fa-envelope pull-right fa-2x"
                                                  style="margin-left: 10px;color: #007efc;"></span></a>

                                <select class="pull-right" name="updatedSeriousness" id="updateSeriousness"
                                        onchange="changeSeriousness(${subscribedTopics.subscriptionId}, this.value)">
                                    <option class="placeholder" selected disabled
                                            value="">${subscribedTopics.subscriptionSeriousness}</option>
                                    <option value="${enumeration.Seriousness.VERYSERIOUS}">Very Serious</option>
                                    <option value="${enumeration.Seriousness.SERIOUS}">Serious</option>
                                    <option value="${enumeration.Seriousness.CASUAL}">Casual</option>
                                </select>

                                <g:if test="${subscribedTopics.ownerUsername == session.user.username}">
                                    <div>
                                        <select class="pull-right" name="updatedVisibility" id="updateVisibility"
                                                onchange="changeVisibility(${subscribedTopics.topicId}, this.value)">
                                            <option class="placeholder" selected disabled
                                                    value="">${subscribedTopics.topicVisibility}</option>
                                            <option value="${enumeration.Visibility.PRIVATE}">PRIVATE</option>
                                            <option value="${enumeration.Visibility.PUBLIC}">PUBLIC</option>
                                        </select>
                                    </div>
                                </g:if>
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
<script>
    function deleteSubscription(subscriptionId) {
        var r = confirm("Are you sure to delete?");
        if (r == true) {
            $.ajax({
                url: "/subscription/delete",
                type: "POST",
                data: {subscriptionId: subscriptionId},
                success: function (data) {
                    alert(data);
                }
            });
        }
    }
</script>