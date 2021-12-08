package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.NewsImage;
import com.dailynews.Newsdaily.repository.NewsImageRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsImageServicesImpl implements NewsImageServices {
    private final NewsImageRepository newsImageRepository;

    public NewsImageServicesImpl(NewsImageRepository newsImageRepository) {
        this.newsImageRepository = newsImageRepository;
    }

    @Override
    public List<NewsImage> findAllImage() {
        return null;
    }

    @Override
    public NewsImage findById(long id) {
        return null;
    }


    @Override
    public void saveImage(NewsImage image) {
        newsImageRepository.saveImage(image);
    }

    @Override
    public void deleteById() {

    }

    @Override
    public List<NewsImage> findByNews(long newsId) {
        return newsImageRepository.findByNews(newsId);
    }
}