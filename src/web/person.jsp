<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <br>
    <div style="float: right" class="form-inline">
        <input type="text" name="search" class="form-control" />
        <div id="search" class="btn btn-success">Search on hobby</div>
    </div>
    <table id="personTable" class="table table-striped">
        <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>Phone</th>
                <th></th>
                <th></th>
            </tr>
        </thead> 
        <tbody> 
        </tbody>
    </table>
    <div class="btn btn-success" id="refresh">Refresh</div>
    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addPersonModal">Add new person</button>
    <div class="modal fade" id="addPersonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <form id="myform" name="myForm" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="label label-default">Firstname</label>
                            <input class="form-control" type="text" id="firstnameinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Lastname</label>
                            <input class="form-control" type="text" id="lastnameinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Email</label>
                            <input class="form-control" type="text" id="emailinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">PhoneNumber</label>
                            <input class="form-control" type="text" id="phoneinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Adress</label>
                            <input class="form-control" type="text" id="adressinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">City</label>
                            <input class="form-control" type="text" id="cityinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Zipcode</label>
                            <input class="form-control" type="text" id="zipcodeinput">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Hobby</label>
                            <input class="form-control" type="text" id="hobbyinput">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="addPersonBtn" type="submit" class="btn btn-success">Save Person</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade" id="updatePersonModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel1">Modal title</h4>
                </div>
                <form id="myupdateform" name="myupdateForm" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="label label-default">Firstname</label>
                            <input class="form-control" type="text" id="updatefirstname">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Lastname</label>
                            <input class="form-control" type="text" id="updatelastname">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Email</label>
                            <input class="form-control" type="text" id="updateemail">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">PhoneNumber</label>
                            <input class="form-control" type="text" id="updatephone">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Adress</label>
                            <input class="form-control" type="text" id="updateadress">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">City</label>
                            <input class="form-control" type="text" id="updatecity">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Zipcode</label>
                            <input class="form-control" type="text" id="updatezipcode">
                        </div>
                        <div class="form-group">
                            <label class="label label-default">Hobby</label>
                            <input class="form-control" type="text" id="updatehobby">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="updatePersonBtn" type="submit" class="btn btn-success">Update Person</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</t:wrapper>