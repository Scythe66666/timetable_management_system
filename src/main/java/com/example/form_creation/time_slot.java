package com.example.form_creation;
import java.util.ArrayList;
import java.util.List;

public class time_slot {
    public List<String> list_lectures = new ArrayList<>();
    public List<String> list_classrooms = new ArrayList<>();
    int i = 0;
    public void append_lecture(String subject, String classroom) {
        i++;
        list_lectures.add(subject);
        list_classrooms.add(classroom);
    }
    public int geti(){
        return i;
    }
    public time_slot() {
        
    }

    public List<String> getList_classrooms() {
        return this.list_classrooms;
    }

    public List<String> getList_lectures() {
        return this.list_lectures;
    }

    public void setList_lectures(List<String> list_lectures) {
        this.list_lectures = list_lectures;
    }

    @Override
    public String toString() {
        return "{" +
            " list_lectures='" + getList_lectures() + "'" +
            ", list_classrooms='" + getList_classrooms() + "'" +
            "}";
    }

    
}
