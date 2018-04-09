<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<g:if test="${session.userId}">
    <div id="myModal2" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">send invitation</h4>
                </div>

                <div class="modal-body">
                    <form class="form-horizontal" action="#">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="email1">email</label>

                            <div class="col-sm-10">
                                <input type="email" class="form-control" id="email1" name="email1">
                            </div>
                        </div>

                        <div class="dropdown">
                            <label class="control-label col-sm-2" for="topicId">topic:</label>
                            <%         linksharingapp.User user = linksharingapp.User.findById(session.userId.toLong())    %>
                            <g:select name="topicId" optionKey="id" optionValue="name"  from="${user.subscriptions.topic}"></g:select>
                        </div>

                        <br>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">share</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                            </div>
                        </div>
                    </form>

                </div>

            </div>

        </div>
    </div>
</g:if>
</body>
</html>