package com.example.form_creation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
  time_slot[][] days, days2;

  UserDetails userDetails;

  @GetMapping("/AddLecture")
  public String AddLecture(Model model) {
    List<String> l_classes = tr.getListOfColumns("Class");
    List<String> l_subjects = tr.getListOfColumns("Subject");
    System.out.println();
    l_classes.remove("NONE");
    model.addAttribute("list_classes", l_classes);
    model.addAttribute("list_subjects", l_subjects);
    return "AddLecture";
  }

  @GetMapping("/main")
  public String mainMethod(Model model, @RequestParam(required = false) String option) {
    // Get the Authentication object
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // Extract user details from Authentication object
    userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    model.addAttribute("username", username);
    String query2 = "SELECT * FROM main WHERE Teacher = '" + username + "'";
    days2 = tr.create_time_table(query2);
    List<String> l_subjects = tr.getListOfColumns("Subject");
    l_subjects.remove("NONE");
    model.addAttribute("list_subjects", l_subjects);

    for (time_slot[] day : days2) {
      for (time_slot tm : day) {
        System.out.println(tm.list_lectures);
      }
    }
    model.addAttribute("time_table", days2);
    model.addAttribute("option", option);
    // System.out.println("your query is " + query2);
    // You can access other user details such as authorities, etc.
    return "main";
  }

  @GetMapping("/timetable")
  public String getMethodName(@RequestParam(required = false) String option, @RequestParam String Class,
      @RequestParam(required = false) String Subject,
      Model model) {
    if(Subject != null)
        model.addAttribute("Subject", Subject);
    model.addAttribute("Class", Class);
    List<String> l_subjects = tr.getListOfColumns("Subject");
    l_subjects.remove("NONE");
    model.addAttribute("list_subjects", l_subjects);
    // String query = "select * from main where Class = 'Comp_SY_Div1'";
    String query = "select * from main where Class = '" + Class + "'";
    days = tr.create_time_table(query);
    for (time_slot[] day : days) {
      for (time_slot tm : day) {
        System.out.println(tm.list_lectures);
      }
    }
    // List<String> l = tr.getListOfColumns("Subject");
    // for (String string : l) {
    // System.out.println(string);
    // }
    // model.addAttribute("list_lec", l);
    model.addAttribute("option", option);
    model.addAttribute("time_table", days);
    return "timetable";
  }

  @PostMapping("/add_lecture")
  public String postMethodName(@RequestParam String id, @RequestParam String Class, @RequestParam String Subject) {
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
    tr.add_lecture(Subject, day, start_time, "AC203", "Comp_SY_Div1");
    return "redirect:/timetable?Class=" + Class;
  }

  @PostMapping("/cancelLecture")
  public String postMethodName(@RequestParam String lecName, @RequestParam String id, @RequestParam String file,
      @RequestParam String Class) {
    time_slot tm;
    String Main = "main";
    time_slot[][] days3 = days;
    if (file.equals(Main))
      days3 = days2;
    int day_count = 0;
    int flag = 0;
    for (time_slot[] day : days3) {
      int time_count = 9;
      for (time_slot slot : day) {
        System.out.println(slot.list_lectures);
        if (id.equalsIgnoreCase(slot.getId())) {
          id.trim();
          tr.cancel_lecture(lecName, this.giveDay(day_count), time_count, Class);
          flag = 1;
          break;
        }
        time_count++;
      }
      if (flag == 1)
        break;
      day_count++;
    }

    return "redirect:/timetable?Class=" + Class;
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
}
