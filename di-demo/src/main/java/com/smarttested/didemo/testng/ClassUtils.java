package com.smarttested.didemo.testng;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;

/**
 * Utils for work with class instances
 * 
 * @author Andrei Varabyeu
 * 
 */
public class ClassUtils {
	/**
	 * Set value for field. Field may be private
	 * 
	 * @param instance
	 * @param field
	 * @param value
	 */
	public static void setFieldValue(final Object instance, final Field field,
			final Object value) {
		new AccessibleObjectAction(field).execute(new Callable<Void>() {
			public Void call() throws Exception {
				field.set(instance, value);
				return null;
			}
		});
	}

	private static class AccessibleObjectAction {
		private final AccessibleObject ao;

		private AccessibleObjectAction(AccessibleObject ao) {
			this.ao = ao;
		}

		private <T> T execute(Callable<T> callable) {
			try {
				ao.setAccessible(true);
				return callable.call();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
}
