package com.web.myapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.myapp.model.User;
import com.web.myapp.repository.UserRepository;

@RestController
@RequestMapping("rest")
public class UserController {
  @Autowired
  UserRepository userRepository;
  
  @GetMapping("/user/{id}")
  public Optional<User> getUser(@PathVariable("id") int id) {
	  return userRepository.findById(id);
  }
  
  @GetMapping("/users")
  public List<User> getAllUser(){
	  return userRepository.findAll();
  }
  
  @PostMapping("/user")
  public User createUser(@RequestBody User user) {
	  return userRepository.save(user);
  }
  
  @PutMapping("/user/{id}")
  public User updateUser(@PathVariable("id") int id,@RequestBody User user) {
	  user.setId(id);
	  return userRepository.save(user);
  }
  
  @DeleteMapping("/user/{id}")
  public void deleteUser(@PathVariable("id") int id) {
	  userRepository.deleteById(id);
  }
}
