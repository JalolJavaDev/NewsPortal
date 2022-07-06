package com.dailynews.Newsdaily.controller;

import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.service.RoleService;
import com.dailynews.Newsdaily.service.UserSecurityService;
import com.dailynews.Newsdaily.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if (!checkPasswordLength(user.getPassword())){
            return new ResponseEntity<>("Parol uzuligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUsername())){
            return new ResponseEntity<>("Bu user Oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST);
        }
        User newUser=userService.create(user);
        return ResponseEntity.ok(newUser);
    }

    private Boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }
}
