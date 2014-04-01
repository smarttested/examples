package com.smarttested.didemo.guice;

import com.smarttested.didemo.service.MailService;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * Created by andrey.vorobyov on 25/03/14.
 */
@Guice(moduleFactory =  MailServiceModuleFactory.class)
public class ModuleFactoryTest {

    @Inject
    private MailService mailService;


    @Test
    public void testModuleFactory(){
        System.out.println(mailService.getClass());
        System.out.println("test");
    }

}
