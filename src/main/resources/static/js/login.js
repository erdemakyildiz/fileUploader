$(document).ready(function(){
    $("#mask").hide();
    $("#alert").hide();

    $("#signUp").click(function(){
        $("#main").fadeOut();
        $("#mask").fadeIn();

        $("#main").load( "../user/login" ).fadeIn(500);

        $("#mask").fadeOut();
    });

    $(".form-wrapper .login").click(function(){

        var username = $("#username").val();
        var password = $("#password").val();

        $("#main").hide();
        $("#mask").fadeIn();

        $.ajax({
            type:'POST',
            url:'/',
            data: {username:username, password:password},
            success:function(data){
                $(".container").css({ maxWidth:'100%' });

                setTimeout(function (data) {
                    $("#mask").fadeOut();

                    $.ajax({
                        type: 'GET',
                        url: '/user/get',
                        success: function (data, textStatus) {
                            $("#userName").html(data.username);
                            $(".loginUser").show();
                        }
                    });

                    $("#main").load( "../items" ).fadeIn(200);
                },1000);
            },
            error:function (err) {
                setTimeout(function () {
                    $("#mask").fadeOut();
                    $("#main").fadeIn();
                    $(".loginUser").hide();

                    onError("Hata", "Kullanıcı adı ve ya şifre hatalı.");
                },1000);


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

        if(currentSectionIndex === 1){
            var username = $("#username").val();
            var email = $("#email").val();
            var password = $("#password").val();

            if (password === "" || password.length < 4){
                onError("Hata", "Geçerli bir parola adı girin. En az 5 karakter.");
                return;
            }

            $.ajax({
                type:'POST',
                url:'/user/save',
                data:{username:username,email:email,password:password},
                success:function(data){

                    var headerSection = $('.steps li').eq(currentSectionIndex);
                    currentSection.removeClass("is-active").next().addClass("is-active");
                    headerSection.removeClass("is-active").next().addClass("is-active");

                    $(".form-wrapper").submit(function (e) {
                        e.preventDefault();
                    });

                    onSuccess("Başarılı", "Kullanıcı girişi yapınız.");
                },
                error:function (err) {

                    var headerSection = $('.steps li').eq(currentSectionIndex);
                    currentSection.removeClass("is-active");
                    headerSection.removeClass("is-active");

                    $(document).find(".form-wrapper .section").first().addClass("is-active");
                    $(document).find(".steps li").first().addClass("is-active");

                    $(".form-wrapper").submit(function (e) {
                        e.preventDefault();
                    });

                    onError("Hata", "Kayıt başarısız. Tüm alanları doldurun lütfen.");
                }
            });

            return;
        }else if(currentSectionIndex === 0){
            var username = $("#username").val();
            var email = $("#email").val();

            if (username === "" || username.length < 5){
                onError("Hata", "Geçerli bir kullanıcı adı girin. En az 5 karakter.");

                return;
            }


            if (!validateEmail(email)){
                onError("Hata", "Geçerli bir mail adresi girin.");

                return;
            }

        }



        var headerSection = $('.steps li').eq(currentSectionIndex);
        currentSection.removeClass("is-active").next().addClass("is-active");
        headerSection.removeClass("is-active").next().addClass("is-active");

        $(".form-wrapper").submit(function (e) {
            e.preventDefault();
        });

    });
});

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}




