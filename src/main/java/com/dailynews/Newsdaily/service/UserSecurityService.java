package com.dailynews.Newsdaily.service;
public interface UserSecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
