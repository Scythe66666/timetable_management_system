package com.example.form_creation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.hibernate.sql.exec.spi.JdbcOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
public class timetable_repo {
    public timetable_repo(){

    }
    @Autowired
    private JdbcTemplate template;
    RowMapper<lecture> rm = new RowMapper<lecture>() {
        @Override
        public lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
            lecture l = new lecture(rs.getString("Subject"), rs.getString("Classroom"), rs.getString("Day"), rs.getInt("serial_no"), rs.getString("Batch"), rs.getInt("start_time"));
            return l;
        }
    };
    String sql = "select * from timetable";
    public List<lecture> getAll(String sql) {
        List <lecture> l = template.query(sql, rm);
        return l;
    }
}
