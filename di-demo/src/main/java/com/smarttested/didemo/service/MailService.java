package com.smarttested.didemo.service;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.smarttested.didemo.model.Email;

/**
 * Set of operations represent mail service
 * 
 * @author Andrei Varabyeu
 * 
 */
public interface MailService {

	/**
	 * Sends email
	 * 
	 * @param email
	 */
	void sendEmail(Email email);

	/**
	 * Receives all emails from mailbox
	 * 
	 * @return
	 */
	List<Email> receiveAllEmails();

	/**
	 * Receives email by provided predicate
	 * 
	 * @param predicate
	 * @return - {@link Optional} of email
	 */
	Optional<Email> receiveEmail(Predicate<Email> predicate);
}
