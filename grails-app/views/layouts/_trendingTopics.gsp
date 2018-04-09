<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<div class="panel-body">
    <g:if test="${unSubscribedTrendingTopics1}">
        <div class="row" style="padding-bottom:10px">
            <div class="col-lg-2">
                <img src="#" class="img-responsive">
            </div>

            <div class="col-lg-10">
                <div class="text-primary">
                    <a class="hyperlink"
                       href="${createLink(controller: 'topic', action: 'show', id: unSubscribedTrendingTopics1.id)}">${unSubscribedTrendingTopics1.name}</a>
                </div>

                <div class="col-lg-4" style="padding-left: 0px">
                    <div class="text-muted">@${unSubscribedTrendingTopics1.createdBy.userName}</div>
                    %{--<g:if test="${session.user.isSubscribed(unSubscribedTrendingTopics1.id)}">--}%
                        <a href="#" class="hyperlink"><ls:checkSubscribed topicId="${unSubscribedTrendingTopics1.id}"></ls:checkSubscribed> </a>
                    %{--</g:if>--}%
                </div>

                <div class="col-lg-4">
                    <div class="text-muted">Subscriptions</div>
                    <div class="text-primary"><ls:subscriptionCount topicId="${unSubscribedTrendingTopics1.id}"></ls:subscriptionCount> </div>
                </div>

                <div class="col-lg-2">
                    <div class="text-muted">Posts</div>
                    <div class="text-primary"><ls:resourceCount topicId="${unSubscribedTrendingTopics1.id}"></ls:resourceCount> </div>
                </div>
            </div>
        </div>
    </g:if>

    <g:if test="${subscribedTrendingTopics1}">
        <div class="row">

            <div class="col-lg-2">
                <img src="#" class="img-responsive">
            </div>

            <div class="col-lg-10">
                <g:form url="[controller: 'topic', action: 'show']">
                    <div class="col-lg-6" style="padding-left: 0px">
                        <input type="text" placeholder="Grails" name="name">
                    </div>
                </g:form>
                <g:form url="[controller: 'subscription', action: 'save', id: subscribedTrendingTopics1.id]">
                    <button type="submit">save</button>
                </g:form>

                <div class="text-primary">
                    <a class="hyperlink"
                       href="${createLink(controller: 'topic', action: 'show', id: subscribedTrendingTopics1.id)}">${subscribedTrendingTopics1.name}</a>
                </div>

                <div class="col-lg-7" style="padding-left: 0px">
                    <div class="text-muted">@${subscribedTrendingTopics1.createdBy.userName}</div>
                        <a href="${createLink(controller: 'subscription',action: 'delete', id: subscribedTrendingTopics1.id)}" class="hyperlink"><ls:checkSubscribed topicId="${subscribedTrendingTopics1.id}"></ls:checkSubscribed> </a>
                </div>

                <div class="col-lg-3">
                    <div class="text-muted">Subscriptions</div>

                    <div class="text-primary"><ls:subscriptionCount topicId="${subscribedTrendingTopics1.id}"></ls:subscriptionCount></div>
                </div>

                <div class="col-lg-2">
                    <div class="text-muted">Posts</div>

                    <div class="text-primary"><ls:resourceCount topicId="${subscribedTrendingTopics1.id}"></ls:resourceCount> </div>
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
            <a href="#">
                <i class="far fa-envelope fa-2x"></i>
            </a>
            <a href="#">
                <i class="far fa-file fa-2x"></i>
            </a>
            <a href="#">
                <i class="far fa-trash-alt fa-2x"></i>
            </a>
        </div>
    </g:if>
</div>
</body>
</html>