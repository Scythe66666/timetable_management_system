package com.example.form_creation.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.form_creation.timetable_repo;
import com.example.form_creation.timetable_service_layer;

@Aspect
@Component
@Order(1)
public class notification {
    @Autowired
    timetable_repo tr; 
    @Autowired
    timetable_service_layer ts; 

}
