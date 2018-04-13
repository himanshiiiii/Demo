<div class="panel-body">
    <div class="row">
        <div class="col-sm-12">
            <div class="row">
                <div class="col-sm-3 fa fa-user fa-5x">
                </div>

                <div class="col-sm-9">
                    <a href="#" class="pull-left">${userTopics.topic.name}</a>
                    <br>

                    <div class="row">
                        <div class="col-sm-6">
                            <h6 class="text-muted">@${userTopics.topic.createdBy.username}</h6>
                            <a href="#">Unsubscribe</a>
                        </div>

                        <div class="col-sm-3">
                            <h6 class="text-muted pull-left">Subscriptions</h6>
                            <h6 class="text-primary">${userTopics.topic.getSubscriptionCount()}</h6>
                        </div>

                        <div class="col-sm-3">
                            <h6 class="text-muted  pull-right">Post
                                <br>
                                <br>

                                <p class="text-primary">${userTopics.topic.resources.size()}</p>
                            </h6>
                        </div>
                        <span type="img" class="glyphicon glyphicon-trash pull-right fa-2x"
                              style="margin-left: 10px;color: #007efc;"></span>
                        <span type="img" class="fa fa-file pull-right fa-2x"
                              style="margin-left: 10px;  margin-right: 5px;color: #007efc;"></span>
                        <span type="img" class="fa fa-envelope pull-right fa-2x"
                              style="margin-left: 10px;color: #007efc;"></span>

                        <select class="pull-right">
                            <option>Serious</option>
                            <option>Casual</option>
                            <option>Very Serious</option>
                        </select>

                        <div>
                            <select class="pull-right">
                                <option>Private</option>
                                <option>Public</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>