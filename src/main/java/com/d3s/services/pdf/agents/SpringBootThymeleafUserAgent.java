package com.d3s.services.pdf.agents;

import com.lowagie.text.Image;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.resource.XMLResource;
import org.xhtmlrenderer.util.XRLog;

import javax.servlet.ServletContext;
import javax.swing.*;
import java.io.IOException;

/**
 * Created by Alex_Lappy_486 on 1/11/17.
 *
 * This class overrides Flying Saucer's default UserAgent to utilize Spring Boot utilities in order to properly resolve
 * static resources that are on the class path.
 */
public class SpringBootThymeleafUserAgent implements UserAgentCallback, ServletContextAware
{
    Logger log = Logger.getLogger(SpringBootThymeleafUserAgent.class);
    private ResourceHttpRequestHandler resourceHandler;
    private ServletContext context;

    public SpringBootThymeleafUserAgent(ResourceHttpRequestHandler resourceHandler)
    {
        this.resourceHandler = resourceHandler;
        XRLog.setLoggingEnabled(true);
    }

    protected Resource findResource(String uri)
    {
        XRLog.setLoggingEnabled(true);
        Resource resource = null;
        if (!uri.endsWith(".eot") && uri != null)
        {
            System.out.println("Checking at: "+resourceHandler.getLocations());
            String uriWithoutContext = uri.replace(context.getContextPath(), "");
            for (ResourceResolver resourceResolver : resourceHandler.getResourceResolvers())
            {
                System.out.println("Checking with "+resourceResolver);
                resource = resourceResolver.resolveResource(null, uri, resourceHandler.getLocations(), null);
                if (resource != null)
                {
                    System.out.println("Regular URI "+uri+" worked!");
                    break;
                }
                else
                {
                    System.out.println("Checking with contextless "+uriWithoutContext);
                    resource = resourceResolver.resolveResource(null, uriWithoutContext, resourceHandler.getLocations(), null);
                    if (resource != null)
                    {
                        System.out.println("context-less URI "+uri+" worked!");
                        break;
                    }
                }
            }
        }
        return resource;
    }


    @Override
    public CSSResource getCSSResource(String uri)
    {
        XRLog.setLoggingEnabled(true);
        CSSResource cssResource = null;
        try {
            Resource springBootResource = findResource(uri);
            if (springBootResource != null)
                cssResource = new CSSResource(springBootResource.getInputStream());
            else
                System.out.println("SKIPPING "+uri+" because its resource was null");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cssResource;
    }

    @Override
    public ImageResource getImageResource(String uri)
    {
        ImageResource imageResource = null;
        try {
            Resource springBootResource = findResource(uri);
            Image image = Image.getInstance(IOUtils.toByteArray(springBootResource.getInputStream()));
            imageResource = new ImageResource(uri, new ITextFSImage(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageResource;
    }

    @Override
    public XMLResource getXMLResource(String uri) {

        XMLResource cssResource = null;
        try {
            Resource springBootResource = findResource(uri);
            cssResource = XMLResource.load(springBootResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cssResource;
    }

    @Override
    public byte[] getBinaryResource(String uri)
    {
        byte[] bytes = new byte[0];
        if (uri != null) {

            try {
                Resource springBootResource = findResource(uri);
                if (springBootResource != null) {
                    bytes = IOUtils.toByteArray(springBootResource.getInputStream());
                }
                else
                    log.warn("Failed to get resource for "+uri+" because the Resource was null");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public boolean isVisited(String uri) {
        return false;
    }

    @Override
    public void setBaseURL(String url) {

    }

    @Override
    public String getBaseURL() {
        return null;
    }

    @Override
    public String resolveURI(String uri) {
        return uri;
    }

    @Override
    public void setServletContext(ServletContext servletContext)
    {
        context = servletContext;
    }
}
