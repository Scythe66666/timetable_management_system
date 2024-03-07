package com.example.form_creation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class home_page {
    @GetMapping("/home")
    public String homePage(Model model) {
        return "index";
    }
     
}
