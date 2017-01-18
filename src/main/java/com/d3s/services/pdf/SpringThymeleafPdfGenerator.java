package com.d3s.services.pdf;

import com.d3s.services.pdf.agents.SpringBootThymeleafUserAgent;
import com.d3s.services.pdf.template.WebTemplate;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.XRLog;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 */
public class SpringThymeleafPdfGenerator extends BasicHtmlPdfGenerator implements ThymeleafTemplatePDFGenerator, ServletContextAware
{
    private ITemplateEngine templateEngine;

    private UserAgentCallback callbackAgent;

    private ServletContext context;

    public SpringThymeleafPdfGenerator(ResourceHttpRequestHandler resourceHandler, ITemplateEngine templateEngine, UserAgentCallback callbackAgent)
    {
        XRLog.setLoggingEnabled(true);
        this.templateEngine = templateEngine;
        this.callbackAgent = callbackAgent;
    }

    @Override
    public File generatePDFFromTemplate(WebTemplate template, File outputFile) throws Exception
    {
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        generatePDFFromTemplate(template, outputStream);

        return outputFile;
    }

    @Override
    public void generatePDFFromTemplate(WebTemplate template, OutputStream outputStream) throws Exception
    {
        ServletContext context = template.getServletRequest().getServletContext();
        WebContext webContext = new WebContext(template.getServletRequest(), template.getServletResponse(), context);
        webContext.setVariables(template.getTemplateParameters());

        String html = templateEngine.process(template.getTemplateFileName(), webContext);
        generatePDF(html, outputStream);
    }

    @Override
    public void adjustITextRenderer(ITextRenderer renderer)
    {
        renderer.getSharedContext().setUserAgentCallback(this.callbackAgent);
        renderer.getSharedContext().setMedia("all");
        renderer.getSharedContext().setPrint(true);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
}
