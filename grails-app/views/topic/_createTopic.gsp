<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">


        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create Topic</h4>
            </div>
            <div class="modal-body row">
                <g:form controller="topic" action="save">
                    <div class="form-group col-lg-12">
                        <label class="control-label col-sm-2">Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="topicName">
                        </div>
                    </div>


                    <div class="dropdown col-lg-12" >
                        <div class="col-lg-2">
                            <label class="control-label ">Visibility:</label>

                        </div>
                        <div class="col-lg-10">
                            <select class="form-control " name="topicVisibility">
                                <option value="${enumeration.Visibility.PUBLIC}">Public</option>
                                <option value="${enumeration.Visibility.PRIVATE}">Private</option>
                            </select>
                        </div>


                    </div>
                    <br><br>



                    <div class="form-group">
                        <br><br>
                        <div class="col-sm-4"></div>
                        <div class=" col-sm-4">
                            <br><br>

                            <button type="submit" class="btn btn-default">Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                        </div>
                        <div class="col-sm-4"></div>
                    </div>
                </g:form>



            </div>
        </div>

    </div>
</div>