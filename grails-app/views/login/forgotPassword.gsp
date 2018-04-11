<!DOCTYPE html>
<html lang="en">
<head>

    <title></title>
    <meta name="layout" content="main">

</head>

<body>

<div class="col-lg-12 " style="margin-top: 25px">

    <div class=" panel panel-default  ">
        <div class="panel-heading " style="background:#007efc">
            <h3 style="color:white">Forgot Password</h3>
        </div>

        <div class="panel-body ">
            <g:form controller="login" action="forgotPassword" method="post">
                <div class="form-group col-lg-10">
                    <label>Enter Your Username</label>
                    <input class="form-control" type="text" name="username"
                           placeholder="Enter Your Username">
                </div>


                <div class="form-group col-lg-10">
                    <label>Enter New Password</label>
                    <input class="form-control" type="password" name="newPassword"
                           placeholder="Enter Your New Password">

                </div>

                <div class="form-group col-lg-10">
                    <label>Confirm New Password</label>
                    <input class="form-control" type="password" name="confirmNewPassword"
                           placeholder="Confirm Your New Password" required>

                </div>

                <div class="form-group col-lg-10">
                    <input type="submit" class="form-control btn-primary" style="background:#007efc">
                </div>

                <g:if test="${flash.error}">
                    ${flash.error}
                </g:if>

            </g:form>

        </div>
    </div>
</div>
</body>
</html>