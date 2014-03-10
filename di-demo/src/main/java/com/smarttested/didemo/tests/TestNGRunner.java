package com.smarttested.didemo.tests;

import org.testng.TestNG;

import com.smarttested.didemo.testng.InjectedObjectsFactoryImpl;

public class TestNGRunner {
	public static void main(String... args) {
		TestNG testNG = new TestNG();
		testNG.setObjectFactory(InjectedObjectsFactoryImpl.class);
		testNG.setTestClasses(new Class[] { TestNgDiTest.class });
		testNG.run();
	}
}
