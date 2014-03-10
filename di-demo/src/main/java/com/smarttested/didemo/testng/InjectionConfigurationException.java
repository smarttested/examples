package com.smarttested.didemo.testng;

/**
 * Exception for Dependency injection
 * 
 * @author Andrei Varabyeu
 */
public class InjectionConfigurationException extends RuntimeException {
	private static final long serialVersionUID = -8303195399115701467L;

	public InjectionConfigurationException(String message) {
		super(message);
	}
}