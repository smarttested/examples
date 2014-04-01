package com.smarttested.didemo.guice;

import com.google.common.base.Joiner;
import com.google.inject.Module;
import org.testng.IModuleFactory;
import org.testng.ITestContext;

import java.util.Map;

/**
 * @author Andrei Varabyeu
 */
public class MailServiceModuleFactory implements IModuleFactory {

    public static final String SERVICE_TYPE_PARAMETER = "serviceType";

    @Override
    public Module createModule(ITestContext iTestContext, Class<?> aClass) {
        Map<String, String> allParameters = iTestContext.getCurrentXmlTest().getAllParameters();
        return new MailServiceConfigurableModule(allParameters.get(SERVICE_TYPE_PARAMETER));
    }
}
