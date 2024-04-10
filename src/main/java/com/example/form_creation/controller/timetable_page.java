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
    // System.out.println();
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
    days2 = tr.create_time_table_for_teacher(username);
    List<String> l_subjects = tr.getListOfColumns("Subject");
    l_subjects.remove("NONE");
    model.addAttribute("list_subjects", l_subjects);
    model.addAttribute("time_table", days2);
    model.addAttribute("option", option);
    return "main";
  }

  @GetMapping("/student_timetable")
  public String Student_timetable(@RequestParam String Class, Model model) {
    model.addAttribute("Class", Class);
    List<String> l_classes = tr.getListOfColumns("Class");
    l_classes.remove("NONE");
    l_classes.remove("");
    model.addAttribute("list_classes", l_classes);
    time_slot[][] days_student = tr.create_time_table(Class);
    model.addAttribute("time_table", days_student);
    return "student_timetable";
  }

  @GetMapping("/timetable")
  public String getMethodName(@RequestParam(required = false) String option, @RequestParam String Class,
      @RequestParam(required = false) String Subject,
      Model model) {
    if (Subject != null)
      model.addAttribute("Subject", Subject);
    model.addAttribute("Class", Class);
    List<String> l_subjects = tr.getListOfColumns("Subject");
    l_subjects.remove("NONE");
    model.addAttribute("list_subjects", l_subjects);
    days = tr.create_time_table(Class);
    model.addAttribute("option", option);
    model.addAttribute("time_table", days);
    return "timetable";
  }

  @PostMapping("/add_lecture")
  public String postMethodName(@RequestParam String id, @RequestParam String Class, @RequestParam String Subject) {
    int i = 0, j = 0;
    id.trim();
    int flag = 0;
    time_slot tm = null;
    for (i = 0; i < days.length; i++) {
      for (j = 0; j < days[i].length; j++) {
        if (id.equalsIgnoreCase(days[i][j].getId())) {
          tm = days[i][j];
          flag = 1;
          break;
        }
      }
      if (flag == 1)
        break;
    }

    String day = tr.giveDay(i);
    int start_time = j + 9;
    tr.add_lecture(Subject, day, start_time, "Comp_SY_Div1", "AC203");
    return "redirect:/timetable?Class=" + Class;
  }

  @PostMapping("/cancelLecture")
  public String postMethodName(@RequestParam String lecName, @RequestParam String id, @RequestParam String file,
      @RequestParam String Class) {
    String Main = "main";
    time_slot[][] days3 = days;
    if (file.equals(Main))
      days3 = days2;
    int day_count = 0;
    int flag = 0;
    for (time_slot[] day : days3) {
      int time_count = 9;
      for (time_slot slot : day) {
        // System.out.println(slot.list_lectures);
        if (id.equalsIgnoreCase(slot.getId())) {
          id.trim();
          int index = slot.getList_lectures().indexOf(lecName);
          tr.cancel_lecture(lecName, tr.giveDay(day_count), time_count, Class, slot.getList_classrooms().get(index));
          flag = 1;
          break;
        }
        time_count++;
      }
      if (flag == 1)
        break;
      day_count++;
    }
    if (file.equals(Main))
      return "redirect:/main";
    return "redirect:/timetable?Class=" + Class;
  }
}
