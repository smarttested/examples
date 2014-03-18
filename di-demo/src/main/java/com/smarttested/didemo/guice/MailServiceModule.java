package com.smarttested.didemo.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.smarttested.didemo.service.*;

/**
 * Guice configuration module
 *
 * @author Andrei Varabyeu
 */
public class MailServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        binder().bind(MailService.class).annotatedWith(Api.class).to(ApiMailService.class);
        binder().bind(MailService.class).annotatedWith(Gui.class).to(UIMailService.class);

        binder().bind(MailService.class).annotatedWith(Names.named("api")).to(ApiMailService.class);
        binder().bind(MailService.class).annotatedWith(Names.named("ui")).to(UIMailService.class);

    }
}
