package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NewsImageServices {
    List<NewsImage> findAllImage();

    NewsImage findOne(Long id);

    NewsImage addNewsImage(NewsImage newsImage);
    void deleteById();

    Optional<NewsImage> getFile(Long imageId);


    void delete(long id);

    List<NewsImage> findByNews(long newsId);
    //NewsImage image findByNewsId(Long newsId);


}