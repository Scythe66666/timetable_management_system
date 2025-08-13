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
    List<String> l_classes = tr.getListOfSubjects("Class", "SY", userDetails.getUsername());
    // System.out.println();
    l_classes.remove("NONE");
    model.addAttribute("list_classes", l_classes);
    return "AddLecture";
  }

  @GetMapping("/main")
  public String mainMethod(Model model, @RequestParam(required = false) String option) {
    // Get the Authentication object
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    List<String> l_classes = tr.getListOfSubjects("Class", "SY", "none");
    l_classes.remove("NONE");
    l_classes.remove("");
    model.addAttribute("list_classes", l_classes);
    // Extract user details from Authentication object
    userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    days2 = tr.create_time_table_for_teacher(username);
    model.addAttribute("file_name", "main");
    model.addAttribute("user", userDetails.getUsername());
    model.addAttribute("time_table", days2);
    model.addAttribute("option", option);
    return "main";
  }

  @GetMapping("/student_timetable")
  public String Student_timetable(@RequestParam String Class, Model model) {
    model.addAttribute("Class", Class);
    List<String> l_classes = tr.getListOfSubjects("Class", "SY", "none");
    l_classes.remove("NONE");
    l_classes.remove("");
    model.addAttribute("list_classes", l_classes);
    time_slot[][] days_student = tr.create_time_table(Class);
    model.addAttribute("notification", tr.getNotification(Class));
    // System.out.println("notification is " + tr.getNotification(Class));
    model.addAttribute("time_table", days_student);
    model.addAttribute("file_name", "student_timetable");
    return "student_timetable";
  }

  @GetMapping("/timetable")
  public String getMethodName(@RequestParam(required = false) String option, @RequestParam String Class,
      @RequestParam(required = false) String Subject,
      @RequestParam(required = false) String id,
      Model model) {
    // System.out
    // .println("value of id is " + id + " Subject is: " + Subject + "Class is " +
    // Class + " options is " + option);
    if (Subject != null)
      model.addAttribute("Subject", Subject);
    if (id != null) {
      // System.out.println("id " + id + " is added to the model");
      model.addAttribute("id", id);
      int num = tr.findDayAndTimeById(id, days);
      String Day = tr.giveDay(num / 10);
      int start_time = num % 10;
      model.addAttribute("start_time", start_time);
      model.addAttribute("day", Day);
      // System.out.println("value of day is " + Day + " value of start_time is " +
      // start_time);
    }
    List<String> Subjects = tr.getListOfSubjects("Subject", Class, userDetails.getUsername());
    // for (String string : Subjects) {
      // System.out.println("list_subjects is " + string);
    // }
    List<String> Classrooms = tr.getListOfClassRooms();
    // in getListofSubjects the second and third argument are worth less kinda like
    // a bug should fix it
    List<String> l_classes = tr.getListOfSubjects("Class", "SY", "none");
    l_classes.remove("NONE");
    l_classes.remove("");
    model.addAttribute("list_classes", l_classes);
    model.addAttribute("user", userDetails.getUsername());
    model.addAttribute("list_classrooms", Classrooms);
    model.addAttribute("list_subjects", Subjects);
    model.addAttribute("Class", Class);
    days = tr.create_time_table(Class);
    model.addAttribute("option", option);
    model.addAttribute("time_table", days);
    model.addAttribute("file_name", "timetable");
    return "timetable";
  }

  @PostMapping("/add_lecture")
  public String AddLecture(@RequestParam String day, @RequestParam int start_time, @RequestParam String Class,
      @RequestParam String Subject,
      @RequestParam String Classroom, @RequestParam String Teacher) {
    // System.out.println("value of classroom is " + Classroom);

    start_time += 9;
    tr.add_lecture(Subject, day, start_time, Class, Classroom, Teacher);
    return "redirect:/timetable?Class=" + Class;
  }

  @PostMapping("/cancelLecture")
  public String postMethodName(@RequestParam String lecName, @RequestParam String id, @RequestParam String file,
      @RequestParam String Teacher,
      @RequestParam String Class) {
    System.out.println(" parameter you have received are  lecName + " + lecName + " id " + id + " file name " + file
        + " teacher :" + Teacher + " Class is " + Class);
    String Main = "main";
    time_slot[][] days3 = days;
    if (file.equals(Main)) {

      // System.out.println("you enter the if of the cancelLecture");
      days3 = days2;
    }
    int day_count = 0;
    int flag = 0;
    for (time_slot[] day : days3) {
      int time_count = 9;
      for (time_slot slot : day) {
        // System.out.println(slot.list_lectures);
        if (id.equalsIgnoreCase(slot.getId())) {
          id.trim();
          int index = slot.getList_lectures().indexOf(lecName);
          if (!file.equals(Main))
            tr.cancel_lecture(lecName, tr.giveDay(day_count), time_count, Class, slot.getList_classrooms().get(index),
                Teacher);
          else
            tr.cancel_lecture(lecName, tr.giveDay(day_count), time_count, slot.getCLASS(),
                slot.getList_classrooms().get(index), userDetails.getUsername());
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

  @PostMapping("/notifications")
  public String getNotifications(@RequestParam String Class) {
    String Message = null;
    return Message;
  }

}
