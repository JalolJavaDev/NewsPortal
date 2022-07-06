package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.NewsImage;
import com.dailynews.Newsdaily.repository.NewsImageRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsImageServicesImpl implements NewsImageServices {
    private final NewsImageRepository newsImageRepository;

    public NewsImageServicesImpl(NewsImageRepository newsImageRepository) {
        this.newsImageRepository = newsImageRepository;
    }

    @Override
    public List<NewsImage> findAllImage() {

        return  newsImageRepository.findAll();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public NewsImage findOne(Long id){
        Optional<NewsImage> optional=newsImageRepository.findById(id);
        if(optional.isPresent()){
            NewsImage newsImage=optional.get();
            return newsImage;
        }
        return null;
    }


    @Override
    public void delete(long id) {
        newsImageRepository.deleteById(id);
    }


    @Override
    public NewsImage addNewsImage(NewsImage newsImage) {

        return newsImageRepository.save(newsImage);
    }

    @Override
    public void deleteById() {

    }
    @Override
    public List<NewsImage> findByNews(long newsId) {
        return newsImageRepository.findByNews(newsId);
    }
    public Optional<NewsImage> getFile(Long imageId) {
        return newsImageRepository.findById(imageId);
    }

}