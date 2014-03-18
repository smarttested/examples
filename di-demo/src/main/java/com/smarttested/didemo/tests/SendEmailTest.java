package com.smarttested.didemo.tests;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.smarttested.didemo.model.Email;
import com.smarttested.didemo.service.MailService;
import com.smarttested.didemo.service.UIMailService;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

/**
 * DI Demo Test
 * 
 * @author Andrei Varabyeu
 * 
 */
public class SendEmailTest {

	private String testAddress = "somemailbox@smartested.com";

	private MailService sender = new UIMailService();

	private MailService receiver = new UIMailService();

	@Test
    public void testSending() {

		/*
		 * Prepare random unique postfix. Also we can use UUID there
		 */
		String messageUUID = UUID.randomUUID().toString();

		final Email email = new Email();
		email.setSubject("test subject " + messageUUID);
		email.setBody("test body " + messageUUID);
		email.setTo(testAddress);

		/*
		 * Send email from test mailbox
		 */
		sender.sendEmail(email);

		/*
		 * Receive sent email
		 */
		Optional<Email> received = receiver
				.receiveEmail(new Predicate<Email>() {
					public boolean apply(Email received) {
						/*
						 * Let's assume message equality by body and subject
						 */
						return email.getSubject().equals(received.getSubject())
								&& email.getBody().equals(received.getBody());
					}
				});

		Assert.assertTrue(received.isPresent(), "Message is not delivered");
	}
}
