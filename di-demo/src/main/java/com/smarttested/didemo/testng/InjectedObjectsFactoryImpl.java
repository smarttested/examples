package com.smarttested.didemo.testng;

import java.lang.reflect.Constructor;

import org.testng.internal.ObjectFactoryImpl;

/**
 * 
 * Implementation of testNG object factory interface
 * 
 * @author Andrei Varabyeu
 * 
 */
public class InjectedObjectsFactoryImpl extends ObjectFactoryImpl {

	private static final long serialVersionUID = 3910780189824500310L;

	private IInstanceProcessor instanceProcessor = new InstanceProcessorImpl();

	@SuppressWarnings("rawtypes")
	public Object newInstance(Constructor paramConstructor,
			Object... paramArrayOfObject) {
		Object testInstance = super.newInstance(paramConstructor,
				paramArrayOfObject);
		instanceProcessor.process(testInstance);
		return testInstance;
	}
}
