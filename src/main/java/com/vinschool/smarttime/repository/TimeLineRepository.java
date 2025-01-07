package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.TimeLine;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, String> {

}