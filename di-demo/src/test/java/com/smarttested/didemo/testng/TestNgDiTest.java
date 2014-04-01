package com.smarttested.didemo.testng;

import com.smarttested.didemo.service.ApiMailService;
import com.smarttested.didemo.service.MailService;
import org.testng.TestNG;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class TestNgDiTest {


    /**
     * Use public static void main, because we need to set {@link org.testng.IObjectFactory}
     *
     * @param args
     */
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setObjectFactory(InjectedObjectsFactoryImpl.class);
        testNG.setTestClasses(new Class[]{TestToBeExecuted.class});
        testNG.run();
    }


    public static class TestToBeExecuted {

        @InjectInstance
        private MailService mailService;

        @Test
        public void testInjection() {
            assertNotNull(mailService);
        }
    }
}
