package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.NewsImage;

import java.util.List;

public interface NewsImageServices {
    List<NewsImage> findAllImage();

    NewsImage findById(long id);

    void saveImage(NewsImage image);

    void deleteById();

    List<NewsImage> findByNews(long newsId);
}