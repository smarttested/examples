package com.smarttested.didemo.testng;

import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.Reflections;

/**
 * Basic implementation of Test Case Instance processor
 * 
 * @author Andrei Varabyeu
 * 
 */
public class InstanceProcessorImpl implements IInstanceProcessor {

	public void process(Object instance) {
		Field[] fields = instance.getClass().getDeclaredFields();
		for (Field field : fields) {
			Object fieldInstance = null;
			if (field.isAnnotationPresent(InjectInstance.class)) {
				if (field.getType().isInterface()) {
					Reflections reflections = new Reflections("");
					Set<?> implementations = reflections.getSubTypesOf(field
							.getType());
					if (implementations.isEmpty()) {
						throw new InjectionConfigurationException(
								"Cannot inject interface instance since there are no implementation in classpath");
					}

					fieldInstance = InstancesStorage.getInstance()
							.createInstance(
									(Class<?>) implementations.iterator()
											.next());
				} else {
					fieldInstance = InstancesStorage.getInstance()
							.createInstance(field.getType());
				}

				process(fieldInstance);
				ClassUtils.setFieldValue(instance, field, fieldInstance);
			}
		}
	}
}
