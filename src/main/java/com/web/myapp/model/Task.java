package com.web.myapp.model;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(hidden=true)
	private int id;

	private String details;

	@ApiModelProperty(hidden=true)
	@ManyToMany(mappedBy = "task")
	@JsonIgnore
	private List<Meeting> meeting;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(List<Meeting> meeting) {
		this.meeting = meeting;
	}

}
