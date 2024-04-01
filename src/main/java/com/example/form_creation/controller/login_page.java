package com.example.form_creation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.form_creation.Model.UserInfo;
import com.example.form_creation.service.security_services;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class login_page {

    @Autowired
    private security_services sr;
    @GetMapping("/login")
    public String displayLoginPage(Model model)    
    {
        return "login";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute UserInfo user){
        System.out.println("post controller was called");
        sr.addUser(user);
        return "redirect:/";
    }
    @GetMapping("/signup")
    public String getMethodName(Model model) {
        model.addAttribute("Userinfo", new UserInfo(1, "asfaf", "fasdaf", "asfdasdf","fafad"));
        return "signup";
    }
    
}
