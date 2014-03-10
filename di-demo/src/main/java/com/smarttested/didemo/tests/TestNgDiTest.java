package com.smarttested.didemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.smarttested.didemo.service.MailService;
import com.smarttested.didemo.testng.InjectInstance;

public class TestNgDiTest {

	@InjectInstance
	private MailService mailService;

	@Test
	public void injectService() {
		Assert.assertNotNull(mailService);
		System.out.println("Injected instance is not null");

	}
}
