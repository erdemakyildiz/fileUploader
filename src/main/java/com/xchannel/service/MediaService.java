package com.xchannel.service;

import com.xchannel.entity.Media;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by erdem akyıldız on 21.11.2017.
 */
public interface MediaService {

    public Media save(Media media);

    public List<Media> findAll(int page);

    public List<Media> findAll();

    public List<String> getPages();

    public void uploadMediaObject(String title);

    public Media findByTitle(String title);

}
