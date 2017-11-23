// Initialize Firebase
var config = {
    apiKey: "AIzaSyADH1UuZ6b4_C3HAIEwQhPVxR6-nfa82wM",
    authDomain: "xchannel-2f56d.firebaseapp.com",
    databaseURL: "https://xchannel-2f56d.firebaseio.com",
    projectId: "xchannel-2f56d",
    storageBucket: "xchannel-2f56d.appspot.com",
    messagingSenderId: "838659413827"
};
firebase.initializeApp(config);

const msg = firebase.messaging();
msg.requestPermission()
.then(function(){
    console.log("Yetki alındı");
    return msg.getToken();
})
    .then(function (token_) {
        $.ajax({
            type: "POST",
            url: "/user/token",
            data: {token:token_},
            success: function () {
                console.log("token kayıt edildi. :)")
            },
            error: function () {
                console.log("token kayıt edilemedi. :(")
            }
        });

        console.log(token_);
    })
.catch(function (err) {
    console.log("Yetki verilmedi");
})


msg.onMessage(function (payload) {
    console.log("OnMessage ", payload);
})