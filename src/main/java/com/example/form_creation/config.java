package com.example.form_creation;

import org.hibernate.annotations.Comment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * config
 */
@Configuration
@ComponentScan(basePackageClasses = { com.example.form_creation.lecture.class, com.example.form_creation.timetable_repo.class,
        com.example.form_creation.timetable_service_layer.class, com.example.form_creation.service.saveContact.class, com.example.form_creation.controller.ContactUs_page.class})
public class config {

}