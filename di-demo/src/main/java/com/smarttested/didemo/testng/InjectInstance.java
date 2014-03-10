package com.smarttested.didemo.testng;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * All fields with this annotation will be initialized automatically only in
 * Test Case classes
 * 
 * @author Andrei Varabyeu
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectInstance {

}
