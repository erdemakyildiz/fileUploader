$(document).ready(function () {

    $("#alert").hide();
    $("#mask").fadeIn();
    $(".loginUser").hide();

    $.ajax({
        type: 'GET',
        url: '/user/get',

        success: function (data, textStatus) {
            $("#userName").html(data.username);
            debugger

            setTimeout(function () {
                $("#mask").fadeOut();
                $(".loginUser").show();

                $("#main").load("../items").fadeIn(500);
            }, 1000);
        },
        error: function (err) {
            debugger
            setTimeout(function () {
                $(".loginUser").hide();
                $("#mask").fadeOut();
                $("#main").load("../user/login").fadeIn(500);
            }, 1000);
        }
    });

});