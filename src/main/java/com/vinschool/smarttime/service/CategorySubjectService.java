package com.vinschool.smarttime.service;

import java.util.List;

import com.vinschool.smarttime.entity.CategorySubject;

import jakarta.servlet.http.HttpServletRequest;

public interface CategorySubjectService {
    CategorySubject save(CategorySubject subject, HttpServletRequest request);

    CategorySubject detail(String id);

    void delete(String id);

    List<CategorySubject> getAll();
}
