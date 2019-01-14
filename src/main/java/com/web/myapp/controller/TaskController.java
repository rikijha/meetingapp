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

import com.web.myapp.model.Meeting;
import com.web.myapp.model.Task;
import com.web.myapp.repository.TaskRepository;

@RestController
@RequestMapping("rest")
public class TaskController {
  @Autowired
  private TaskRepository taskRepository;
  
  @GetMapping("/tasks/{id}")
  public List<Meeting> getAllMeeting(@PathVariable("id") int meeting_id){
	  return taskRepository.findAllByMeetingId(meeting_id);
  }
  
  @GetMapping("/task/{id}")
  public Optional<Task> getTask(@PathVariable("id") int id) {
	  return taskRepository.findById(id);
  }
  
  @GetMapping("/task")
  public List<Task> getAllTask(){
	  return taskRepository.findAll();
  }
  
  @PostMapping("/task")
  public Task addTask(@RequestBody Task task) {
	  return taskRepository.save(task);
  }
  
  @PutMapping("task/{id}")
  public Task updateTask(@PathVariable("id") int id,@RequestBody Task task) {
	  task.setId(id);
	  return taskRepository.save(task);
  }
  
  @DeleteMapping("task/{id}")
  public void deletTask(@PathVariable("id") int id) {
	  taskRepository.deleteById(id);
  }
}
