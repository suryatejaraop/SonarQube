package com.smtp.mail.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smtp.mail.service.MailService;

@RestController
public class MailSendController {
	@Autowired
	private MailService mailservice;
	@GetMapping(value = "/sendemail")
	   public String sendEmail() throws MessagingException, IOException {
		mailservice.sendmail();

	      return "Email sent successfully";
	   } 
}
