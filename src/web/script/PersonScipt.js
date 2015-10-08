$(document).ready(function () {
    getPerson();
    $("#refresh").click(function () {
        getPerson();
    });

    $("#search").click(function () {
        getPersonByHobby();
    });

    $("#personTable").on("click", "a[href='#delete']", function (e) {
        var $this = $(this),
                $container = $this.parents("tr").first(),
                personid = $container.data("id");

        $.ajax({
            method: "DELETE",
            contentType: "application/json",
            url: "/3.semCa.2/api/person/" + personid
        });

        e.preventDefault();
        getPerson();
    });
});

var Utils = {
    fillInTable: function (person) {
        var phoneNumb = "";

        for (var i = 0, max = person.phones.length; i < max; i++) {
            phoneNumb += ", " + person.phones[i].number;
        }
        phoneNumb = phoneNumb.substring(2);

        return $(document.createElement("tr"))
                .data({id: person.id})
                .append($(document.createElement("td")).text(person.firstName))
                .append($(document.createElement("td")).text(person.lastName))
                .append($(document.createElement("td")).text(person.email))
                .append($(document.createElement("td")).text(phoneNumb))
                .append(
                        $(document.createElement("td")).append(
                        $(document.createElement("a")).attr({href: "#delete"}).text("delete"))
                        )
                .append($(document.createElement("td")).append($(document.createElement("a")).attr({href: "#update"}).text("edit"))
                        );
    }
};

function getPerson() {
    $.ajax({
        type: "GET",
        url: "/3.semCa.2/api/person/contactinfo"
    }).done(function (dataFromServer) {
        var personRows = dataFromServer.map(function (person) {
            return Utils.fillInTable(person);
        });
        $("#personTable tbody").html(personRows);
    }).fail(function (errorData) {
        console.log(errorData);
    });
}

function getPersonByHobby() {
    $.ajax({
        type: "GET",
        url: "/3.semCa.2/api/person/contactinfo/hobby/" + $("input[name=search]").val()
    }).done(function (dataFromServer) {
        var personRows = dataFromServer.map(function (person) {
            return Utils.fillInTable(person);
        });
        $("#personTable tbody").html(personRows);
    }).fail(function (errorData) {
        console.log(errorData);
    });
}

function addNewPerson() {
    $("#myform").submit(function (e) {
        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "/3.semCa.2/api/person",
            data: JSON.stringify({firstName: $("#firstnameinput").val(),
                lastName: $("#lastnameinput").val(),
                email: $("#emailinput").val(),
                phones: [{number: $("#phoneinput").val()}],
                adress: $("#adressinput").val(),
                city: $("#cityinput").val(),
                zipcode: $("#zipcodeinput").val(),
                hobby: $("#hobbyinput").val()
            })

        });
        e.preventDefault();
    });
}