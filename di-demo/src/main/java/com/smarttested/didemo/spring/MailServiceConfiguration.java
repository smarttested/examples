package com.smarttested.didemo.spring;

import com.smarttested.didemo.service.ApiMailService;
import com.smarttested.didemo.service.MailService;
import com.smarttested.didemo.service.UIMailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Andrei Varabyeu
 */
@Configuration
public class MailServiceConfiguration {

    @Bean(name = "api")
    public MailService apiMailService(){
        return new ApiMailService();
    }

    @Bean(name = "ui")
    public MailService uiMailService(){
        return new UIMailService();
    }

}
