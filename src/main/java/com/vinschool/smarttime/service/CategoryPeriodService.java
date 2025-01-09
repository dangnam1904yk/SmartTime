package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.CategoryPeriod;

public interface CategoryPeriodService {
    CategoryPeriod save(CategoryPeriod period);

    void delete(String id);

    CategoryPeriod detail(String id);

    List<CategoryPeriod> getAll();
}
