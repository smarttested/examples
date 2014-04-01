package com.smarttested.didemo.guice;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.smarttested.didemo.service.*;

/**
 * Created by andrey.vorobyov on 25/03/14.
 */
public class MailServiceConfigurableModule extends AbstractModule {


    private final Optional<MailServiceModule.ServiceType> serviceType;

    public MailServiceConfigurableModule(String serviceTypeString){
        Optional<MailServiceModule.ServiceType> serviceType = MailServiceModule.ServiceType.fromString(serviceTypeString);
        Preconditions.checkArgument(serviceType.isPresent(), "Incorrect service type provided: " + serviceTypeString);
        this.serviceType = serviceType;

    }


    @Override
    protected void configure() {
        serviceType.get().bind(binder());
    }
}
