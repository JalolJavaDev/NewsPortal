package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
     User create(User user);

    User findByUser(String admin);

    User findByEmail(String email);

    Boolean checkUserName(String userName);


}