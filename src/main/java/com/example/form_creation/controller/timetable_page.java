package com.example.form_creation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.form_creation.*;;


@Controller
public class timetable_page {

    @Autowired
    timetable_service_layer tr;

    @GetMapping("/timetable")
    public String getMethodName(Model model) {
        time_slot [][]days = tr.create_time_table();
        model.addAttribute("time_table", days);
        return "timetable";
    }

}
