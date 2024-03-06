package com.example.form_creation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

import java.sql.*;

@Controller
public class timtable_page {

    @GetMapping("/timetable")
    public String displayTimeTable(Model m) throws Exception {
        timetable tb = new timetable("timetable.db");
        // List <time_slot>[] days = new ArrayList[7];
        // List <List <time_slot>> days = new ArrayList<>(7);
        time_slot[][] days = new time_slot[7][];
        for (int i = 0; i < 7; i++) {
            time_slot[] day = new time_slot[9];
            days[i] = day;
            for(int j = 0; j < 9; j++)
            {
                days[i][j] = new time_slot();
            }
        }
        ResultSet rs = tb.giveResultSet("timetable");
        while (rs.next()) {
            // time_slot ts = new time_slot(rs.getString("Subject"),
            // rs.getInt("start_time"), rs.getString(""));
            String Day = rs.getString("Day");
            int start_time = rs.getInt("start_time");
            int day_no = -1;
            int i = 0;
            if (Day == null)
                continue;
            switch (Day) {
                case "Monday":
                    day_no = 0;
                    break;
                case "Tuesday":
                    day_no = 1;
                    break;
                case "Wednesday":
                    day_no = 2;
                    break;
                case "Thursday":
                    day_no = 3;
                    break;
                case "Friday":
                    day_no = 4;
                    break;
                case "Saturday":
                    day_no = 5;
                    break;
                case "Sunday":
                    day_no = 6;
                    break;
                default:
                continue;
            }
            if (day_no == -1) {
                System.out.println(
                        "there was a grave error where serial_no is" +rs.getInt("serial_no"));
                continue;
            }
            if(rs.getString("Subject") == null)
                System.out.println(" an eeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrror is thereer\n\n\n\n\n\n\n\n\n\n");
            // System.out.println("the number of append is " + (++i) + "the number of days is" + day_no +" the start_ time is "+ start_time + " where serial_no is" +rs.getInt("serial_no")); 
            days[day_no][start_time - 9].append_lecture(rs.getString("Subject"), rs.getString("Classroom"));  
        }
        m.addAttribute("time_table", days);
        return "timetable.html";
    }
}
