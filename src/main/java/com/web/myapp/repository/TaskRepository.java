package com.web.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.myapp.model.Meeting;
import com.web.myapp.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
   List<Meeting> findAllByMeetingId(int id);
}
