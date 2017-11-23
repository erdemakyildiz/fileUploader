package com.xchannel.service;

import com.xchannel.entity.FirebaseToken;
import com.xchannel.entity.Media;

import java.util.List;

/**
 * Created by Detay on 23.11.2017.
 */
public interface FirebaseTokenRegisterService {

    public void saveToken(FirebaseToken firebaseToken);

    public List<FirebaseToken> findAll();

    public FirebaseToken findToken(String token);

    public void sendPushNotification(Media media);

}
