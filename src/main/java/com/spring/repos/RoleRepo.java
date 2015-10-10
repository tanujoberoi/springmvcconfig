package com.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.UserRole;

public interface RoleRepo  extends JpaRepository<UserRole, Long> {

}
