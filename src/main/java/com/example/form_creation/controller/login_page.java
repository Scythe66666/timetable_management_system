package com.example.form_creation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.form_creation.Model.UserInfo;
import com.example.form_creation.service.security_services;



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
    public String addNewUser(@RequestBody UserInfo user){
        System.out.println("post controller was called");
        sr.addUser(user);
        return "redirect:/";
    }
    // @PostMapping("/new")
    // public String addNewUser(@RequestParam String name, @RequestParam String password, @RequestParam String email){
    //     String role = "ROLE_ADMIN";
    //     UserInfo user = new UserInfo();
    //     user.setEmail(email);
    //     user.setName(name);
    //     user.setPassword(password);
    //     user.setRoles(role);
    //     System.out.println("post controller was called");
    //     sr.addUser(user);
    //     // return "<h1>yes</h1>";
    //     return "redirect:/";
    // }
    @GetMapping("/signup")
    public String getMethodName(Model model) {
        model.addAttribute("Userinfo", new UserInfo());
        return "signup";
    }

    @GetMapping("/afterLogin")
    public String afterLogin(Model model) {

        return "afterLogin";
    }
    

    
    
}
