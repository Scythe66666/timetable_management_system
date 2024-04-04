package com.example.form_creation;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class timetable_service_layer {
    @Autowired
    timetable_repo rp;

    public void cancel_lecture(String subject, String Day, int start_time, String Class) {
        // Class = "Comp_SY_Div1";
        rp.delete(subject, Day, start_time);
        rp.insert("NONE", Day, start_time, null, Class);
    }

    public void add_lecture(String Subject, String Day, int start_time, String Classroom, String Class) {
        rp.delete("NONE", Day, start_time);
        rp.insert(Subject, Day, start_time, Classroom, Class);
    }

    public time_slot[][] create_time_table(String query) {
        time_slot[][] days = new time_slot[7][];
        List<lecture> li = rp.getAll(query);
        for (int i = 0; i < 7; i++) {
            time_slot[] day = new time_slot[9];
            days[i] = day;
            for (int j = 0; j < 9; j++) {
                days[i][j] = new time_slot();
            }
        }
        Iterator<lecture> it = li.iterator();
        while (it.hasNext()) {
            // time_slot ts = new time_slot(rs.getString("Subject"),
            // rs.getInt("start_time"), rs.getString(""));
            lecture lec = it.next();
            String Day = lec.getDay();
            int start_time = lec.getStart_time();
            int day_no = -1;
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
                        "there was a grave error where serial_no is" + lec.getSerial_no());
                continue;
            }
            if (lec.getSubject() == null)
                System.out.println(" an error is thereer\n\n\n\n\n\n\n\n\n\n");
            days[day_no][start_time - 9].append_lecture(lec.getSubject(), lec.getClassroom());
            days[day_no][start_time - 9].setCLASS(lec.getCLASS());
        }

        return days;
    }

    public List<String> getListOfColumns(String column) {
        List<String> l;
        if (column.equals("Subject"))
            l = rp.getSubjects();
        else
        {
            l = rp.getClasses();
        }
        return l;
    }
}