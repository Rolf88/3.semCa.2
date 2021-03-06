$(document).ready(function () {
    getPerson();
    $("#refresh").click(function () {
        getPerson();
    });

    $("#search").click(function () {
        getPersonByHobby();
    });

    $("#addPersonBtn").click(function () {
        addNewPerson();
    });

    $("#updatePersonBtn").click(function () {
        updateNewPerson();
    });

    $("#personTable").on("click", "a[href='#update']", function (e) {
        var $this = $(this),
                $container = $this.parents("tr").first(),
                personid = $container.data("id");

        $("#myupdateform").data("personid", personid);
        updateUpdateForm(personid).then(function () {
            $("#updatePersonModal").modal("show");
        });

        e.preventDefault();
    });

    $("#personTable").on("click", "a[href='#delete']", function (e) {
        var $this = $(this),
                $container = $this.parents("tr").first(),
                personid = $container.data("id");

        $.ajax({
            method: "DELETE",
            contentType: "application/json",
            url: "/3.semCa.2/api/person/" + personid
        }).then(function () {
            getPerson();
        });

        e.preventDefault();
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
                .append($(document.createElement("td")).append(
                        $(document.createElement("a")).attr({href: "#update"}).text("edit"))
                        );
    }
};

function updateUpdateForm(personId) {
    return $.ajax({
        method: "GET",
        url: "/3.semCa.2/api/person/complete/" + personId
    }).then(function (response) {
        $("#updatefirstname").val(response.firstName);
        $("#updatelastname").val(response.lastName);
        $("#updateemail").val(response.email);

        if (response.phones.length > 0) {
            $("#updatephone").val(response.phones[0].number);
        }

        if (typeof (response.address) !== "undefined") {
            $("#updateadress").val(response.address.street);
            $("#updatecity").val(response.address.city.city);
            $("#updatezipcode").val(response.address.city.zip);
        }

        if (response.hobbies.length > 0) {
            $("#updatehobby").val(response.hobbies[0].name);
        }
    });
}

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
                address: {
                    street: $("#adressinput").val(),
                    additionalInfo: "",
                    city: {
                        city: $("#cityinput").val(),
                        zip: $("#zipcodeinput").val()
                    }
                },
                hobbies: [{
                        name: $("#hobbyinput").val(),
                        description: ""
                    }]
            })
        }).then(function () {
            $("#addPersonModal").modal("hide");
            getPerson();
        });
        e.preventDefault();
    });
}

function updateNewPerson() {

    $("#myupdateform").submit(function (e) {
        var personid = $(this).data("personid");
        $.ajax({
            method: "PUT",
            contentType: "application/json",
            url: "/3.semCa.2/api/person",
            data: JSON.stringify({
                id: personid,
                firstName: $("#updatefirstname").val(),
                lastName: $("#updatelastname").val(),
                email: $("#updateemail").val(),
                phones: [{number: $("#updatephone").val()}],
                address: {
                    street: $("#updateadress").val(),
                    additionalInfo: "",
                    city: {
                        city: $("#updatecity").val(),
                        zip: $("#updatezipcode").val()
                    }
                },
                hobbies: [{
                        name: $("#updatehobby").val(),
                        description: ""
                    }]
            })

        }).then(function () {
            $("#updatePersonModal").modal("hide");
            getPerson();
        });

        e.preventDefault();
    });
}