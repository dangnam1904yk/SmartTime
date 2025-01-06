package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.Role;
import com.vinschool.smarttime.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements  RoleService{
    private  final RoleRepository repository;
    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Role findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }
}
