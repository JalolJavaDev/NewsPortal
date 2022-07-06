package com.dailynews.Newsdaily.service;

import com.dailynews.Newsdaily.domen.Role;
import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.repository.RoleRepository;
import com.dailynews.Newsdaily.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public User create(User user){
        User newUser =new User();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCreated(new java.util.Date());
        newUser.setUserStatus(true);
           newUser.setRoles(user.getRoles());
newUser.setUsername(user.getUsername());
newUser.setFullName(user.getFullName());
newUser.setEmail(user.getEmail());
       /* user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setConfirmPassword(user.getPassword());
        user.setCreated(new java.util.Date());
        user.setUpdated(new java.util.Date());
        user.setUserStatus(true);  // true only for pivotal
        user.setRoles(role);
        List<Role> role = roleRepository.findAll();
        Set<Role> uRole = new HashSet();
       for (Role userRole : role) {
            if (userRole.getRole().equals("ADMIN")) {
                uRole.add(userRole);
            }else {
                uRole.add(userRole);
            }
        }
        user.setRoles(uRole);*/
        return userRepository.save(newUser);
    }
    @Override
    public User findByUser(String admin) {
        return userRepository.findByUsername(admin);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean checkUserName(String userName){
        return userRepository.existsByUsername(userName);
    }




}