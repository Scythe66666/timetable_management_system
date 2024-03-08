package com.example.form_creation;

import java.sql.*;


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
