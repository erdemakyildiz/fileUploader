package com.xchannel.repository;

import com.xchannel.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Detay on 21.11.2017.
 */
@Repository
public interface MediaRepository extends PagingAndSortingRepository<Media, String> {

    Page<Media> findAll(Pageable pageable);

    void deleteByMediaTitleEquals(String title);

    Media findFirstByMediaTitleEquals(String title);

}
