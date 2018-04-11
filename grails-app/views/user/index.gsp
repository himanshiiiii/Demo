<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">
    <asset:javascript src="jquery-2.2.0.min.js"/>

</head>

<body>

<div class="container">
    <div class="col-lg-6">
        <div class="panel panel-primary col-lg-12">
            <div class="panel-body">
                <div class="col-sm-12">
                    <div class="row">
                        %{--<div class="col-sm-3 fa fa-user fa-5x">--}%
                        %{--</div>--}%
                        <div class="col-sm-3">
                            <ls:userImage username="${session.user.username}" height="100" width="100"/>
                        </div>
                        %{--<img src="${createLink(controller: 'user', action: 'fetchUserImage', params: [username: session.user.username])}"/>--}%
                        <div class="col-sm-9">

                            <p>${session.user.getName()}<br>
                                <small class="text-muted">@${session.user.username}</small>
                            </p>

                            <div class="row">

                                <h6 class="text-muted col-sm-6">Subscriptions
                                    <p class="text-primary">${session.user.getSubscriptionCount()}</p>
                                </h6>

                                <h6 class="text-muted col-sm-6">Topics
                                    <p class="text-primary">${session.user.getTopicCount()}</p>
                                </h6>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <br><br><br><br>

        <div class="col-lg-12"><br></div>
        <br><br><br><br>

        <g:render template="subscriptions"></g:render>

        <br>

        <div class="col-lg-12"><br></div>
        <br>

        <g:render template="/topic/trendingTopics"></g:render>

    </div>

    <g:render template="/user/inbox"></g:render>

</div>

<script>
    function changeVisibility(topicId,value) {
        console.log("inside change")
        console.log("id is : ", topicId)
        console.log("value is : ", value)
        $.ajax({
            type: 'post',
            data: {'id': topicId, 'visibility': value},
            url: '/topic/changeVisibility',
            dataType: 'json',
            success: function(res){
                alert(res);
            },
            error: function(res){
                $('#message').text('Error!');
                $('.dvLoading').hide();
            }
        });
    }
</script>

<script>
    function changeSeriousness(subscriptionId,value) {
        console.log("inside change")
        console.log("id is : ", subscriptionId)
        console.log("value is : ", value)
        $.ajax({
            type: 'post',
            data: {'id': subscriptionId, 'seriousness': value},
            url: '/subscription/changeSeriousness',
            dataType: 'json',
            success: function(res){
                alert(res);
            },
            error: function(res){
                $('#message').text('Error!');
                $('.dvLoading').hide();
            }
        });
    }
</script>

<script>
    function deletecontact(id) {
        var r = confirm("Are you sure to delete?");
        if (r == true) {
            $.ajax({
                url: "/deleteContact",
                type: "POST",
                data: {id: id},
                success: function (data) {
                    alert(data);
                }

            });
        }
    }
</script>

</body>
</html>