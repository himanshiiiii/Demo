<%@ page import="linksharingapp.User" %>
<!doctype html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:stylesheet href="application.css"></asset:stylesheet>
    <asset:javascript src="application.js"></asset:javascript>
    <asset:stylesheet src="menu.css"></asset:stylesheet>

    <title></title>
    <g:layoutHead/>
</head>

<body>

<g:if test="${!session.userId}">
    <div class="container">
        <div class="page-header well">
            <span>

                <a class="text-primary" href="#" style="text-decoration: underline">Link Sharing</a>

            </span>
            <g:form class="search" url="[controller:'resource',action:'searchResource']">

                <span><i class="fa fa-search"></i></span>
                <input id="inputSearch" placeholder="Search term" name="searchKey" for="aLink">
                <span><i class=" fa fa-times"></i></span>
            </g:form>

        </div>
    </div>
</g:if>
<g:else>
    <header class="page-header well" style="padding-bottom: 50px">
        <div class="col-lg-4">
            <span>

                <a class="text-primary" href="#" style="text-decoration: underline">Link Sharing</a>

            </span>
        </div>

        <div class="col-lg-8">
            <div class="col-lg-5">
                <g:form class="search" url="[controller:'resource',action:'searchResource']">

                    <span><i class="fa fa-search"></i></span>
                    <input id="inputSearch" placeholder="Search term" name="searchKey" for="aLink">
                    <span><i class=" fa fa-times"></i></span>
                </g:form>
            </div>
            <span class="col-lg-1">
                <a href="#myModal" data-toggle="modal" data-target="#myModal">
                    <i class="fas fa-comment" style="font-size:24px"></i>
                </a>
            </span>
            <g:render template="/layouts/createTopic"></g:render>


            <span class="col-lg-1">
                <a href="#myModal2" data-toggle="modal" data-target="#myModal2">
                    <i class="far fa-envelope" style="font-size:24px"></i>
                </a>
            </span>

            <g:render template="/layouts/invitationTopic"></g:render>


            <span class="col-lg-1">
                <a href="#myModal3" data-toggle="modal" data-target="#myModal3">
                    <i class="fas fa-link" style="font-size:24px"></i>
                </a>
            </span>

            <g:render template="/layouts/createLinkResource"></g:render>

            <span class="col-lg-1">
                <a href="#myModal4" data-toggle="modal" data-target="#myModal4">
                    <i class="far fa-file" style="font-size:24px"></i>
                </a>
            </span>

            <g:render template="/layouts/createDocumentResource"></g:render>

            <span class="col-lg-3 dropdown">

                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="fas fa-user" style="font-size:24px"></i>
                    <%         linksharingapp.User user = linksharingapp.User.findById(session.userId.toLong())    %>
                    ${user.userName}
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">profile</a></li>
                    <li><a href="#">users</a></li>
                    <li><a href="#">Topic</a></li>
                    <li><a href="#">post</a></li>
                    <li><a href="${createLink(controller: 'logIn', action: 'logOut')}">logout</a></li>
                </ul>
            </span>

        </div>
    </header>
</g:else>
<g:if test="${flash.error}">
    <h6>${flash.error}</h6>
</g:if>
<g:if test="${flash.message}">
    <h6>${flash.message}</h6>
</g:if>
<g:layoutBody/>

</body>
</html>
