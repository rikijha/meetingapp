package com.web.myapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ApiModelProperty(hidden=true)
	private int id;
    private String title;
    private String venue;
    
    private String description;
    
    @ManyToOne
    @ApiModelProperty(hidden=true)
    private User user;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<User> attendees;
    
    public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@CreationTimestamp
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="ddMMyyyy")
    @ApiModelProperty(hidden=true)
    private Date date;
    
    public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToMany
    @JoinTable(name="meeting_task",joinColumns=@JoinColumn(name="meeting_id",referencedColumnName="id"),
               inverseJoinColumns=@JoinColumn(name="task_id",referencedColumnName="id"))
    @ApiModelProperty(hidden=true)
    private List<Task> task;
    
	
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
    
}
