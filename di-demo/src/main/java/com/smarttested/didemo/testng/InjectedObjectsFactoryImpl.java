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


    public Object newInstance(Constructor paramConstructor,
                              Object... paramArrayOfObject) {
        Object testInstance = super.newInstance(paramConstructor,
                paramArrayOfObject);
        process(testInstance);
        return testInstance;
    }

    public void process(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();

        /* go through all class fields */
        for (Field field : fields) {
            Object fieldInstance;

            /* if we need to inject instance into this field */
            if (field.isAnnotationPresent(InjectInstance.class)) {

                /* if this field is an interface we need to find at least one implementation  */
                if (field.getType().isInterface()) {

                    /**
                     * Using Reflections library to find implementations
                     * @see <a href="https://code.google.com/p/reflections/">Reflections</a>
                     *
                     */
                    Reflections reflections = new Reflections("");
                    Set<?> implementations = reflections.getSubTypesOf(field
                            .getType());
                    if (implementations.isEmpty()) {
                        throw new InjectionConfigurationException(
                                "Cannot inject interface instance since there are no implementation in classpath");
                    }

                    /* once we find implementation class, let's create instance. We don't care about instance we need
                     to inject so let's inject first one
                     */
                    fieldInstance = InstancesStorage.getInstance()
                            .getObject(
                                    (Class<?>) implementations.iterator()
                                            .next()
                            );
                } else {
                    /* if this field is not an interface we are able to create new instance of it */
                    fieldInstance = InstancesStorage.getInstance()
                            .getObject(field.getType());
                }

                /* process just created instance, because it may also contain fields which need to be injected */
                process(fieldInstance);

                /* set just created object to test class instance */
                ClassUtils.setFieldValue(instance, field, fieldInstance);
            }
        }
    }
}
