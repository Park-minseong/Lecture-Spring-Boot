package com.spring.boardWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boardWeb.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
