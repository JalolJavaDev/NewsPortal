package com.dailynews.Newsdaily.controller;
import com.dailynews.Newsdaily.domen.*;
import com.dailynews.Newsdaily.service.*;
import org.scalatest.prop.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CategoriesController {

    private final NewsCategoriesAndSubCategoriesService newsCategoriesAndSubCategoriesService;

    public CategoriesController(NewsCategoriesAndSubCategoriesService newsCategoriesAndSubCategoriesService ) {
        this.newsCategoriesAndSubCategoriesService = newsCategoriesAndSubCategoriesService;

    }
    @GetMapping("/category")
    public ResponseEntity<Page<NewsCategoriesAndSubCategories>> getAll(@PageableDefault(sort = "categoryId",direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable){
        Page<NewsCategoriesAndSubCategories> news_category=newsCategoriesAndSubCategoriesService.findAll(pageable);
        return ResponseEntity.ok(news_category);
    }
    @GetMapping("/category/name/{categoryName}")
    public ResponseEntity<List<News>> getCategoryByName(@PathVariable("categoryName") String categoryName) {
       List<News>category =newsCategoriesAndSubCategoriesService.findByCategoryName(categoryName) ;
    return  ResponseEntity.ok(category);
}
    @GetMapping("/category/{id}")
    public ResponseEntity<NewsCategoriesAndSubCategories> getOne(@PathVariable Long id){
        NewsCategoriesAndSubCategories news_category=newsCategoriesAndSubCategoriesService.findOne(id);
        return ResponseEntity.ok(news_category);
    }
    @PostMapping("/category")
    public ResponseEntity<?> addNews(@RequestBody NewsCategoriesAndSubCategories newsCategoriesAndSubCategories ) {
        if(newsCategoriesAndSubCategoriesService.checkCategoryName(newsCategoriesAndSubCategories.getCategoryName())){
            return new ResponseEntity<>("Bunday categoriya mavjud", HttpStatus.BAD_REQUEST);
        }
        NewsCategoriesAndSubCategories newscategory=newsCategoriesAndSubCategoriesService.addNewsCategory(newsCategoriesAndSubCategories);
        return ResponseEntity.ok(newscategory);
    }

    @PutMapping("/category")
    public ResponseEntity<NewsCategoriesAndSubCategories> updateNews(@RequestBody NewsCategoriesAndSubCategories newsCategoriesAndSubCategories){
        NewsCategoriesAndSubCategories news_Category=newsCategoriesAndSubCategoriesService.updNewsCategory(newsCategoriesAndSubCategories);
        return ResponseEntity.ok(news_Category);
    }
    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable Long id){
        newsCategoriesAndSubCategoriesService.delete(id);

    }


}