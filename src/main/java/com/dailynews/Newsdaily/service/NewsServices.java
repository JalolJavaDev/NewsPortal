package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NewsServices  {
    News findOne(Long id);
    Page<News> findAll(Pageable pageable);
    News  makeNews(News news );
    News findByNewsTitle(String newsTitle);
    void delete(long id);

}