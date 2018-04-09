<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>

<body>
<g:if test="${session.userId}">
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">create topic</h4>
                </div>

                <div class="modal-body">
                    <g:form class="form-horizontal" url="[controller: 'Topic', action: 'save']">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="name1">name</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name1" name="topic">
                            </div>
                        </div>

                        <div class="dropdown">
                            <label class="control-label col-sm-2" for="visibility1">visibility:</label>

                            <select class="form-select-button" id="visibility1" name="visibility">
                                <option value="public" >public</option>
                                <option value="private">private</option>
                            </select>
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