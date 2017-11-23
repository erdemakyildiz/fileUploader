package com.xchannel.controller;

import com.xchannel.entity.FirebaseToken;
import com.xchannel.service.FirebaseTokenRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by erdem akyildiz on 22.11.2017.
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private FirebaseTokenRegisterService firebaseTokenRegisterService;

    @GetMapping(path = "login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @PostMapping(path = "token")
    @ResponseBody
    public void registerToken(@RequestParam("token") String token){
        if (firebaseTokenRegisterService.findToken(token) == null) {
            FirebaseToken firebaseToken = new FirebaseToken();
            firebaseToken.setFirebaseToken(token);

            firebaseTokenRegisterService.saveToken(firebaseToken);
        }
    }

}
