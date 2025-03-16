package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.User;

import java.util.List;

public interface UserService {
    User save(User user, String role);

    void delete(String id);

    User findById(String id);

    User findByEmail(String email);

    List<User> getAll();

    User checkLogin(String email, String password);

    List<User> findUserByCodeRole(String codeRole);
}
