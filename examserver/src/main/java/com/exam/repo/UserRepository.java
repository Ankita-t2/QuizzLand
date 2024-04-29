package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User ,Long> {
	public User findByUserName(String username);




}
