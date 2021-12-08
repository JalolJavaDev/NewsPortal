package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsCategoriesAndSubCategories;

import java.util.List;

public interface NewsCategoriesAndSubCategoriesRepository {
    List<NewsCategoriesAndSubCategories> findAllNewsCategory();

    List<NewsCategoriesAndSubCategories> findAllNewsCategoriesAndSubCategories();

    NewsCategoriesAndSubCategories findById(long id);

    void saveNewsCategory(NewsCategoriesAndSubCategories newsCategoriesAndSubCategories);

    void deleteById(long id);

    List<NewsCategoriesAndSubCategories> showCategoriesInMenuBar();

    List<News> findByCategoryName(String categoryName);

    List<NewsCategoriesAndSubCategories> findAllSubCategory();

    List<NewsCategoriesAndSubCategories> findSubCategoryChildBySubCategoryName(String subCategoryChild);

    NewsCategoriesAndSubCategories findBySubCategoryId(int subCategoryId);

    List<NewsCategoriesAndSubCategories> findSubCategoryByCategoryId(int categoryId);

    boolean saveNewsSubCategory(NewsCategoriesAndSubCategories newsSubCategory);

    boolean deleteById(int id);

    NewsCategoriesAndSubCategories findBySubCategoryName(String subCategoryName);

    List<NewsCategoriesAndSubCategories> findNewsSubCategoriesByCategoryName(String categoryName);
}
