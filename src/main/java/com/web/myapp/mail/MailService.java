package com.web.myapp.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.web.myapp.model.User;

@Service
public class MailService {
private JavaMailSender javaMailSender;

@Autowired
public MailService(JavaMailSender javaMailSender) {
	this.javaMailSender = javaMailSender;
}

public void sendEmail(User user) throws MailException {
	SimpleMailMessage mail=new SimpleMailMessage();
	mail.setTo(user.getEmail());
	mail.setFrom("meetingapp4@gmail.com");
	mail.setSubject("Test Api");
	mail.setText("Hurray");
	
	javaMailSender.send(mail);
}
}
