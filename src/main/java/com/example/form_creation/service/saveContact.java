package com.example.form_creation.service;

import com.example.form_creation.Model.Contact;
import com.example.form_creation.saveContactRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class saveContact {

  @Autowired
  private saveContactRepository scr;

  public void saveContacts(Contact contact) {
    scr.saveMessage(contact);
  }

  public List<Contact> getMessages() {
    String sql = "select * from ContactUs";
    List <Contact> li = scr.displayMessages(sql);
    return li;
  }
}
