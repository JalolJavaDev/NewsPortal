package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.NewsUserDetails;
import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component("userdetailsservice")
@Service
public class NewsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public NewsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("username not found"));

        return user.map(NewsUserDetails::new).get();
    }

}