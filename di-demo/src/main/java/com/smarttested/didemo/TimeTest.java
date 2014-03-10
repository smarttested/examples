package com.smarttested.didemo;

import java.util.Calendar;

import org.testng.annotations.Test;

public class TimeTest {

	@Test
	public void someTest(){
		System.out.println(Calendar.getInstance().getTimeInMillis());
	}
}
