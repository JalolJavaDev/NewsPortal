package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.Users;
import com.dailynews.Newsdaily.repository.UsersReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    private final UsersReopsitory usersReopsitory;

    public UsersService(UsersReopsitory usersReopsitory) {
        this.usersReopsitory = usersReopsitory;
    }

    public List<Users> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Users> pagedResult = usersReopsitory.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Users>();
        }
    }
}