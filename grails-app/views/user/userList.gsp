<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta name="layout" content="main">

</head>

<body>
<div class="container">
    <div class="col-lg-12">
        <g:each in="${allUsers}" var="user">
            <div class="panel panel-primary col-lg-12">
                <div class="panel-body">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-3">
                        <ls:userImage username="${user.username}" height="100" width="100"/>
                        </div>

                            <div class="col-sm-9">

                                <p>${user.name}<br>
                                    <small class="text-muted">@${user.username}</small>
                                </p>

                                <div class="row">

                                    <h6 class="col-sm-4">Subscriptions
                                        <p class="text-primary">${user.subscriptionCount}</p>
                                    </h6>

                                    <h6 class="col-sm-4">Topics
                                        <p class="text-primary">${user.resourceCount}</p>
                                    </h6>

                                    <h6 class="col-sm-4">Active
                                        <p class="text-primary"><a
                                                href="${createLink(controller: 'user', action: 'changeState', id:
                                                        user.userId
                                                )}">${user.active.toString().toUpperCase()}</a></p>
                                    </h6>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </g:each>

    </div>

</div>

</body>
</html>