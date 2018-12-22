package com.web.myapp.controller;

import java.util.List;

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
import com.web.myapp.repository.MeetingRepository;

@RestController
@RequestMapping("rest")
public class MeetingController {
 @Autowired
 MeetingRepository meetingRepository;
 
 @GetMapping("/meetings")
 public List<Meeting>getAllUsers() {
	 return meetingRepository.findAll();
 }
 
 @PostMapping("/meeting")
 public Meeting addMeeting(@RequestBody Meeting meeting) {
	 return meetingRepository.save(meeting);
 }
 
 @GetMapping("/meeting/{id}")
 public Meeting getMeeting(@PathVariable("id") int id) {
	 return meetingRepository.getOne(id);
 }
 
 @PutMapping("/meeting/{id}")
 public Meeting updateMeeting(@PathVariable("id") int id,@RequestBody Meeting meeting) {
	 meeting.setId(id);
	 return meetingRepository.save(meeting);
 }
 
 @DeleteMapping("/meeting")
 public void deleteMeeting(@PathVariable("id") int id) {
	 meetingRepository.deleteById(id);
 }
 
 
}
