package com.ppman69.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.webjars.WebJarAssetLocator;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;
import org.xhtmlrenderer.resource.CSSResource;
import org.xhtmlrenderer.resource.ImageResource;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.IOException;

/**
 * Created by Alex_Lappy_486 on 1/11/17.
 *
 * This class is capable of resolving URLS embedded in HTML that request for static content, as well as webjars located
 * in the classpath
 */
public class SpringThymeleafWebjarUserAgent extends ITextUserAgent {

    public SpringThymeleafWebjarUserAgent(ITextOutputDevice outputDevice) {
        super(outputDevice);
    }

    private ResourceLoader loader = null;

    private WebJarAssetLocator webjarResolver;

    public void setWebjarResolver(WebJarAssetLocator resolver)
    {
        this.webjarResolver = resolver;
    }

    public void setResourceLoader(ResourceLoader loader)
    {
        this.loader = loader;
    }

    @Override
    public CSSResource getCSSResource(String uri)
    {
        CSSResource resource = null;
        try {
            String resolvedURI = resolveUri(uri);
            resource = new CSSResource(new ClassPathResource(resolvedURI).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String resolveUri(String uri)
    {
        String allLowercase = uri.toLowerCase();
        if (allLowercase.contains("webjar"))
        {
            uri = this.webjarResolver.getFullPath(uri);
        }
        return uri;
    }

    @Override
    public ImageResource getImageResource(String uri) {
        return null;
    }

    @Override
    public XMLResource getXMLResource(String uri) {
        return null;
    }

    @Override
    public byte[] getBinaryResource(String uri) {
        return new byte[0];
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
