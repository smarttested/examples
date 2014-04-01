package com.smarttested.didemo.guice;

import com.smarttested.didemo.service.Api;
import com.smarttested.didemo.service.Gui;
import com.smarttested.didemo.service.MailService;
import org.junit.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo test for showing Guice injection
 */
@Guice(modules = {MailServiceModule.class})
public class GuiceDemoTest {

    @Inject
    @Gui
    private MailService uiMailService;

    @Inject
    //@Api
    @Named("api")
    private MailService apiMailService;

    @Test
    public void testInjection(){
        uiMailService.receiveAllEmails();
        Assert.assertNotNull(uiMailService);
        Assert.assertNotNull(apiMailService);
    }
}
