package com.dailynews.Newsdaily.controller;
import com.dailynews.Newsdaily.domen.*;
import com.dailynews.Newsdaily.repository.*;
import com.dailynews.Newsdaily.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class NewsController {


    private final NewsServices newsServices;

    public NewsController(NewsServices newsServices) {

        this.newsServices = newsServices;

    }

    @GetMapping("/news")
    public ResponseEntity<?> getAll(@PageableDefault(sort = "newsId",direction = Sort.Direction.DESC, size = Integer.MAX_VALUE) Pageable pageable){
        Page<News> newsproduct=newsServices.findAll(pageable);
        return ResponseEntity.ok(newsproduct);
    }
    @GetMapping("/news/newsTitle")
    public ResponseEntity getByTitle(@RequestParam String newsTitle){
        News newsproduct=newsServices.findByNewsTitle(newsTitle);
        return ResponseEntity.ok(newsproduct);
    }
    @GetMapping("/news/{newsId}")
    public ResponseEntity<News> getOne(@PathVariable Long newsId){
        News news=newsServices.findOne(newsId);
        return ResponseEntity.ok(news);
    }
    @PostMapping("/news")
    public ResponseEntity<News> addNews(@RequestBody News news) {
        News newsproduct=newsServices.makeNews(news);
        return ResponseEntity.ok(newsproduct);
    }
    @PutMapping("/news")
    public ResponseEntity<News> updateNews(@RequestBody News news){
        News newsProduct=newsServices.makeNews(news);
        return ResponseEntity.ok(newsProduct);
    }
    @DeleteMapping("/news/{id}")
    public void delete(@PathVariable Long id){
        newsServices.delete(id);
    }


}