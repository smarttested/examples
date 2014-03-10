package com.smarttested.didemo.service;

import java.util.Collections;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.smarttested.didemo.model.Email;

public class ApiMailService implements MailService {

	public void sendEmail(Email email) {
		org.apache.commons.mail.Email mailToSend = new SimpleEmail();
		try {
			mailToSend.setFrom(email.getFrom());
			mailToSend.setTo(Collections.singletonList(email.getTo()));
			mailToSend.setSubject(email.getSubject());
			mailToSend.setMsg(email.getBody());
			mailToSend.send();
		} catch (EmailException e) {
			throw new RuntimeException("Unable to send email", e);
		}

	}

	public List<Email> receiveAllEmails() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Email> receiveEmail(Predicate<Email> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

}
