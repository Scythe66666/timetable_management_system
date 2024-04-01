package com.example.form_creation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class timetable_repo {
    public timetable_repo() {

    }

    @Autowired
    private JdbcTemplate template;
    RowMapper<lecture> rm = new RowMapper<lecture>() {
        @Override
        public lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
            lecture l = new lecture(rs.getString("Subject"), rs.getString("Classroom"), rs.getString("Day"),
                    rs.getInt("serial_no"), rs.getString("Batch"), rs.getInt("start_time"));
            return l;
        }
    };
    RowMapper<String> r2 = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            String l = rs.getString("Subject");
            return l;
        }
    };
    String sql = "select * from timetable";

    public List<lecture> getAll(String sql) {
        List<lecture> l = template.query(sql, rm);
        return l;
    }

    public List<String> getSubjects(String column){
        String sql = "SELECT DISTINCT "+column+" from timetable";

        List <String> l = template.query(sql, r2);
        return l;
    }

    public void delete(String Subject, String Day, int start_time) {
        String sql = "DELETE FROM timetable WHERE start_time = ? AND  Subject = ? AND Day = ?";

        int rows = template.update(sql, start_time, Subject, Day);
        System.out.println("number of rows affected are " + rows);
    }

    public void insert(String Subject, String Day, int start_time, String Classroom) {
        String sql = "insert into timetable (Subject, Day, start_time, Classroom) values (?, ?, ?, ?)";
        int rows = template.update(sql, Subject, Day, start_time, Classroom);
        // System.out.println("the insert operation in the database is complete and the rows affected are " + rows);
    }

}
