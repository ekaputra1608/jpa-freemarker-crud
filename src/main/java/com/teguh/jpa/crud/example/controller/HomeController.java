package com.teguh.jpa.crud.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("pageTitle", "Welcome to JPA-Crud Project");

        return "index";
    }
}
