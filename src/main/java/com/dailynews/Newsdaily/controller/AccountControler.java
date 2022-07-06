package com.dailynews.Newsdaily.controller;

import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.security.SecurityUtils;
import com.dailynews.Newsdaily.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountControler {
    private final UserService userService;

    public AccountControler(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/account")
    public ResponseEntity<?>getAccount(){
        String login= SecurityUtils.getCurrentUserName().get();
        User user=userService.findByUser(login);

        return ResponseEntity.ok(user);
    }
}
