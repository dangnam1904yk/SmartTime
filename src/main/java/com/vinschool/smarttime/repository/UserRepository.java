package com.vinschool.smarttime.repository;

import com.vinschool.smarttime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    User findByEmailAndIdIsNot(String email, String id);

    User findByEmailAndPassword(String email, String password);

}
