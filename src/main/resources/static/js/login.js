$(document).ready(function(){
    $("#loader").hide();

    $(".form-wrapper .login").click(function(){

        var username = $("#username").val();
        var password = $("#password").val();

        $.ajax({
            type:'POST',
            url:'/user/login',
            data: {username:username, password:password},
            success:function(data){
                debugger
                $(".container").css({ maxWidth:'100' });
                $("#main").fadeOut(300)
                $("#loader").fadeIn();
                setTimeout(function () {
                    $("#loader").fadeOut();
                    $("#main").delay(200).load( "../items" ).fadeIn();
                },2000);
            },
            error:function (err) {
                debugger
                $("#main").fadeOut().fadeIn();
            }
        });

    });


    $(".form-wrapper .openRegister").click(function(){
        $("#main").hide();
        $("#main").hide().load( "../register" ).fadeIn('2500');
    });

    $(".form-wrapper .registerButton").click(function(){
        var button = $(this);

        var currentSection = button.parents(".section");
        var currentSectionIndex = currentSection.index();
        debugger
        if(currentSectionIndex === 1){

            var formData = $('#registerForm').serialize();
            $.ajax({
                type:'POST',
                url:'/user/save',
                data:JSON.stringify(formData),
                success:function(data){
                    console.log(data);
                },
                error:function (err) {
                    console.log(err);
                }
            });
        }

        var headerSection = $('.steps li').eq(currentSectionIndex);
        currentSection.removeClass("is-active").next().addClass("is-active");
        headerSection.removeClass("is-active").next().addClass("is-active");

        $(".form-wrapper").submit(function(e) {
            e.preventDefault();
        });

    });
});


