package com.smarttested.didemo.testng;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * Storage for injected instances
 * 
 * @author avarabyeu
 * 
 */
public class ObjectContainer {
	private static Supplier<ObjectContainer> instance = Suppliers
			.memoize(new Supplier<ObjectContainer>() {

				public ObjectContainer get() {
					return new ObjectContainer();
				}
			});

	private Map<Class<?>, Object> instancesCache;

	private ObjectContainer() {
		instancesCache = new HashMap<Class<?>, Object>();
	}

	public static ObjectContainer getInstance() {
		return instance.get();
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<?> clazz) {
		if (!instancesCache.containsKey(clazz)) {
			insertInstanceToCache(clazz);
		}
		return (T) instancesCache.get(clazz);
	}

	public void insertInstanceToCache(Class<?> clazz) {
		instancesCache.put(clazz, createInstance(clazz));
	}

	private Object createInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new InjectionConfigurationException(
					"Cannot create new instance for injection for class '"
							+ clazz.getName() + "'. " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new InjectionConfigurationException(
					"Cannot create new instance for injection for class '"
							+ clazz.getName() + "'. " + e.getMessage());
		}
	}
}
