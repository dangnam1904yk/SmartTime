package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.CategorySubject;

@Repository
public interface CategorySubjectRepository extends JpaRepository<CategorySubject, String> {

}
