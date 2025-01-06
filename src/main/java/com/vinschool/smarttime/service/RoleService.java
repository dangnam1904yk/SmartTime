package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.Role;

import java.util.List;

public interface RoleService {
    void save(Role role);
    void delete(String id);
    Role findById( String id);
    List<Role> getAll();
}
