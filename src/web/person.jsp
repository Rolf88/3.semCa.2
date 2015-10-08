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
            </tr>
        </thead> 
        <tbody> 
            <tr>
                <td>Test</td>
                <td>Test</td>
                <td>Test</td>
                <td>Test</td>
            </tr>
            <tr>
                <td>Test</td>
                <td>Test</td>
                <td>Test</td>
                <td>Test</td>
            </tr>
        </tbody>
    </table>
    <div class="btn btn-success">Refresh</div>
    <div class="btn btn-info">Add new person</div>
</t:wrapper>