package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsCategoriesAndSubCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsCategoriesAndSubCategoriesService {

    NewsCategoriesAndSubCategories findOne(Long id);
    Page<NewsCategoriesAndSubCategories> findAll(Pageable pageable);

    NewsCategoriesAndSubCategories addNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories);
    NewsCategoriesAndSubCategories updNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories);
//NewsCategoriesAndSubCategories getAllCategoryByName (String categoryName);
    //List<NewsCategoriesAndSubCategories> getAllCategoriesByName(String categoryName);
    void delete(long id);

    List<News> findByCategoryName(String categoryName);

    Boolean checkCategoryName(String categoryName);

    //List<NewsCategoriesAndSubCategories> getAllCategoriesByName(String categoryName);
}