package com.d3s.controllers;

import com.d3s.services.pdf.ThymeleafTemplatePDFGenerator;
import com.d3s.services.pdf.template.HTMLWebTemplate;
import com.d3s.services.pdf.template.WebTemplate;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.resource.ResourceResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class IndexController implements ServletContextAware
{
    private ServletContext servletContext;

    @Autowired
    private ThymeleafTemplatePDFGenerator generator;

    @Autowired
    ResourceHttpRequestHandler resourceHandler;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("HERE");
        model.addAttribute("recipient", "World");
        String[] uris = {
                "/SpringThymeleafPlayground/css/index.css",
                "SpringThymeleafPlayground/css/index.css",
                "css/index.css",
                "/css/index.css",
        };

        for (ResourceResolver resourceResolver : resourceHandler.getResourceResolvers())
        {
            for (String uri : uris)
            {
                Resource resource = resourceResolver.resolveResource(null, uri, resourceHandler.getLocations(), null);
                if (resource == null)
                {
                    System.out.println(uri+" DID NOT WORK!");
                }
                else
                {
                    System.out.println(uri+" DID WORK!");
                }
            }
        }



        return "/home/index";
    }

    @RequestMapping(value="/ajax")
    public @ResponseBody String doThing()
    {
        return "TEST";
    }

    @RequestMapping(value="/pdf")
    public void generatePDF(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String path = "/Users/Alex_Lappy_486/Desktop/test.pdf";
        File f = new File(path);
        ModelAndView mv = new ModelAndView();
        mv.addObject("recipient", "World");
        mv.setViewName("/home/index");

        // Generate PDF
        generator.generatePDFFromTemplate(constructTemplate(mv, req, resp), f);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private WebTemplate constructTemplate(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response)
    {
        WebTemplate template = new HTMLWebTemplate(modelAndView.getViewName(),
                modelAndView.getModel(), request, response);

        return template;
    }
}