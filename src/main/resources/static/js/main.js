function onSuccess(title,error) {
    $("#alert").attr('class','w3-panel w3-green w3-round');
    $("#alert h3").html(title);
    $("#alert p").html(error);
    $("#alert").fadeIn().delay(3000);
    $("#alert").fadeOut();
}

function onError(title,error) {
    $("#alert").attr('class','w3-panel w3-red w3-round');
    $("#alert h3").html(title);
    $("#alert p").html(error);
    $("#alert").fadeIn().delay(3000);
    $("#alert").fadeOut();
}
