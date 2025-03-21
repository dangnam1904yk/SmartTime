package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.DetailNotification;
import java.util.List;

@Repository
public interface DetailNotificationRepository extends JpaRepository<DetailNotification, String> {
    long countByUserId(String userId);

    long count();

    List<DetailNotification> findByUserId(String userId);

}
