package com.dailynews.Newsdaily.repository;

import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NewsRepository  extends JpaRepository<News,Long> {

    News findById(long id);

    News findByNewsTitle(String newsTitle);

    void deleteById(long id);

   // List<Users> showAllUsers();


}