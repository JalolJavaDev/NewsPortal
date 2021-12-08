package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class NewsServicesImpl implements NewsServices {


    private final  NewsRepository newsRepository;

    public NewsServicesImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public News findOne(Long id){
        Optional<News>optional=newsRepository.findById(id);
        if(optional.isPresent()){
            News newsproduct=optional.get();
            return newsproduct;
        }
        return null;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<News> findAll(Pageable pageable){
        Page<News> page=newsRepository.findAll(pageable);
        return page;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public News findByTitle(String title){
        return newsRepository.getfindByTitl(title);
    }

    public void delete(Long id){
        newsRepository.deleteById(id);
    }

    @Override
    public News makeNews(News news) {
return newsRepository.save(news);
    }


  /*  @Override
    public  makeNews(News news) {
        news.setCreationDTM(new java.util.Date());
        news.setUpdationDTM(new java.util.Date());
        news.setActive(true);
        newsRepository.saveNews(news);

        return news.getNewsId();
    }*/

    @Override
    public void deleteById(long id) {
        newsRepository.deleteById(id);
    }

}