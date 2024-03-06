package com.example.form_creation.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class login_page {

    @GetMapping("/login")
    public String displayLoginPage(Model model)    
    {
        return "login";
    }
}
