<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 3/4/18
  Time: 4:52 PM
--%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading" style="background: salmon">
        Login
    </div>

    <div class="panel-body">
        <g:form class="form-horizontal" url="[controller: 'logIn', action: 'logInHandler']">
            <div class="form-group">
                <label class="control-label col-lg-4">Name</label>

                <div class="col-lg-8">
                    <input type="text" class="form-control" name="userName"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Password</label>

                <div class="col-lg-8">
                    <input type="password" class="form-control" name="password">
                </div>
            </div>

            <div class="col-lg-offset-2">

                <a href="#forgotPassord" data-toggle="modal" data-target="#forgotPassword" style="text-decoration: underline">
                    forgot Password
                </a>

                <g:render template="forgotPassword"></g:render>

                <button type="submit" class="btn btn-primary pull-right">Login</button>
            </div>
        </g:form>
    </div>
</div>
</body>
</html>