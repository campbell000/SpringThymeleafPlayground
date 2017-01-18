package com.d3s.services.pdf.template;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 */
public class HTMLWebTemplate implements WebTemplate
{
    private String templateFileName;
    private Map<String, Object> templateParameters;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;

    public HTMLWebTemplate(String templateFileName, Map<String, Object> templateParameters, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        this.templateFileName = templateFileName;
        this.templateParameters = templateParameters;
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    @Override
    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    @Override
    public Map<String, Object> getTemplateParameters() {
        return templateParameters;
    }

    public void setTemplateParameters(Map<String, Object> templateParameters) {
        this.templateParameters = templateParameters;
    }

    @Override
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }
}
