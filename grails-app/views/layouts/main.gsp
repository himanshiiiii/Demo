<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <g:layoutHead/>

</head>

<body>
<nav class="navbar navbar">
    <div class="container-fluid">
        <div class="col-lg-4">
            <div class="navbar-header">
                <a class="navbar-brand" href="${createLink(controller: 'login', action: 'index')}">LinkSharing</a>
            </div>
        </div>

        <div class="col-lg-8">

            <ul class="nav navbar-nav col-lg-12">
                <li class="col-lg-5 ">
                    <g:form class="navbar-form navbar-left" controller="search" action="index">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search..." name="searchObject">

                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </g:form>
                </li>

                <g:if test="${session.user}">
                    <li class="col-lg-1 " style="padding: 0px"><a href="#myModal" data-toggle="modal"
                                                                  data-target="#myModal"><i class="fa fa-comments"
                                                                                            style="font-size:24px"></i>
                    </a>
                    </li>

                    <g:render template="/topic/createTopic"></g:render>


                    <li class="col-lg-1" style="padding: 0px"><a href="#myModal2" data-toggle="modal"
                                                                 data-target="#myModal2"><i class="fa fa-envelope"
                                                                                            style="font-size:24px"></i>
                    </a>
                    </li>
                    <g:render template="/topic/email"></g:render>

                    <li class="col-lg-1" style="padding: 0px"><a href="#myModal3" data-toggle="modal"
                                                                 data-target="#myModal3"><i class="fa fa-link"
                                                                                            style="font-size:24px"></i>
                    </a>
                    </li>
                    <g:render template="/resource/createLinkResource"></g:render>


                    <li class="col-lg-1" style="padding: 0px"><a href="#myModal4" data-toggle="modal"
                                                                 data-target="#myModal4"><i class="fa fa-file-text"
                                                                                            style="font-size:24px"></i>
                    </a>
                    </li>
                    <g:render template="/resource/createDocumentResource"></g:render>


                    <li class="dropdown  col-lg-3" style="padding: 0px">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-user"
                                                                                      style="font-size:24px"></i>
                            ${session.user.getName()}
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><g:link controller="user" action="editProfile">Profile</g:link></li>
                            <g:if test="${session.user.admin}">
                                <li><g:link controller="user" action="showUserListToAdmin">Users</g:link></li>
                            %{--<li><g:link controller="user" action="showTopics">Topic</g:link></li>--}%
                            %{--<li><g:link controller="resource" action="showPosts">Post</g:link></li>--}%
                            </g:if>
                            <li><g:link controller="login" action="logout">Logout</g:link></li>
                        </ul>
                    </li>
                </g:if>
            </ul>

        </div>

    </div>
</nav>

%{--<g:if test="${session.user == null}">--}%
<g:if test="${flash.error}">

    ${flash.error}

</g:if>
<g:if test="${flash.message}">
    ${flash.message}

</g:if>
%{--</g:if>--}%
<g:layoutBody/>

</body>

</html>
