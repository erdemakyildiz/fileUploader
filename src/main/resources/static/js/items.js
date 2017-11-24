$(document).ready(function () {
    $(".item").click(function(){
        var uri = ".." + $(this).attr("href")
        window.open(uri, "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=50,width=800,height=600");
    });

    $(".pageItem").click(function(){
        var page = $(this).text();

        $( ".container" ).css({ maxWidth:'100' });
        $("#main").slideUp();
        $("#mask").fadeIn();

        setTimeout(function () {
            $("#mask").fadeOut();
            $("#main").delay(200).load( "../items?p="+ page ).slideDown();
        },2000);
    });
});