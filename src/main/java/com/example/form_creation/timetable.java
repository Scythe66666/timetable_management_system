package com.example.form_creation;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

class time_slot {
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

public class timetable {
    private String Connection_string = "jdbc:sqlite:" + System.getProperty("user.dir")
            + "/src/main/resources/static/database/";
    private Connection con;
    private String db_name;

    public timetable(String Db_name) throws Exception {
        db_name = Db_name;
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(Connection_string + Db_name);
        Statement stm = con.createStatement();
    }

    ResultSet giveResultSet(String table_name) throws Exception {
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from " + table_name);
        return rs;
    }
}
