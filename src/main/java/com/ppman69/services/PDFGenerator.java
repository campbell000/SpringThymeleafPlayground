package com.ppman69.services;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.webjars.WebJarAssetLocator;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Alex_Lappy_486 on 1/11/17.
 */
@Service
public class PDFGenerator
{
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    ResourceLoader loader;

    WebJarAssetLocator webJarsResourceResolver = new WebJarAssetLocator();

    public File generatePDF(ModelAndView mv, HttpServletRequest req, HttpServletResponse resp,
                              File file) throws IOException, DocumentException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariables(mv.getModel());

        String generatedHTML = templateEngine.process(mv.getViewName(), context);
        ITextRenderer renderer = new ITextRenderer();
        SpringThymeleafWebjarUserAgent agent = new SpringThymeleafWebjarUserAgent(renderer.getOutputDevice());
        agent.setResourceLoader(loader);
        agent.setWebjarResolver(webJarsResourceResolver);

        WebJarAssetLocator locator;

        renderer.getSharedContext().setUserAgentCallback(agent);


        renderer.setDocumentFromString(generatedHTML);
        renderer.layout();
        FileOutputStream outputStream = new FileOutputStream(file);
        renderer.createPDF(outputStream);
        outputStream.close();

        return file;
    }
}
