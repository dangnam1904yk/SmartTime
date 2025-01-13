package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.Role;
import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.repository.UserRepository;
import com.vinschool.smarttime.ulti.HashPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceIImpl implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;

    @Override
    public void save(User user, String role) {
        Role role1 = roleService.findById(role);
        if (role1 == null) {
            role1 = roleService.findByCode(role);
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role1);

        user.setRole(roles);
        if (user.getId() == null || user.getId() == "") {
            if (repository.findByEmail(user.getEmail()) == null) {
                user.setPassword(HashPassword.hashPass(user.getPassword()));
                user.setCreateDate(new Date());
                user.setCreateDate(new Date());
                user.setId(null);
            }
        } else {
            User oldUser = findById(user.getId());
            if (repository.findByEmailAndIdIsNot(user.getEmail(), user.getId()) == null) {
                user.setPassword(oldUser.getPassword());
                user.setCreateDate(oldUser.getCreateDate());
                user.setUpdateDate(new Date());
            }
        }
        repository.save(user);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(String id) {
        Optional<User> optionn = repository.findById(id);
        if (optionn.isPresent())
            return optionn.get();
        return null;
    }

    @Override
    public User finByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User checkLogin(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> findUserByCodeRole(String codeRole) {
        return repository.findByRoleCodeRole(codeRole);
    }
}
