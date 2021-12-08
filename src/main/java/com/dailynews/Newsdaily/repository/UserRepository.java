package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    User findByEmail(String email);
boolean existsByUsername(String username);
   // User findByLogin(@Param("username")String username);
}