package com.vinschool.smarttime.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.CategoryRoom;
import com.vinschool.smarttime.repository.CategoryRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryRoomServiceImpl implements CategoryRoomSerive {

    private final CategoryRoomRepository repository;

    @Override
    public CategoryRoom save(CategoryRoom room) {
        return repository.save(room);
    }

    @Override
    public CategoryRoom detail(String id) {
        return repository.findById(id).get();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<CategoryRoom> getAll() {
        return repository.findAll();
    }

}
