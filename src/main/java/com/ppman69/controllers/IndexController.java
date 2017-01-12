package com.ppman69.controllers;

import com.lowagie.text.DocumentException;
import com.ppman69.services.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    PDFGenerator generator;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("HERE");
        model.addAttribute("recipient", "World");
        return "/home/index";
    }

    @RequestMapping(value="/ajax")
    public @ResponseBody String doThing()
    {
        return "TEST";
    }

    @RequestMapping(value="/pdf")
    public void generatePDF(HttpServletRequest req, HttpServletResponse resp) throws IOException, DocumentException {
        String path = "/Users/Alex_Lappy_486/Desktop/test.pdf";
        File f = new File(path);
        ModelAndView mv = new ModelAndView();
        mv.addObject("recipient", "World");
        mv.setViewName("/home/index");
        generator.generatePDF(mv, req, resp, f);
    }
}