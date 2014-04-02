package com.smarttested.didemo.jdk8;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Provides useful conversion methods between Google Guava functional API classes and native provided by JDK8
 * Use it to gradually move legacy code under new JVM version
 *
 * @author Andrei Varabyeu
 */
public final class FunctionalConverter {

    private FunctionalConverter() {
        /*utility class. no reason to create instance*/
    }

    /**
     * Converts Guava's {@link com.google.common.base.Predicate} to JDK8 {@link java.util.function.Predicate}
     *
     * @param predicate Guava predicate
     * @param <T>       Type predicate applicable to
     * @return JDK8 {@link java.util.function.Predicate}
     */
    public static <T> Predicate<T> asJdk(com.google.common.base.Predicate<T> predicate) {
        return (t -> predicate.apply(t));
    }

    /**
     * Converts Guava's {@link com.google.common.base.Function} to JDK8 {@link java.util.function.Function}
     *
     * @param function Guava {@link com.google.common.base.Function}
     * @param <T> Type function applicable to
     * @param <R> Function return type
     * @return JDK8 {@link java.util.function.Function}
     */
    public static <T, R> Function<T, R> asJdk(com.google.common.base.Function<T, R> function) {
        return (t -> function.apply(t));
    }

    /**
     * Converts JDK8 {@link java.util.function.Predicate} to Guava's {@link com.google.common.base.Predicate}
     *
     * @param predicate JDK8 {@link java.util.function.Predicate}
     * @param <T> Type predicate applicable to
     * @return Guava's {@link com.google.common.base.Predicate}
     */
    public static <T> com.google.common.base.Predicate<T> asGuava(Predicate<T> predicate) {
        return (t -> predicate.test(t));
    }

    /**
     * Converts JDK8 {@link java.util.function.Function} to Guava's {@link com.google.common.base.Function}
     *
     * @param function  JDK8 {@link java.util.function.Function}
     * @param <T> Type function applicable to
     * @param <R> Function return type
     * @return Guava's {@link com.google.common.base.Function}
     */
    public static <T, R> com.google.common.base.Function<T, R> asGuava(Function<T, R> function) {
        return (t -> function.apply(t));
    }
}
