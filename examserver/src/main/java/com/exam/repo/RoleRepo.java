package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
