package com.example.form_creation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class about_page {
    
    @GetMapping("/about")
    public String getMethodName(Model model) {
        return "about";
    }
    
}
