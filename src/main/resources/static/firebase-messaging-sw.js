importScripts('firebase-app.js');
importScripts('firebase-messaging.js');

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
msg.setBackgroundMessageHandler(function (payload) {
    return self.registration.showNotification(payload);
});

