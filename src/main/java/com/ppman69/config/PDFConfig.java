package com.ppman69.config;

import com.ppman69.services.SpringThymeleafWebjarUserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.xhtmlrenderer.extend.UserAgentCallback;

/**
 * Created by Alex_Lappy_486 on 1/12/17.
 */
@Configuration
public class PDFConfig
{
    @Autowired
    ResourceHttpRequestHandler resourceHttpRequestHandler;

    @Bean
    public UserAgentCallback springThymeleafUserAgentCallback()
    {
        return new SpringThymeleafWebjarUserAgent(resourceHttpRequestHandler);
    }
}
