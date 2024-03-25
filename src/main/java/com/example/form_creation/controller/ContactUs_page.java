package com.example.form_creation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.form_creation.Model.Contact;
import com.example.form_creation.service.saveContact;
import java.util.List;

@Controller
public class ContactUs_page {


    @Autowired
    private saveContact save;

    @GetMapping("/ContactUs")
    public String requestMethodName(Model model) {
        return "ContactUs";
    }

    // @PostMapping("/saveMsg")
    // public ModelAndView postMethodName(@RequestParam(name = "Name") String Name,
    //         @RequestParam(name = "Mobile no.") String number, @RequestParam(name = "Email") String Email,
    //         @RequestParam String Message) {
    //     System.out.println("name is" +Name);
    //     System.out.println("name is" +number);
    //     System.out.println("name is" +Message);
    //     System.out.println("name is" +Email);
    //     System.out.println("name is" +Name);
    //     return new ModelAndView("redirect:/ContactUs");
    // } 

    @PostMapping("/saveMsg")
    public ModelAndView postMethodName(Contact contact)
    {
        System.out.println(contact); 
        save.saveContacts(contact);
        return new ModelAndView("redirect:/ContactUs");
    }

    @GetMapping("/getContactUs")
    public String getMethodName(Model m){
        List <Contact> li = save.getMessages(); 
        m.addAttribute("list", li);
        return "displayContactUsMessages";
    }
    
}
