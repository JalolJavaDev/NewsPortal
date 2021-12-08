package com.dailynews.Newsdaily.repository;
import com.dailynews.Newsdaily.domen.Users;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersReopsitory extends PagingAndSortingRepository<Users, Long> {
}