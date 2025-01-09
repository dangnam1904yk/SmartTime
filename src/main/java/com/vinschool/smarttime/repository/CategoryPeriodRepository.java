package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.CategoryPeriod;

@Repository
public interface CategoryPeriodRepository extends JpaRepository<CategoryPeriod, String> {

}
