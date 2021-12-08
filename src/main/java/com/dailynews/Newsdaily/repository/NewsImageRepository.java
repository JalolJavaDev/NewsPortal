package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.NewsImage;

import java.util.List;

public interface NewsImageRepository {
    List<NewsImage> findAllImage();

    NewsImage findById(long id);

    void saveImage(NewsImage image);

    void deleteById();

    List<NewsImage> findByNews(long newsId);
}