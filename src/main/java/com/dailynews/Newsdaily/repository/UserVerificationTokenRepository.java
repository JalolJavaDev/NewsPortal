package com.dailynews.Newsdaily.repository;

import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.domen.UserVerificationToken;

public interface UserVerificationTokenRepository {
    void saveToken(User user, String token);

    UserVerificationToken findById();

    String constructToken();

    UserVerificationToken findByUserId(User id);


}