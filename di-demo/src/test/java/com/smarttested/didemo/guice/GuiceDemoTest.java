package com.smarttested.didemo.guice;

import com.smarttested.didemo.service.Api;
import com.smarttested.didemo.service.Gui;
import com.smarttested.didemo.service.MailService;
import org.junit.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * Demo test for showing Guice injection
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
