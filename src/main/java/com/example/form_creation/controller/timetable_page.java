package com.example.form_creation.controller;

import com.example.form_creation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class timetable_page {

  @Autowired
  timetable_service_layer tr;

  time_slot[][] days;

  @GetMapping("/timetable")
  public String getMethodName(
    @RequestParam(required = false) String option,
    Model model
  ) {
    days = tr.create_time_table();
    for (time_slot[] day : days) {
      for (time_slot tm : day) {
        System.out.println(tm.list_lectures);
      }
    }
    // System.out.println("value of option is "+  option);
    model.addAttribute("option", option);
    model.addAttribute("time_table", days);
    return "timetable";
  }

  @PostMapping("/add_lecture")
  public String postMethodName(@RequestParam String id) {
    int i = 0, j = 0;
    id.trim();
    int flag = 0;
    for (i = 0; i < days.length; i++) {
      for (j = 0; j < days[i].length; j++) {
        System.out.println(
          "value of days id is " + days[i][j].getId() + " value of id " + id
        );
        if (id.equalsIgnoreCase(days[i][j].getId())) 
        {
            flag = 1;
            break;
        }
      }
      if(flag == 1)
        break;
    }
    String[] arr = {
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
      "Sunday",
    };
    String day = arr[i];
    int start_time = j + 9;
    System.out.println(
      "the day is " + day + " the start time is " + start_time
    );
    tr.add_lecture("TOC", day, start_time, "AC203");
    //TODO: process POST request
    return "redirect:/timetable";
  }
}
