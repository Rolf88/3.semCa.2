<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <h1 class="page-header">AJAX Examples</h1>

    <table class="table">
        <tr>
            <td>
                <div>
                    <h4>Get all persons contact info</h4>
                </div>
            </td>
            <td>
                <h4>AJAX Method</h4>
                <pre>
$.ajax({
    type: "GET",
    url: "/3.semCa.2/api/person/contactinfo"
}).done(function (persons) {
    console.log(persons);
}).fail(function (errorData) {
    console.log(errorData);
});</pre>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Create a new person</h4>
                </div>
            </td>
            <td>
                <h4>AJAX Method</h4>
                <pre>
$.ajax({
    method: "POST",
    contentType: "application/json",
    url: "/3.semCa.2/api/person",
    data: JSON.stringify(
    {
        firstName: $("#firstnameinput").val(),
        lastName: $("#lastnameinput").val(),
        email: $("#emailinput").val(),
        phones: [{number: $("#phoneinput").val()}],
        address: 
            {
            street: $("#adressinput").val(),
            additionalInfo: "",
            city: 
            {
                city: $("#cityinput").val(),
                zip: $("#zipcodeinput").val()
            }
        },
        hobbies: 
        [
            {
                name: $("#hobbyinput").val(),
                description: ""
            }
        ]
    })
});</pre>
            </td>
        </tr>
        <tr>
            <td>
                <div>
                    <h4>Update a person</h4>
                </div>
            </td>
            <td>
                <h4>AJAX Method</h4>
                <pre>
$.ajax({
    method: "PUT",
    contentType: "application/json",
    url: "/3.semCa.2/api/person",
    data: JSON.stringify(
    {
        id: 1000,
        firstName: $("#firstnameinput").val(),
        lastName: $("#lastnameinput").val(),
        email: $("#emailinput").val(),
        phones: [{number: $("#phoneinput").val()}],
        address: 
        {
            street: $("#adressinput").val(),
            additionalInfo: "",
            city: 
            {
                city: $("#cityinput").val(),
                zip: $("#zipcodeinput").val()
            }
        },
        hobbies: 
        [
            {
                name: $("#hobbyinput").val(),
                description: ""
            }
        ]
    })
});</pre>
            </td>
        </tr>
    </table>
</t:wrapper>