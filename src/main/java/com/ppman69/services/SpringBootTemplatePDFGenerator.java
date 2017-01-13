package com.ppman69.services;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.support.WebContentGenerator;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.webjars.WebJarAssetLocator;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex_Lappy_486 on 1/11/17.
 */
@Service
public class SpringBootTemplatePDFGenerator
{
    /* This object is used for rendering the HTML from a Thymeleaf Template */
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    UserAgentCallback userAgentCallback;

    public SpringBootTemplatePDFGenerator(SpringTemplateEngine templateEngine, UserAgentCallback userAgentCallback)
    {
        this.templateEngine = templateEngine;
        this.userAgentCallback = userAgentCallback;
    }

    public File generatePDF(ModelAndView mv, HttpServletRequest req, HttpServletResponse resp,
                              File file) throws IOException, DocumentException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariables(mv.getModel());

        String generatedHTML = templateEngine.process(mv.getViewName(), context);
        System.out.println(generatedHTML);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getSharedContext().setUserAgentCallback(userAgentCallback);


        renderer.setDocumentFromString(generatedHTML);
        renderer.layout();
        FileOutputStream outputStream = new FileOutputStream(file);
        renderer.createPDF(outputStream);
        outputStream.close();

        return file;
    }
}
