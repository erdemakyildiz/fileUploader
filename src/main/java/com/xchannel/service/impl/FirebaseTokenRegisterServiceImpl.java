package com.xchannel.service.impl;

import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import com.xchannel.entity.FirebaseToken;
import com.xchannel.entity.Media;
import com.xchannel.repository.FirebaseTokenRepository;
import com.xchannel.service.FirebaseTokenRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Detay on 23.11.2017.
 */
@Service
public class FirebaseTokenRegisterServiceImpl implements FirebaseTokenRegisterService{

    @Autowired
    private  FirebaseTokenRepository firebaseTokenRepository;

    @Override
    public void saveToken(FirebaseToken firebaseToken) {
        firebaseTokenRepository.save(firebaseToken);
    }

    @Override
    public List<FirebaseToken> findAll() {
        return firebaseTokenRepository.findAll();
    }

    @Override
    public FirebaseToken findToken(String token) {
        return firebaseTokenRepository.findFirstByFirebaseTokenEquals(token);
    }

    @Async
    @Override
    public void sendPushNotification(Media media) {
        firebaseTokenRepository.findAll().forEach(firebaseToken -> {
            try {
                URL url = new URL("https://fcm.googleapis.com/fcm/send");

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization","key=AIzaSyBBcxZUaN2MwLsbXc8RUMf8jNlVD8zlXu8");
                con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                con.setDoOutput(true);

                JsonObject payloadJson = new JsonObject();
                payloadJson.addProperty("to",firebaseToken.getFirebaseToken());

                JsonObject message = new JsonObject();
                message.addProperty("title","Yeni İfşa Geldi Wuuhuu");
                message.addProperty("body", media.getMediaTitle());
                message.addProperty("icon","/images/smile.png");
                message.addProperty("click_action","http://46.101.127.234:8080/media/"+media.getFileObjectId());
                payloadJson.add("notification", message);

//                System.out.println(payloadJson.toString());
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
                os.write(payloadJson.toString());
                os.close();

                InputStream in = new BufferedInputStream(con.getInputStream());

                in.close();
                con.disconnect();
            }catch (Exception e){
                firebaseTokenRepository.delete(firebaseToken);
            }
        });

    }
}
