$(document).ready(function () {
    $("").click(function () {
        $.ajax({
            type: "GET",
            url: "3.semCa.2/api/person/contactinfo"
        }).done(function (dataFromServer) {
            var personRows = dataFromServer.map(function (person) {
                return Utils.fillInTable(person);
            });
            $("").html(personRows);
        }).fail(function (errorData) {
            alert(errorData);
        });
    });
});

var Utils = {
    fillInTable: function (person) {

        return $(document.createElement("tr"))
                .data({id: person.id})
                .append($(document.createElement("td")).text(person.firstName))
                .append($(document.createElement("td")).text(person.lastName))
                .append($(document.createElement("td")).text(person.email))               
                .append($(document.createElement("td")).text(person.phones))               
                .append(
                        $(document.createElement("td")).append(
                        $(document.createElement("a")).attr({href: "#delete"}).text("delete"))
                        )
                .append($(document.createElement("td")).append($(document.createElement("a")).attr({href: "#update"}).text("edit"))
                        );
    }
};