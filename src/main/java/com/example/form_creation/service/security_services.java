
package com.example.form_creation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.form_creation.Model.UserInfo;
import com.example.form_creation.Repository.UserInfoRepository;

/**
 * security_services
 */
@Service
public class security_services {
    @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
    private UserInfoRepository repository;
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}