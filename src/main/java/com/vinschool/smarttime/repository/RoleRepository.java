package com.vinschool.smarttime.repository;

import com.vinschool.smarttime.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByNameRole(String namRole);

    Role findByNameRoleAndIdIsNot(String name, String id);

    Role findByCodeRole(String code);
}
