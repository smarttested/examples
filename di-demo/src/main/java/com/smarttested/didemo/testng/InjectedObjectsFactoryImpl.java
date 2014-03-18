package com.smarttested.didemo.testng;

import org.reflections.Reflections;
import org.testng.internal.ObjectFactoryImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Implementation of testNG object factory interface
 *
 * @author Andrei Varabyeu
 */
public class InjectedObjectsFactoryImpl extends ObjectFactoryImpl {

    private static final long serialVersionUID = 3910780189824500310L;


    @SuppressWarnings("rawtypes")
    public Object newInstance(Constructor paramConstructor,
                              Object... paramArrayOfObject) {
        Object testInstance = super.newInstance(paramConstructor,
                paramArrayOfObject);
        process(testInstance);
        return testInstance;
    }

    public void process(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object fieldInstance;
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
