package com.xchannel.service.impl;

import com.mongodb.gridfs.GridFSDBFile;
import com.xchannel.entity.Media;
import com.xchannel.repository.MediaRepository;
import com.xchannel.service.MediaService;
import jersey.repackaged.com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
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

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public Media save(Media media) {
        Media savedMedia =  mediaRepository.save(media);
        return savedMedia;
    }

    @Override
    public List<String> getPages() {
        List<String> pages = new ArrayList<>();
        Long count = mediaRepository.count();
        int pageCount = count.intValue() / 30;

        if (pageCount % 30 == 0)
            pageCount -= 1;

        for (int i = 0; i <= pageCount; i++) {
            pages.add(String.valueOf(i+1));
        }

        return pages;
    }

    @Override
    public List<Media> findAll(int page) {
        Sort sort = new Sort(Sort.Direction.DESC, "createDate");
        Pageable pageable = new PageRequest(page, 30, sort);
        Page<Media> mediaPage = mediaRepository.findAll(pageable);
        return Lists.newArrayList(mediaPage);
    }

    @Override
    public List<Media> findAll() {
        return Lists.newArrayList(mediaRepository.findAll());
    }

    @Override
    public void uploadMediaObject(String title) {
        Media media = mediaRepository.findOne(title);
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(media.getFileObjectId())));
        mediaRepository.delete(media);
    }

    @Override
    public Media findByTitle(String title) {
        return mediaRepository.findFirstByMediaTitleEquals(title);
    }
}
