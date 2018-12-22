package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.myapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
