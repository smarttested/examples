package com.smarttested.didemo.guice;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.smarttested.didemo.service.*;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Guice configuration module
 *
 * @author Andrei Varabyeu
 */
public class MailServiceModule extends AbstractModule {

    public static final String API = "api";
    public static final String UI = "ui";

    @Override
    protected void configure() {
        for (ServiceType serviceType : ServiceType.values()) {
            serviceType.bindByName(binder());
        }
    }

    public static enum ServiceType {

        UI {
            @Override
            void bind(Binder binder) {
                binder.bind(MailService.class).to(UIMailService.class);
            }

            @Override
            void bindByName(Binder binder) {
                binder.bind(MailService.class).annotatedWith(Gui.class).to(UIMailService.class);
                binder.bind(MailService.class).annotatedWith(Names.named(MailServiceModule.UI)).to(UIMailService.class);
            }
        },

        API {
            @Override
            void bind(Binder binder) {
                binder.bind(MailService.class).to(ApiMailService.class);

            }

            @Override
            void bindByName(Binder binder) {
                binder.bind(MailService.class).annotatedWith(Api.class).to(ApiMailService.class);
                binder.bind(MailService.class).annotatedWith(Names.named(MailServiceModule.API)).to(ApiMailService.class);

                binder.bindInterceptor(Matchers.any(), Matchers.any(), new MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                        Stopwatch timer = Stopwatch.createStarted();
                        Object proceed = methodInvocation.proceed();
                        System.out.println("Method: " + methodInvocation.getMethod().getName() + " ; Time: " + timer.stop().toString());
                        return proceed;
                    }
                });
            }
        };

        abstract void bind(Binder binder);

        abstract void bindByName(Binder binder);


        public static Optional<ServiceType> fromString(String serviceType) {
            if (null == serviceType) {
                return Optional.absent();
            }
            String serviceTypeUpperCase = serviceType.toUpperCase();
            for (ServiceType type : ServiceType.values()) {
                if (type.name().equals(serviceTypeUpperCase)) {
                    return Optional.of(type);
                }
            }
            return Optional.absent();
        }
    }
}
