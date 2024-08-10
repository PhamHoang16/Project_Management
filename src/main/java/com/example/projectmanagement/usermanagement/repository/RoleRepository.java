package com.example.projectmanagement.usermanagement.repository;

import com.example.projectmanagement.usermanagement.entity.Role;
import com.example.projectmanagement.usermanagement.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
