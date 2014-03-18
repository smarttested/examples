package com.smarttested.didemo.guice;

import com.google.inject.AbstractModule;
import com.smarttested.didemo.service.*;

import java.util.GregorianCalendar;

/**
 * Created by andrey.vorobyov on 16/03/14.
 */
public class MailServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        binder().bind(MailService.class).annotatedWith(Api.class).to(ApiMailService.class);
        binder().bind(MailService.class).annotatedWith(Gui.class).to(UIMailService.class);
    }
}
