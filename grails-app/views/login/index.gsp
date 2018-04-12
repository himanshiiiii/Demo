<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">

</head>

<body>

<div class=" container">
    <div class="col-lg-6">
        <div class="col-lg-12" style="margin-top: 25px">

            <g:render template="/resource/recentShares"></g:render>

        </div>

        <div class="col-lg-12" style="margin-top: 25px">

            <g:render template="/resource/topPosts"></g:render>

        </div>

    </div>

    <div class="col-lg-6">

        <g:render template="login"></g:render>

        <g:render template="register"></g:render>


    </div>

</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>