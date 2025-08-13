package com.example.form_creation;

import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class timetable_service_layer {
    @Autowired
    timetable_repo rp;

    public void cancel_lecture(String subject, String Day, int start_time, String Class, String Classroom, String Teacher) {
        // Class = "Comp_SY_Div1";
        System.out.println("cancel lecture was executed");
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        rp.insert2(subject, Day, start_time, Class, today, "canceled", Classroom, Teacher);
    }

    public void add_lecture(String subject, String Day, int start_time, String Class, String Classroom, String Teacher) {
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        rp.insert2(subject, Day, start_time, Class, today, "Extra", Classroom, Teacher);
    }

    public time_slot[][] create_time_table(String Class) {
        String query = "select * from main where Class = '" + Class + "'";
        String query2 = "SELECT * FROM changesInTimetable WHERE YEARWEEK(CancelDate) = YEARWEEK(CURDATE()) AND Class = '"
                + Class + "'" + " AND changeType = 'canceled'";
        String query3 = "SELECT * FROM changesInTimetable WHERE YEARWEEK(CancelDate) = YEARWEEK(CURDATE()) AND Class = '"
                + Class + "'" + "AND changeType = 'Extra'";

        time_slot[][] days = new time_slot[7][];
        List<lecture> li = rp.getAll(query);
        List<lecture> lc = rp.getAllCanceled(query2);
        List<lecture> le = rp.getAllCanceled(query3);
        for (lecture l : lc) {
            System.out.println("lectures canceled are" + l);
        }

        // initializing days
        for (int i = 0; i < 7; i++) {
            time_slot[] day = new time_slot[9];
            days[i] = day;
            for (int j = 0; j < 9; j++)
                days[i][j] = new time_slot();
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
            day_no = this.giveNumber(Day);
            if (day_no == -1) {
                System.out.println(
                        "there was a grave error where serial_no is" + lec.getSerial_no() + " value of lecture.day is "
                                + lec.getDay());
                continue;
            }
            if (lec.getSubject() == null)
                System.out.println(" an error is thereer\n\n\n\n\n\n\n\n\n\n");
            days[day_no][start_time - 9].append_lecture(lec.getSubject(), lec.getClassroom());
            days[day_no][start_time - 9].setCLASS(lec.getCLASS());
        }
        for (lecture Lecture : lc) {
            days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9]
                    .append_canceled_lecture(Lecture.getSubject(), Lecture.getClassroom());
        }

        for (lecture Lecture : le) {
            // System.out.println(Lecture);
            days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].append_extra_lecture(Lecture.getSubject(),
                    Lecture.getClassroom());
        }

        return days;
    }

    public time_slot[][] create_time_table_for_teacher(String Teacher) {
        String query = "select * from main where Teacher = '" + Teacher + "'";
        String query1 = "SELECT * FROM changesInTimetable WHERE YEARWEEK(CancelDate) = YEARWEEK(CURDATE()) AND changeType = 'canceled' AND Teacher = '"
                + Teacher + "'";
        String query2 = "SELECT * FROM changesInTimetable WHERE YEARWEEK(CancelDate) = YEARWEEK(CURDATE()) AND changeType = 'Extra' AND Teacher = '"
                + Teacher + "'";

        time_slot[][] days = new time_slot[7][];
        List<lecture> li = rp.getAll(query);
        List<lecture> lc = rp.getAllCanceled(query1);
        List<lecture> le = rp.getAllCanceled(query2);

        for (lecture l : lc) {
            System.out.println("lectures canceled are" + l);
        }
        for (lecture l : le) {
            System.out.println("lectures canceled are" + l);
        }

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
            day_no = this.giveNumber(Day);
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
        for (lecture Lecture : lc) {
            days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9]
                    .append_canceled_lecture(Lecture.getSubject(), Lecture.getClassroom());
        }

        for (lecture Lecture : le) {
            days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].append_extra_lecture(Lecture.getSubject(),
                    Lecture.getClassroom());
            System.out.println(" extra lectures  settings                   "
                    + days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].getExtra_lectures().get(0) + "   "
                    + days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].getExtra_classrooms().get(0));
            System.out.println(" extra lectures  settings                   "
                    + days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].getExtra_lectures().get(0) + "   "
                    + days[giveNumber(Lecture.getDay())][Lecture.getStart_time() - 9].getExtra_classrooms().get(0));
        }

        return days;
    }

    public List<String> getListOfSubjects(String column, String Class, String Teacher) {
        List<String> l;
        if (column.equals("Subject"))
            l = rp.getSubjects(Class, Teacher);
        else {
            l = rp.getClasses();
        }
        return l;
    }

    public List<String> getListOfClassRooms() {
        List<String> l = rp.getClassrooms();
        return l;
    }

    public int findDayAndTimeById(String id, time_slot[][] days) {
        int i, j;
        for (i = 0; i < days.length; i++) {
            for (j = 0; j < days[i].length; j++) {
                if (id.equalsIgnoreCase(days[i][j].getId())) {
                    return i * 10 + j;
                }
            }
        }

        System.out.println(
                "biggggggggggg errrrrrrrrrrrrrrrrrrrrorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr]\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n ");
        return -1;
    }

    public String getNotification(String Class) {
        String sql1 = "select * from changesInTimetable where Class='" + Class + "' AND changeType='Extra'";
        String sql2 = "select * from changesInTimetable where Class='" + Class + "' AND changeType='canceled'";
        List<lecture> lc = rp.getAllCanceled(sql2);
        List<lecture> le = rp.getAllCanceled(sql1);
        String Message = "";
        if (lc.isEmpty() && le.isEmpty()) {
            Message = "no notifications for today . Enjoy your day!!!!!!!!!!!";
            return Message;
        } else if (!le.isEmpty()) {
            Message += "Extra lectures arranged are ";
            for (lecture lecture : le) {
                Message += lecture.getSubject() + ",";
            }
        } else if (!lc.isEmpty()) {
            Message += " canceled lectures are ";
            for (lecture lecture : lc) {
                Message += lecture.getSubject() + ",";
            }
        }

        return Message;
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

    public int giveNumber(String day) {
        int num;
        switch (day) {
            case "Monday":
                num = 0;
                break;
            case "Tuesday":
                num = 1;
                break;
            case "Wednesday":
                num = 2;
                break;
            case "Thursday":
                num = 3;
                break;
            case "Friday":
                num = 4;
                break;
            case "Saturday":
                num = 5;
                break;
            case "Sunday":
                num = 6;
                break;
            default:
                num = -1; // Invalid day name
                break;
        }
        return num;
    }

}