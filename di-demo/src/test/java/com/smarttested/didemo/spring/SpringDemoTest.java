package com.smarttested.didemo.spring;

import com.smarttested.didemo.service.MailService;
import org.junit.Assert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.inject.Named;

@ContextConfiguration(classes = {MailServiceConfiguration.class})
public class SpringDemoTest extends AbstractTestNGSpringContextTests {


    @Inject
    @Named("ui")
    private MailService uiMailService;

    @Inject
    @Named("api")
    private MailService apiMailService;

    @Test
    public void testInjection() {
        Assert.assertNotNull(uiMailService);
        Assert.assertNotNull(apiMailService);
    }
}
