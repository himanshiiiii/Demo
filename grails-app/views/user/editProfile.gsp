<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.name} Profile</title>
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
                        <div class="col-sm-3">
                            <ls:userImage username="${user.username}" height="100" width="100"/>
                        </div>

                        <div class="col-sm-9">

                            <p>${user.name}<br>
                                <small class="text-muted">@${user.username}</small>
                            </p>

                            <div class="row">

                                <h6 class="text-muted col-sm-6">Subscriptions
                                    <p class="text-primary">${user.subscriptionCount}</p>
                                </h6>

                                <h6 class="text-muted col-sm-6">Topics
                                    <p class="text-primary">${user.resourceCount}</p>
                                </h6>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-12">

            <div class=" panel panel-default  ">
                <div class="panel-heading " style="background:#007efc">
                    <div class="col-lg-4" style="margin-top: 0px">
                        <p style="margin-top: 0px">

                        <h3 style="color:white ;margin-top: 0px">Topic</h3></p>
                    </div>

                    <form class="form-inline">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>

                </div>

                <div class="panel-body ">
                    <div class="col-lg-12">
                        <g:each in="${userTopics}" var="topic">
                            <div class="col-lg-12 row">
                                <div class="col-sm-12">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <ls:userImage username="${topic.ownerUsername}" height="100" width="100"/>
                                        </div>

                                        <div class="col-sm-9">
                                            <g:form class="form-inline" controller="topic" action="changeName">
                                                <div class="form-group">

                                                    <input type="text" class="form-control" name="changedTopicName"
                                                           placeholder="${topic.topicName}">
                                                    <g:hiddenField name="topicId" value="${topic.topicId}"/>
                                                </div>
                                                <button type="submit" class="btn btn-primary ">Save</button>
                                            </g:form>
                                            <br>

                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <h6 class="text-muted">@${topic.ownerUsername}</h6>
                                                </div>

                                                <div class="col-sm-3">
                                                    <h6 class="text-muted pull-left">Subscriptions</h6>
                                                    <h6 class="text-primary">${topic.subscriptionCount}</h6>
                                                </div>

                                                <div class="col-sm-3">
                                                    <h6 class="text-muted  pull-right">Post
                                                        <br>
                                                        <br>

                                                        <p class="text-primary">${topic.resourcesCount}</p>
                                                    </h6>
                                                </div>
                                                <span type="img" class="fa fa-file pull-right fa-2x"
                                                      style="margin-left: 10px;  margin-right: 5px;"></span>
                                                <span type="img" class="fa fa-envelope pull-right fa-2x"
                                                      style="margin-left: 10px;">
                                                </span>
                                                <a href=""
                                                   onclick="return deleteTopic(${topic.topicId})">
                                                <span type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                                                      style="margin-left: 10px;">
                                                </span></a>

                                                <div>
                                                    <select class="pull-right" name="topicVisibility"
                                                            id="topic_Visibility"
                                                            onchange="changeVisibility(${topic.topicId}, this.value)">
                                                        <option class="placeholder" selected disabled
                                                                value="">${topic.topicVisibility}</option>
                                                        <option value="${enumeration.Visibility.PRIVATE}">PRIVATE</option>
                                                        <option value="${enumeration.Visibility.PUBLIC}">PUBLIC</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="col-lg-12"><hr></div>
                        </g:each>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-lg-12">

            <div class=" panel panel-default  ">
                <div class="panel-heading " style="background:#007efc">
                    <div class="col-lg-4" style="margin-top: 0px">
                        <p style="margin-top: 0px">

                        <h3 style="color:white ;margin-top: 0px">Post</h3></p>
                    </div>

                    <form class="form-inline">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>

                </div>

                <div class="panel-body ">
                    <div class="col-lg-12">
                        <g:each in="${userPosts}" var="post">
                            <div class="col-lg-12 row">
                                <div class="col-sm-12">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <ls:userImage username="${post.ownerUsername}" height="100" width="100"/>
                                        </div>

                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="text-muted">@${post.ownerUsername}</h6>
                                            </div>

                                            <div class="col-sm-5">
                                                <a href="${createLink(controller: 'topic', action: 'show', id: post.topicId)}"
                                                   class="pull-right">${post.topicName}</a>
                                            </div>
                                        </div>

                                        <div class="col-sm-9">
                                            <g:form class="form-inline" controller="resource" action="changeDesciption">
                                                <div class="form-group">
                                                    <textarea placeholder="${post.resourceDescription}" rows="4"
                                                              cols="50" name="updatedResourceDescription"></textarea>
                                                    <g:hiddenField name="resourceId" value="${post.resourceId}"/>
                                                </div>
                                                <button type="submit" class="btn btn-primary ">Save</button>
                                            </g:form>
                                            <br>

                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="col-lg-12"><hr></div>
                        </g:each>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div class="col-lg-6">
        <div class="col-lg-12 " style="margin-top: 25px">

            <div class=" panel panel-default  ">
                <div class="panel-heading " style="background:#007efc">
                    <p>

                    <h3 style="color:white">Profile</h3></p>
                </div>

                <div class="panel-body ">
                    <g:uploadForm controller="user" action="updateUser" enctype="multipart/form-data">
                        <div class="form-group col-lg-10">
                            <label>First Name*</label>
                            <input class="form-control" type="text" name="updatedFirstname" required>
                        </div>

                        <div class="form-group col-lg-10">
                            <label>Last Name*</label>
                            <input class="form-control" type="text" name="updateLastname" required>
                        </div>

                        <div class="form-group col-lg-10">
                            <label>Username*</label>
                            <input class="form-control" type="text" name="updatedUsername" required>
                        </div>

                        <div class="col-lg-2"></div>

                        <div class="form-group col-lg-10">
                            <label>Photo</label>
                            <input class="form-control" type="file" name="updatedPhoto">

                        </div>

                        <div class="col-lg-12"></div>

                        <div class="form-group col-lg-10">
                            <input type="submit" class="form-control btn-primary">
                        </div>

                    </g:uploadForm>

                </div>
            </div>
        </div>

        <div class="col-lg-12 " style="margin-top: 25px">

            <div class=" panel panel-default  ">
                <div class="panel-heading " style="background:#007efc">

                    <p>

                    <h3 style="color:white">Change Password</h3></p>
                </div>

                <div class="panel-body ">
                    <g:form controller="user" action="changePassword">
                        <div class="form-group col-lg-10">
                            <label>Password*</label>
                            <input class="form-control" type="text" name="updatedPassword">
                        </div>

                        <div class="form-group col-lg-10">
                            <label>Confirm Password*</label>
                            <input class="form-control" type="password" name="updatedConfirmPassword">
                        </div>


                        <div class="col-lg-2"></div>


                        <div class="col-lg-12"></div>

                        <div class="form-group col-lg-10">
                            <input type="submit" class="form-control btn-primary">
                        </div>

                    </g:form>

                </div>
            </div>
        </div>

    </div>
</div>

</div>


<script>
    function changeVisibility(topicId, value) {
        console.log("inside change")
        console.log("id is : ", topicId)
        console.log("value is : ", value)
        $.ajax({
            type: 'post',
            data: {'id': topicId, 'visibility': value},
            url: '/topic/changeVisibility',
            dataType: 'json',
            success: function (res) {
                alert(res);
            },
            error: function (res) {
                $('#message').text('Error!');
                $('.dvLoading').hide();
            }
        });
    }
</script>

<script>
    function deleteTopic(topicId) {
        var r = confirm("Are you sure to delete?");
        if (r == true) {
            $.ajax({
                url: "/topic/delete",
                type: "POST",
                data: {topicId: topicId},
                success: function (data) {
                    alert(data);
                }
            });
        }
    }
</script>

</body>

</html>