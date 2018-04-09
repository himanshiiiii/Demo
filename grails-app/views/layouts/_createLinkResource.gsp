<%@ page import="linksharingapp.User" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<g:if test="${session.userId}">
    <div id="myModal3" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">create resource</h4>
                </div>

                <div class="modal-body">
                    <g:form class="form-horizontal" url="[controller: 'LinkResource', action: 'save']">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="link">link:</label>

                            <div class="col-sm-10">
                                <input type="url" class="form-control" id="link" placeholder="Enter link"
                                       name="url">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="descrip">description:</label>

                            <div class="col-sm-10">
                                <textarea class="form-control" rows="5" id="descrip" name="description"></textarea>
                            </div>
                        </div>

                        <div class="dropdown">
                            <label class="control-label col-sm-2" for="topicId">topic:</label>
                            <%         linksharingapp.User user = User.findById(session.userId.toLong())    %>
                            <g:select name="topicId" optionKey="id" optionValue="name"  from="${user.subscriptions.topic}"></g:select>
                        </div>
                        <br>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">share</button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </g:form>

                </div>
                <!--<div class="modal-footer">-->
                <!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
                <!--</div>-->
            </div>

        </div>
    </div>
</g:if>
</body>
</html>