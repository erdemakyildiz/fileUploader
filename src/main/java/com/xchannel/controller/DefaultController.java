package com.xchannel.controller;

import com.xchannel.entity.Media;
import com.xchannel.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Detay on 21.11.2017.
 */
@Controller
public class DefaultController {

    @Autowired
    private MediaService mediaService;

    @RequestMapping(path = "/")
    public ModelAndView getHomePage(Model model, @RequestParam(value = "p", defaultValue = "0") int page){
        model.addAttribute("files",mediaService.findAll(page));
        model.addAttribute("pages", mediaService.getPages());

        return new ModelAndView("index");
    }

}
