package com.dailynews.Newsdaily.service;
import com.dailynews.Newsdaily.domen.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    List<Role> findAllRole();
}