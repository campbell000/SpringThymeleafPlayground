package com.ppman69.services;

import com.lowagie.text.Image;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.webjars.WebJarAssetLocator;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.resource.XMLResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Alex_Lappy_486 on 1/11/17.
 *
 * This class overrides Flying Saucer's default UserAgent to utilize Spring Boot utilities in order to properly resolve
 * static resources that are on the class path.
 */
public class SpringThymeleafWebjarUserAgent implements UserAgentCallback
{
    private ResourceHttpRequestHandler resourceHandler;

    public SpringThymeleafWebjarUserAgent(ResourceHttpRequestHandler resourceHandler)
    {
        this.resourceHandler = resourceHandler;
    }

    protected Resource findResource(String uri)
    {
        Resource resource = null;
        for (ResourceResolver resourceResolver : resourceHandler.getResourceResolvers())
        {
            resource = resourceResolver.resolveResource(null, uri, resourceHandler.getLocations(), null);
            if (resource != null)
                break;
        }
        return resource;
    }


    @Override
    public CSSResource getCSSResource(String uri)
    {
        CSSResource cssResource = null;
        try {
            Resource springBootResource = findResource(uri);
            cssResource = new CSSResource(springBootResource.getInputStream());
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
    public byte[] getBinaryResource(String uri) {

        byte[] bytes = new byte[0];
        try {
            Resource springBootResource = findResource(uri);
            bytes = IOUtils.toByteArray(springBootResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
}
