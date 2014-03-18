package com.smarttested.didemo.guice;

import com.google.inject.Inject;
import com.smarttested.didemo.guice.MailServiceModule;
import com.smarttested.didemo.service.Api;
import com.smarttested.didemo.service.Gui;
import com.smarttested.didemo.service.MailService;
import org.junit.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * Created by andrey.vorobyov on 16/03/14.
 */
@Guice(modules = {MailServiceModule.class})
public class GuiceDemoTest {

    @Inject
    @Gui
    private MailService uiMailService;

    @Inject
    @Api
    private MailService apiMailService;

    @Test
    public void testInjection(){
        Assert.assertNotNull(uiMailService);
        Assert.assertNotNull(apiMailService);
    }
}
