package com.d3s.config;

import com.d3s.services.pdf.SpringThymeleafPdfGenerator;
import com.d3s.services.pdf.ThymeleafTemplatePDFGenerator;
import com.d3s.services.pdf.agents.SpringBootThymeleafUserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.swing.*;

/**
 * Created by Alex_Lappy_486 on 1/12/17.
 */
@Configuration
public class PDFConfig
{
    @Autowired
    ResourceHttpRequestHandler resourceHttpRequestHandler;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Bean
    public ThymeleafTemplatePDFGenerator templatePDFGenerator()
    {
        ThymeleafTemplatePDFGenerator pdfGenerator =
                new SpringThymeleafPdfGenerator(resourceHttpRequestHandler, templateEngine, springBootThymeleafUserAgent());

        return pdfGenerator;
    }

    @Bean
    @Scope("prototype")
    public SpringBootThymeleafUserAgent springBootThymeleafUserAgent()
    {
        return new SpringBootThymeleafUserAgent(resourceHttpRequestHandler);
    }
}
