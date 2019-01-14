package com.web.myapp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.myapp.model.Meeting;
import com.web.myapp.model.User;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

	@Modifying
	@Transactional
	@Query(value="insert into meeting_task values(:mid,:tid)",nativeQuery=true)
	void assignTask(@Param("mid") int mid,@Param("tid") int tid);
	
	
	@Modifying
	@Transactional
	@Query(value="insert into meeting_attendees values(:mid,:uid)",nativeQuery=true)
	void assignAttendee(@Param("mid") int mid,@Param("uid") int uid);
	
	List<Meeting> findAllByUserId(int id);
	
	List<Meeting> findAllByAttendees(User user);
}
