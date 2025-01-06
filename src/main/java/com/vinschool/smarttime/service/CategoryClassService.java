package com.vinschool.smarttime.service;

import com.vinschool.smarttime.entity.CategoryClass;
import com.vinschool.smarttime.repository.CategoryClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CategoryClassService implements  CategoryClassServiceImpl {
    private  final CategoryClassRepository repository;

    @Override
    public CategoryClass getById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public List<CategoryClass> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(CategoryClass categoryClass) {
        CategoryClass category = null;
        if(categoryClass.getId().isEmpty()){
        category  = getByNameClass(categoryClass.getClassName());
        }else {
            category= findByNameClassAndIdIsNot(categoryClass.getClassName(),categoryClass.getId());
        }
        if(categoryClass.getId().isEmpty()){
            categoryClass.setId(null);
        }
        if(category == null){
            categoryClass.setCreateDate(new Date());
            repository.save(categoryClass);
        }

    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public CategoryClass getByNameClass(String className) {
        return repository.findByClassName(className);
    }

    @Override
    public CategoryClass findByNameClassAndIdIsNot(String nameClass, String id) {
       return repository.findByClassNameAndIdIsNot(nameClass,id);
    }
}
