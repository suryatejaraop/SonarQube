package com.smtp.mail.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service

public class MailService {
	public void sendmail() throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "125.21.157.22");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("suryateja.p@1digitals.com", "surya@0291");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("suryateja.p@1digitals.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("suryateja0291@gmail.com"));
		msg.setSubject("Testing");
		msg.setContent("Surya is testing an API", "text/html");
		msg.setSentDate(new Date());
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Tutorials point email", "text/html");


		/*
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart(); attachPart.attachFile("d:/images/Screenshot(1).png");
		 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 */

		Transport.send(msg);   
	}
}
