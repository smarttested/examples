package com.smarttested.didemo.service;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binding annotation for API-related services
 *
 * @author Andrei Varabyeu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@BindingAnnotation
public @interface Api {

}
