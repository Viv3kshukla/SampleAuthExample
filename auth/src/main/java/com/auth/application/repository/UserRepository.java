package com.auth.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.application.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}