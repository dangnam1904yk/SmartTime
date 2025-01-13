package com.vinschool.smarttime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.TimeLine;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLine, String> {
    List<TimeLine> findByType(int type);
}
