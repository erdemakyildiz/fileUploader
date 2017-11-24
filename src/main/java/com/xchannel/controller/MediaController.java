package com.xchannel.controller;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.xchannel.entity.Media;
import com.xchannel.service.FirebaseTokenRegisterService;
import com.xchannel.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by erdem akyıldız on 21.11.2017.
 */
@RequestMapping(path = "media")
@Controller
public class MediaController {


    @Autowired
    private MediaService mediaService;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @GetMapping(path = "upload/{title}")
    public @ResponseBody String uploadMediaObject(@PathVariable(value = "title") final String title, Model model) {
        //he he
        mediaService.uploadMediaObject(title);
        return "aslanim yigidim.";
    }

    @GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Media> allMedia() {
        return mediaService.findAll();
    }

    @GetMapping(path = "pageableMedia", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Media> pageableMedia(@RequestParam(value = "p", defaultValue = "0") int page) {
        return mediaService.findAll(page);
    }

    @PostMapping(path = "uploadFile", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> setMediaObject(@RequestParam("file") MultipartFile file, @RequestParam("fileTitle") String fileTitle,
                                       Model model, HttpServletRequest request) {
        Media dbMedia = mediaService.findByTitle(fileTitle);

        if (fileTitle.isEmpty()) {
            return ResponseEntity.badRequest().body("İfşa Adı Zorunludur !");
        }else if (dbMedia != null){
            return ResponseEntity.badRequest().body("Bu isimde var zaten");
        } else {
            Media media = new Media();

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            media.setUser(username);

            media.setHost(request.getRemoteHost());

//            media.setAdress(request.getRemoteAddr());
//            media.setPort(request.getRemotePort());

            media.setMediaTitle(fileTitle);
            media.setCreateDate(new Date());

            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Hata var aq : " + e.getMessage());
            }

            GridFSFile gridFSFile = gridFsTemplate.store(inputStream, file.getName(), file.getContentType());
            media.setFileObjectId(gridFSFile.getId().toString());

            mediaService.save(media);
            return ResponseEntity.ok().body("ok");
        }


    }

    @GetMapping(path = "/{objectId}")
    public ResponseEntity<byte[]> getMediaObject(@PathVariable(value = "objectId") final String objectId,HttpServletResponse response, Model model) {
        GridFSDBFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(objectId)));

        ByteArrayOutputStream outputFile = new ByteArrayOutputStream();

        byte[] media = null;
        try {
            gridFSFile.writeTo(outputFile);
            media = outputFile.toByteArray();

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(gridFSFile.getContentType()))
                    .body(media);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message","Hata var yine la " + e.getMessage());
        }

        return new ResponseEntity<byte[]>(null);
    }


}
