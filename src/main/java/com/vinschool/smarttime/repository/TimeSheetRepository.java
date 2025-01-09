package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.TimeSheet;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, String> {

}
