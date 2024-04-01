
package com.example.form_creation.security_config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.example.form_creation.Repository.UserInfoRepository;
import com.example.form_creation.Model.UserInfo;

/**
 * UserInfoUserDetailsService
 */
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository Repository;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional <UserInfo> userInfo = Repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
        .orElseThrow(()
        ->new UnsupportedOperationException("user Not found Exception"));
    }
}