package com.example.form_creation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form_creation.time_slot;
import com.example.form_creation.timetable_service_layer;

@Controller
public class timetable_page {

  @Autowired
  timetable_service_layer tr;

  time_slot[][] days;

  @GetMapping("/timetable")
  public String getMethodName(
      @RequestParam(required = false) String option,
      Model model) {
    days = tr.create_time_table();
    for (time_slot[] day : days) {
      for (time_slot tm : day) {
        System.out.println(tm.list_lectures);
      }
    }
    List<String> l = tr.getListOfColumns("subject");
    for (String string : l) {
      System.out.println(string);
    }
    // System.out.println("value of option is "+ option);
    model.addAttribute("list_lec", l);
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
            "value of days id is " + days[i][j].getId() + " value of id " + id);
        if (id.equalsIgnoreCase(days[i][j].getId())) {
          flag = 1;
          break;
        }
      }
      if (flag == 1)
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
        "the day is " + day + " the start time is " + start_time);
    tr.add_lecture("TOC", day, start_time, "AC203");
    // TODO: process POST request
    return "redirect:/timetable";
  }

  @GetMapping("/experiment")
  public String Experiment() {
    // TODO: process POST request
    List<String> l = tr.getListOfColumns("subject");
    for (String string : l) {
      System.out.println(string);
    }
    return "redirect:/";
  }

  public String giveDay(int num) {
    String str = null;
    switch (num) {
      case 0:
        str = "Monday";
        break;
      case 1:
        str = "Tuesday";
        break;
      case 2:
        str = "Wednesday";
        break;
      case 3:
        str = "Thursday";
        break;
      case 4:
        str = "Friday";
        break;
      case 5:
        str = "Saturday";
        break;
      case 6:
        str = "Sunday";
        break;
      default:
        str = "Invalid day number";
        break;
    }
    return str;
  }

  @PostMapping("/cancelLecture")
  public String postMethodName(@RequestParam String lecName, @RequestParam String id) {
    time_slot tm;
    int day_count = 0;
    int flag = 0;
    for (time_slot[] day : days) {
      int time_count = 9;
      for (time_slot slot : day) {
        if (id.equalsIgnoreCase(slot.getId()))
        {  
          id.trim(); 
          tr.cancel_lecture(lecName, this.giveDay(day_count), time_count);
          flag = 1;
          break;
        }
        time_count++;
      }
      if(flag == 1)
        break;
      day_count++;
    }

    return "redirect:/timetable";
  }

}
