package com.xchannel.push;

import com.xchannel.entity.Media;
import com.xchannel.service.FirebaseTokenRegisterService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by Detay on 23.11.2017.
 */
@Aspect
@Component
public class PushAopService {

    @Autowired
    FirebaseTokenRegisterService firebaseTokenRegisterService;

    @AfterReturning("execution(* com.xchannel.service.MediaService.save (com.xchannel.entity.Media)) && args(media)")
    public void beforeSampleCreation(Media media) {
        if (media != null && !StringUtils.isEmpty(media.getMediaTitle()))
            firebaseTokenRegisterService.sendPushNotification(media);
    }

}
