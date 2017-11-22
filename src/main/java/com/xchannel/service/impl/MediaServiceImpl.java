package com.xchannel.service.impl;

import com.xchannel.entity.Media;
import com.xchannel.repository.MediaRepository;
import com.xchannel.service.MediaService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by erdem akyıldız on 21.11.2017.
 */
@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Media save(Media media) {
        Media savedMedia =  mediaRepository.save(media);

        try {
            String stringUrl = "http://ortc-developers-useast1-s0001.realtime.co/send";
            URL url = new URL(stringUrl);
            URLConnection uc = url.openConnection();

            uc.setRequestProperty("AK","K4xqxB");
            uc.setRequestProperty("AT","");
            uc.setRequestProperty("C","channel-ee31dc0a-8b9e-4c7b-9680-62a06915ada8");
            uc.setRequestProperty("M","12345678_1-1_This is a web push notification sent using the Realtime REST API");

            InputStreamReader inputStreamReader = new InputStreamReader(uc.getInputStream());
        }catch (Exception e){}

        return savedMedia;
    }

    @Override
    public List<String> getPages() {
        List<String> pages = new ArrayList<>();
        Long count = mediaRepository.count();
        int pageCount = count.intValue() / 10;
        for (int i = 0; i <= pageCount; i++) {
            pages.add(String.valueOf(i));
        }

        return pages;
    }

    @Override
    public List<Media> findAll(int page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(page, 10, sort);
        Page<Media> mediaPage = mediaRepository.findAll(pageable);
        return Lists.newArrayList(mediaPage);
    }

    @Override
    public List<Media> findAll() {
        return Lists.newArrayList(mediaRepository.findAll());
    }

    @Override
    public void uploadMediaObject(String title) {
        mediaRepository.delete(title);
    }

    @Override
    public Media findByTitle(String title) {
        return mediaRepository.findFirstByMediaTitleEquals(title);
    }
}
