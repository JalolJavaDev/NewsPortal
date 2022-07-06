package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsCategoriesAndSubCategories;
import com.dailynews.Newsdaily.repository.NewsCategoriesAndSubCategoriesRepository;
import com.dailynews.Newsdaily.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class NewsCategoriesAndSubCategoriesServiceImpl implements NewsCategoriesAndSubCategoriesService {
    private final NewsCategoriesAndSubCategoriesRepository newsCategoriesAndSubCategoriesRepository;
private final NewsRepository newsRepository;

    public NewsCategoriesAndSubCategoriesServiceImpl(NewsCategoriesAndSubCategoriesRepository newsCategoriesAndSubCategoriesRepository, JdbcTemplate jdbcTemplate, NewsRepository newsRepository) {

        this.newsCategoriesAndSubCategoriesRepository = newsCategoriesAndSubCategoriesRepository;

        this.newsRepository = newsRepository;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public NewsCategoriesAndSubCategories findOne(Long id){
        Optional<NewsCategoriesAndSubCategories> optional= newsCategoriesAndSubCategoriesRepository.findById(id);
        if(optional.isPresent()){
            NewsCategoriesAndSubCategories newsCategory=optional.get();
            return newsCategory;
        }
        return null;
    }
    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Page<NewsCategoriesAndSubCategories> findAll(Pageable pageable){
        return  newsCategoriesAndSubCategoriesRepository.findAll(pageable);
    }

    @Override
    public void delete(long id) {
        newsCategoriesAndSubCategoriesRepository.deleteById(id);
    }


    @Override
    public NewsCategoriesAndSubCategories addNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories) {
        newsCategoriesAndSubCategories.setCreationDTM(new java.util.Date());
        newsCategoriesAndSubCategories.setActive(true);
        return newsCategoriesAndSubCategoriesRepository.save(newsCategoriesAndSubCategories);
    }
    public NewsCategoriesAndSubCategories updNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories) {
        newsCategoriesAndSubCategories.setUpdationDTM(new java.util.Date());
        return newsCategoriesAndSubCategoriesRepository.save(newsCategoriesAndSubCategories);
    }

    @Override
    public List<News> findByCategoryName(String categoryName) {
        List<News> categoryList = newsCategoriesAndSubCategoriesRepository.findByCategoryName(categoryName);
        return categoryList;
    }

    @Override
    public Boolean checkCategoryName(String categoryName) {
     return  newsCategoriesAndSubCategoriesRepository.existsByCategoryName(categoryName);
    }

}