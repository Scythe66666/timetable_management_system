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
    private String Teacher;


    @Override
    public String toString() {
        return "{" +
            " Subject='" + getSubject() + "'" +
            ", Classroom='" + getClassroom() + "'" +
            ", Day='" + getDay() + "'" +
            ", serial_no='" + getSerial_no() + "'" +
            ", start_time='" + getStart_time() + "'" +
            ", CLASS='" + getCLASS() + "'" +
            ", Teacher='" + getTeacher() + "'" +
            "}";
    }
}
