package com.web.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.myapp.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

}
