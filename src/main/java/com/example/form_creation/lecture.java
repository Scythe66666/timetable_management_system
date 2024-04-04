package com.example.form_creation;

import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Scope("prototype")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class lecture {
    private String Subject;
    private String Classroom;
    private String Day;
    private int serial_no;
    private int start_time;
    private String CLASS; 
    // private String Batch;


    // public String getSubject() {
    //     return this.Subject;
    // }

    // public void setSubject(String Subject) {
    //     this.Subject = Subject;
    // }

    // public String getClassroom() {
    //     return this.Classroom;
    // }

    // public void setClassroom(String Classroom) {
    //     this.Classroom = Classroom;
    // }

    // public String getDay() {
    //     return this.Day;
    // }

    // public void setDay(String Day) {
    //     this.Day = Day;
    // }

    // public int getSerial_no() {
    //     return this.serial_no;
    // }

    // public void setSerial_no(int serial_no) {
    //     this.serial_no = serial_no;
    // }


    // public int getStart_time() {
    //     return this.start_time;
    // }

    // public void setStart_time(int start_time) {
    //     this.start_time = start_time;
    // }

    // public lecture Subject(String Subject) {
    //     setSubject(Subject);
    //     return this;
    // }

    // public lecture Classroom(String Classroom) {
    //     setClassroom(Classroom);
    //     return this;
    // }

    // public lecture Day(String Day) {
    //     setDay(Day);
    //     return this;
    // }

    // public lecture serial_no(int serial_no) {
    //     setSerial_no(serial_no);
    //     return this;
    // }


    // public lecture start_time(int start_time) {
    //     setStart_time(start_time);
    //     return this;
    // }

    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof lecture)) {
    //         return false;
    //     }
    //     lecture timetable = (lecture) o;
    //     return Objects.equals(Subject, timetable.Subject) && Objects.equals(Classroom, timetable.Classroom) && Objects.equals(Day, timetable.Day) && serial_no == timetable.serial_no && start_time == timetable.start_time;
    // }


    // @Override
    // public String toString() {
    //     return "{" +
    //         " Subject='" + getSubject() + "'" +
    //         ", Classroom='" + getClassroom() + "'" +
    //         ", Day='" + getDay() + "'" +
    //         ", serial_no='" + getSerial_no() + "'" +
    //         ", start_time='" + getStart_time() + "'" +
    //         "}";
    // }
}
