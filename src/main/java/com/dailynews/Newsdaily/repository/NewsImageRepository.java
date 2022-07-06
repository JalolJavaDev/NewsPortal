package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.NewsImage;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsImageRepository extends JpaRepository<NewsImage, Long> {
    Optional<NewsImage> findByImageSource(String imageSource);
    NewsImage findById(long id);

    void deleteById(long id);
   @Query("select n from NewsImage n where n.news.newsId = :newId")
   List<NewsImage> findByNews(@Param("newId") long newsId);

}