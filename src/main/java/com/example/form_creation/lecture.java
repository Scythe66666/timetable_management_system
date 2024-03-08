package com.example.form_creation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
@Scope("prototype")
public class lecture {
    private String Subject;
    private String Classroom;
    private String Day;
    private int serial_no;
    private String Batch;

    public lecture() {
    }

    public lecture(String Subject, String Classroom, String Day, int serial_no, String Batch, int start_time) {
        this.Subject = Subject;
        this.Classroom = Classroom;
        this.Day = Day;
        this.serial_no = serial_no;
        this.Batch = Batch;
        this.start_time = start_time;
    }

    public String getSubject() {
        return this.Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public String getClassroom() {
        return this.Classroom;
    }

    public void setClassroom(String Classroom) {
        this.Classroom = Classroom;
    }

    public String getDay() {
        return this.Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public int getSerial_no() {
        return this.serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public String getBatch() {
        return this.Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
    }

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public lecture Subject(String Subject) {
        setSubject(Subject);
        return this;
    }

    public lecture Classroom(String Classroom) {
        setClassroom(Classroom);
        return this;
    }

    public lecture Day(String Day) {
        setDay(Day);
        return this;
    }

    public lecture serial_no(int serial_no) {
        setSerial_no(serial_no);
        return this;
    }

    public lecture Batch(String Batch) {
        setBatch(Batch);
        return this;
    }

    public lecture start_time(int start_time) {
        setStart_time(start_time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof lecture)) {
            return false;
        }
        lecture timetable = (lecture) o;
        return Objects.equals(Subject, timetable.Subject) && Objects.equals(Classroom, timetable.Classroom) && Objects.equals(Day, timetable.Day) && serial_no == timetable.serial_no && Objects.equals(Batch, timetable.Batch) && start_time == timetable.start_time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Subject, Classroom, Day, serial_no, Batch, start_time);
    }

    @Override
    public String toString() {
        return "{" +
            " Subject='" + getSubject() + "'" +
            ", Classroom='" + getClassroom() + "'" +
            ", Day='" + getDay() + "'" +
            ", serial_no='" + getSerial_no() + "'" +
            ", Batch='" + getBatch() + "'" +
            ", start_time='" + getStart_time() + "'" +
            "}";
    }
    private int start_time; 
}
