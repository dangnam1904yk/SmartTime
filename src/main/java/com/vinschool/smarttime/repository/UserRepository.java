package com.vinschool.smarttime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.User;
import com.vinschool.smarttime.model.projection.UserProjection;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = { "role" })
    UserProjection findProjectionByEmail(String email);

    User findByEmail(String email);

    User findByEmailAndIdIsNot(String email, String id);

    User findByEmailAndPassword(String email, String password);

    List<User> findByRoleCodeRole(String code);
}
