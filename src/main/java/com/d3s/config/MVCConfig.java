package com.d3s.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Alex_Lappy_486 on 1/10/17.
 */
public class MVCConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware
{
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
