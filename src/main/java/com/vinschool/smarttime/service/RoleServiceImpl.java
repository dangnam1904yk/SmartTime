package com.vinschool.smarttime.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.Role;
import com.vinschool.smarttime.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Role findById(String id) {
        Optional<Role> optionn = repository.findById(id);
        if (optionn.isPresent())
            return optionn.get();
        return null;
    }

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public Role findByCode(String code) {
        return repository.findByCodeRole(code);
    }
}
