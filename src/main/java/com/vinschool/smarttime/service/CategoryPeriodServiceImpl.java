package com.vinschool.smarttime.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vinschool.smarttime.entity.CategoryPeriod;
import com.vinschool.smarttime.repository.CategoryPeriodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryPeriodServiceImpl implements CategoryPeriodService {

    private final CategoryPeriodRepository repository;

    @Override
    public CategoryPeriod save(CategoryPeriod period) {
        return repository.save(period);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public CategoryPeriod detail(String id) {
        Optional<CategoryPeriod> optional = repository.findById(id);
        if (optional.isPresent())
            return optional.get();
        return null;
    }

    @Override
    public List<CategoryPeriod> getAll() {
        return repository.findAll();
    }

}
