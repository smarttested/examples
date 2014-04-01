package com.smarttested.didemo.testng;

import com.smarttested.didemo.service.MailService;
import org.testng.TestNG;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TestNgDiTest {


    @Test
    public void injectService() {
        TestNG testNG = new TestNG();
        testNG.setObjectFactory(InjectedObjectsFactoryImpl.class);
        testNG.setTestClasses(new Class[]{TestToBeExecuted.class});
        testNG.run();
    }


    private static class TestToBeExecuted {
        @InjectInstance
        private MailService mailService;

        @Test
        public void injectService() {
            assertNotNull(mailService);
        }
    }
}
