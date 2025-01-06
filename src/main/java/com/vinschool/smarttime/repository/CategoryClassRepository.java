package com.vinschool.smarttime.repository;

import com.vinschool.smarttime.entity.CategoryClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryClassRepository extends JpaRepository<CategoryClass, String> {
    CategoryClass findByClassName(String nameClass);
    CategoryClass findByClassNameAndIdIsNot(String nameClass,String id);

}
