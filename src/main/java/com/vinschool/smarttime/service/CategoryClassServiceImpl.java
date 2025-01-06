package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.CategoryClass;

import java.util.List;

public interface CategoryClassServiceImpl {
    CategoryClass getById( String id);
    List<CategoryClass> getAll();
    void save(CategoryClass categoryClass);
    void delete(String id);
    CategoryClass getByNameClass( String className);
    CategoryClass findByNameClassAndIdIsNot(String nameClass,String id);
}
