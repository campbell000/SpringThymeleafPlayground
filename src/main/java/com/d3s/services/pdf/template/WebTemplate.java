package com.d3s.services.pdf.template;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 */
public interface WebTemplate
{
    public String getTemplateFileName();

    public Map<String, Object> getTemplateParameters();

    public HttpServletRequest getServletRequest();

    public HttpServletResponse getServletResponse();
}
