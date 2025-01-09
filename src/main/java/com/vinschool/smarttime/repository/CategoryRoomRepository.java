package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.CategoryRoom;

@Repository
public interface CategoryRoomRepository extends JpaRepository<CategoryRoom, String> {

}
