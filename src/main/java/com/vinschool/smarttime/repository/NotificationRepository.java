package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String> {

}
