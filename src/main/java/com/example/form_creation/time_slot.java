package com.example.form_creation;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class time_slot {
    public final String id;
    private String CLASS;
    private List<String> list_lectures;
    private List<String> list_classrooms;
    private List<String> canceled_lectures;
    private List<String> extra_lectures;
    private List<String> extra_classrooms;

    public void append_lecture(String subject, String classroom) {
        list_lectures.add(subject);
        list_classrooms.add(classroom);
    }
    
    public void append_extra_lecture(String subject, String classroom) {
        extra_lectures.add(subject);
        extra_classrooms.add(classroom);
        list_lectures.remove(subject);
        list_classrooms.remove(classroom);
    }

    public void append_canceled_lecture(String subject, String classroom) {
        canceled_lectures.add(subject);
        list_lectures.remove(subject);
        list_classrooms.remove(classroom);
    }

    public boolean delete_lecture(String subject) {
        return list_lectures.remove(subject);
    }

    public time_slot() {
        id = UUID.randomUUID().toString();
        list_lectures = new ArrayList<>();
        list_classrooms = new ArrayList<>();
        canceled_lectures = new ArrayList<>();
        extra_lectures = new ArrayList<>();
        extra_classrooms = new ArrayList<>();
    }

}