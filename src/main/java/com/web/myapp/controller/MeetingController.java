package com.web.myapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.web.myapp.model.User;
import com.web.myapp.repository.MeetingRepository;
import com.web.myapp.repository.TaskRepository;
import com.web.myapp.repository.UserRepository;

@RestController
@RequestMapping("rest")
public class MeetingController {
 @Autowired
 MeetingRepository meetingRepository;
 
 @Autowired
 UserRepository userRepository;
 
 @Autowired
 TaskRepository taskRepository;
 
 
 
 
 @GetMapping("/meetings/{userId}")
 public Set<Meeting>getAllMeetings(@PathVariable("userId") int userId) {
	 User user=userRepository.getOne(userId);
	 List<Meeting> m1=meetingRepository.findAllByUserId(userId);
	 m1.addAll(meetingRepository.findAllByAttendees(user));
	 Set<Meeting> meetings=new HashSet<>(m1);
	 return meetings;
 }
 
 
 @PostMapping("/meeting/{userId}")
 public Meeting addMeeting(@PathVariable("userId") int userId,@RequestBody Meeting meeting) {
	 User user=userRepository.getOne(userId);
	 meeting.setUser(user);
	 return meetingRepository.save(meeting);
 }
 
 @GetMapping("/meeting/{id}")
 public Meeting getMeeting(@PathVariable("id") int id) {
	 return meetingRepository.getOne(id);
 }
 
 @PutMapping("/meeting/{id}")
 public Meeting updateMeeting(@PathVariable("id") int id,@RequestBody Meeting meeting) {
	 Meeting m=meetingRepository.getOne(id);
	 m.setTitle(meeting.getTitle());
	 m.setDescription(meeting.getDescription());
	 m.setVenue(meeting.getVenue());
	 return meetingRepository.save(m);
 }
 
 @DeleteMapping("/meeting/{id}")
 public void deleteMeeting(@PathVariable("id") int id) {
	 meetingRepository.deleteById(id);
 }
 
@PostMapping("/assigntask/{meetingId}/{taskId}") 
 public void assignTask(@PathVariable("meetingId") int mid,@PathVariable("taskId") int tid) {
	meetingRepository.assignTask(mid, tid);
}

@DeleteMapping("/meeting/deletetask/{meetingId}/{taskId}")
public Meeting deleteTask(@PathVariable("meetingId") int mid,@PathVariable("taskId") int tid) {
	Task task=taskRepository.getOne(tid);
	Meeting meeting=meetingRepository.getOne(mid);
	meeting.getTask().remove(task);
	return meetingRepository.save(meeting);
}

@PostMapping("/assignAttendee/{meetingId}/{userId}") 
public void assignAttendee(@PathVariable("meetingId") int mid,@PathVariable("userId") int uid) {
	meetingRepository.assignAttendee(mid, uid);
}

@DeleteMapping("/meeting/deleteattendee/{meetingId}/{userId}")
public Meeting deleteAttendee(@PathVariable("meetingId") int mid,@PathVariable("userId") int uid) {
	User user=userRepository.getOne(uid);
	Meeting meeting=meetingRepository.getOne(mid);
	meeting.getAttendees().remove(user);
	return meetingRepository.save(meeting);
}
 
}
