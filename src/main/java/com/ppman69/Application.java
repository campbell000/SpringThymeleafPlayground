package com.ppman69;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Alex_Lappy_486 on 1/10/17.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(Application.class);
    }
}
