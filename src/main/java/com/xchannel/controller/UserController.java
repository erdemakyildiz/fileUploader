package com.xchannel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by erdem akyildiz on 22.11.2017.
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    @GetMapping(path = "login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

}
