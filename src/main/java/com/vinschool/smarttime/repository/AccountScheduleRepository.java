package com.vinschool.smarttime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.AccountSchedule;

@Repository
public interface AccountScheduleRepository extends JpaRepository<AccountSchedule, String> {

    AccountSchedule findByUserId(String userId);
}
