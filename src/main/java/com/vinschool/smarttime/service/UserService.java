package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.User;

import java.util.List;

public interface UserService {
    void save(User user, String role);
    void delete (String id);
    User findById (String id);
    User finByEmail( String email);
    List<User> getAll();
}
