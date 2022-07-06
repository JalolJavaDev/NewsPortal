package com.dailynews.Newsdaily.controller;

import com.dailynews.Newsdaily.controller.vm.LoginVM;
import com.dailynews.Newsdaily.domen.Role;
import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.repository.UserRepository;
import com.dailynews.Newsdaily.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController

@RequestMapping("/api")
public class UserJwtController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVM loginVM){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword()));
        System.out.println("user :"+loginVM.getUsername());
        User user = userRepository.findByLogin(loginVM.getUsername());
        if (user==null){
            throw new UsernameNotFoundException("Bu foydalanuvch mavjut emas");
        }
        String token = jwtTokenProvider.createToken(user.getUsername(),user.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }
}
