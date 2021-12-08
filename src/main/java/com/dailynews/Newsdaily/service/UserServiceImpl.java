package com.dailynews.Newsdaily.service;

import com.dailynews.Newsdaily.domen.Role;
import com.dailynews.Newsdaily.domen.User;
import com.dailynews.Newsdaily.domen.UserVerificationToken;
import com.dailynews.Newsdaily.repository.RoleRepository;
import com.dailynews.Newsdaily.repository.UserRepository;
import com.dailynews.Newsdaily.repository.UserVerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserVerificationTokenRepository userVerificationTokenRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserVerificationTokenRepository userVerificationTokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userVerificationTokenRepository = userVerificationTokenRepository;
    }

    @Override
    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setConfirmPassword(user.getPassword());
        user.setCreated(new java.util.Date());
        user.setUpdated(new java.util.Date());
        user.setUserStatus(true);  // true only for pivotal
        List<Role> role = roleRepository.findAll();
        Set<Role> uRole = new HashSet();
        for (Role userRole : role) {
            if (userRole.getRole().equals("ADMIN")) {
                uRole.add(userRole);
            }
        }
        user.setRoles(uRole);
        return userRepository.save(user);
    }
    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User>optional=userRepository.findByUsername(username);
        if(optional.isPresent()){
            User product=optional.get();
            return Optional.of(product);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean verifyingUser(User user, String token) {
        Long id = user.getId();
        UserVerificationToken tokenDetails = userVerificationTokenRepository.findByUserId(user);
        if (tokenDetails.getToken().equals(token)) {
            Query updateUser = entityManager.createQuery("update User set userStatus=true where id=:id");
            updateUser.setParameter("id", id);
            updateUser.executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkUserName(String userName){
        return userRepository.existsByUsername(userName);
    }

  /*  @Override
    public User getByLogin(String username) {
        return userRepository.findByLogin(username);
    }*/


}