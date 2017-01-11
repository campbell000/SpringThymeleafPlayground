package com.ppman69.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class IndexController {

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
}