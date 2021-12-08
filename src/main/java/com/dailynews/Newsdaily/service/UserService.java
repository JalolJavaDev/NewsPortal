package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.News;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface UserService {
     User create(User user);

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    boolean verifyingUser(User id, String token);
    Boolean checkUserName(String userName);
  //  User getByLogin(String username);

}