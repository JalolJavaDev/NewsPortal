package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.NewsCategoriesAndSubCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsCategoriesAndSubCategoriesRepository extends JpaRepository< NewsCategoriesAndSubCategories,Long> {
    NewsCategoriesAndSubCategories findById(long id);
   // @Query("select n from NewsCategoriesAndSubCategories n where n.categoryName =:categoryName")
    //NewsCategoriesAndSubCategories findNewsCategoriesAndSubCategoriesByCategoryName (@Param("categoryName") String categoryName);
   @Query("select n from News n where n.newsCategoriesAndSubCategories.categoryName = :categoryName")
    List<News> findByCategoryName(@Param("categoryName") String categoryName);
    void deleteById(long id);
    boolean existsByCategoryName(String categoryName);
}
