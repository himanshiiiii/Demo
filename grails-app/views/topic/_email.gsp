<div id="myModal2" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" controller="invitation" action="sendMail">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Email</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" name="recieverEmail">
                        </div>
                    </div>
                    <div class="dropdown">
                        <label class="control-label col-sm-2">Topic:</label>

                      %{--  <button class="btn btn-default dropdown-toggle" type="button"
                                data-toggle="dropdown" name="topicInvitation">Topic
                         --}%  %{-- <span class="caret"></span></button>--}%
                        <div class="col-sm-10">
                        <select class="form-control" name="topicName">
                            <g:each in="${session.user.getUserTopics()}" var="topicList">
                                <option value="${topicList}">${topicList}</option>
                            </g:each>
                        </select>
                    </div>
                    </div>

                    <br/>
<br/>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Share</button>
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">Close</button>

                        </div>
                    </div>
                </g:form>

            </div>

        </div>

    </div>
</div>
</div>