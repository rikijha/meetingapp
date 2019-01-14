package com.web.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.myapp.mail.MailService;
import com.web.myapp.model.Credential;
import com.web.myapp.model.User;
import com.web.myapp.repository.CredentialRespository;
import com.web.myapp.repository.UserRepository;

@RestController
@RequestMapping("rest")
public class UserController {
  @Autowired
  UserRepository userRepository;
  
  @Autowired
  CredentialRespository credentialRespository;
  
  Map<String, Integer> map=new HashMap<>();
  
  @Autowired
  private MailService mailService;
  
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
	  User u=userRepository.getOne(id);
	  u.setName(user.getName());
	  u.setEmail(user.getEmail());
	  u.setRole(user.getRole());
	  return userRepository.save(u);
  }
  
  @DeleteMapping("/user/{id}")
  public void deleteUser(@PathVariable("id") int id) {
	  userRepository.deleteById(id);
  }
  
  @GetMapping("/user/generatecode/{email}")
  public int generateCode(@PathVariable("email") String email) {
	  int code=new Random().nextInt(100000);
	  User user=userRepository.findByEmail(email);
	  mailService.sendEmail(user);
	  map.put(email, code);
	  return code;
	  
  }
  
  @GetMapping("/user/verifyCode/{email}/{code}")
  public boolean verifyCode(@PathVariable("email") String email,@PathVariable("code") int code) {
	  for(Map.Entry<String, Integer> e:map.entrySet()) {
		  if(e.getKey().equals(email)) {
			  if(e.getValue()==code) {
				  return true;
			  }
		  }
	  }
	  return false;
  }
  
  @PostMapping("/user/setpassword/{email}")
  public User setPassword(@PathVariable("email") String email,@RequestBody Credential credential) {
	  User user=userRepository.findByEmail(email);
	  credential.setUsername(user.getEmail());
	  Credential c=credentialRespository.save(credential);
	  user.setCredential(c);
	  return userRepository.save(user);
  }
  
  @PostMapping("/user/authenticate")
  public User validateUser(@RequestBody Credential credential) {
	  Credential c=credentialRespository.findByUsername(credential.getUsername());
	  if(c.getPassword().equals(credential.getPassword())) {
		  return userRepository.findByEmail(credential.getUsername());
	  } 
	  return null;
  }
  
}
