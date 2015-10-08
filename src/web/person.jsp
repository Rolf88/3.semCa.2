<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <br>
    <div style="float: right" class="form-inline">
        <input type="text" name="search" class="form-control" />
        <div class="btn btn-success">Search on hobby</div>
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
    <div class="btn btn-info">Add new person</div>
</t:wrapper>