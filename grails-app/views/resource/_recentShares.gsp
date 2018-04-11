<div class=" panel panel-default     ">
    <div class="panel-heading " style="background: #007efc">
        <p>

        <h3 style="color:white">Recent Shares</h3></p>
    </div>

    <div class="panel-body  ">
        <div class="col-lg-12">
            <g:each in="${recentSharesList}" var="recentShares">
                <div class="col-sm-3">
                    <ls:userImage username="${recentShares.ownerUsername}" height="100" width="100"/>
                </div>

                <div class="col-lg-9">

                    <p>${recentShares.ownerName}  <span
                            style="color: darkgray">@${recentShares.ownerUsername}</span><span
                            class="pull-right"
                            style="margin-right: 0px;color: #007efc;font-size: small"><a href="${createLink(controller: 'topic', action: 'show',id: recentShares.topicId)}">${recentShares.topicName}</a></span>
                    </p>

                    <p>${recentShares.resourceDescription}</p>

                    <i class="fa fa-facebook-official fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-google fa-lg" aria-hidden="true"></i>
                    <i class="fa fa-twitter fa-lg" aria-hidden="true"></i>
                    <span class="pull-right" style="margin-right: 0px;color: #007efc">
                        <a href="${createLink(controller:'resource',action:'showPost',id:recentShares.resourceId)}">View Post</a>
                    </span>
                </div>
                <div class="col-lg-12">
                    <hr>
                </div>
            </g:each>
        </div>

    </div>
</div>