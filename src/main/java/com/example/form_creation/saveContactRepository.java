package com.example.form_creation;
import com.example.form_creation.Model.Contact;

import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class saveContactRepository {
   
    @Autowired
    private JdbcTemplate  template; 

    public void saveMessage(Contact contact)
    {
        String sql = "insert into ContactUs (message, name , phone_number, email) values (?, ?, ?, ?)";
        template.update(sql, contact.getMessage(), contact.getName(), contact.getNumber(), contact.getEmail());
    }

    public List <Contact> displayMessages(String sql){
        RowMapper <Contact> rm = (rs, rowNum)->{
            Contact c = new Contact();
            c.setEmail(rs.getString("email"));
            c.setMessage(rs.getString("message"));
            c.setNumber(rs.getString("phone_number"));
            c.setName(rs.getString("name"));
            return c;
        };
        List <Contact> li = template.query(sql, rm);

        return li;
    }
}
