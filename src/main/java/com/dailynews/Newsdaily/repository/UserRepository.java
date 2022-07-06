package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);
boolean existsByUsername(String username);
    @Query("select u from User u where u.username=:username")
   User findByLogin(@Param("username") String username);
}