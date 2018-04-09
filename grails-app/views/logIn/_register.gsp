<%--
  Created by IntelliJ IDEA.
  User: Himanshi
  Date: 3/4/18
  Time: 4:50 PM
--%>

<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading" style="background: salmon">
        Sign Up
    </div>

    <div class="panel-body">
        <g:form class="form-horizontal" method="post" enctype="multipart/form-data"
                url="[controller: 'dummy', action: 'save']">
            <div class="form-group">
                <label class="control-label col-lg-4">First Name</label>

                <div class="col-lg-8">
                    <input type="text" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Last Name</label>

                <div class="col-lg-8">
                    <input type="text" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Email</label>

                <div class="col-lg-8">
                    <input type="text" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Username</label>

                <div class="col-lg-8">
                    <input type="text" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Password</label>

                <div class="col-lg-8">
                    <input type="password" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4">Confirm Password</label>

                <div class="col-lg-8">
                    <input type="password" class="form-control">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-4" for="document">Photo</label>

                <div class="col-sm-8">
                    <input type="file" class="form-control" id="document" name="avatar">
                </div>
            </div>

            <button type="submit" class="btn btn-primary" style="float: right">Register</button>

        </g:form>
    </div>
</div>

</body>
</html>