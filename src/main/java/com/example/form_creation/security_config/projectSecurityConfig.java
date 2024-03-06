package com.example.form_creation.security_config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class projectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
    {
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());*/

        // http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
            // .formLogin(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults());
        http.csrf((csrf) -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
        .authorizeHttpRequests((requests) -> requests.requestMatchers("/home", "/login").permitAll()
        .requestMatchers("/assets/**").permitAll()
        .requestMatchers("/timetable").permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll())
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login1")
            .defaultSuccessUrl("/dashboard").failureUrl("/login1?error=true").permitAll())
        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login1?logout=true")
            .invalidateHttpSession(true).permitAll())
        .httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }
    /**
     * @return
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("12345")
        .roles("USER")
        .build();
        
        UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("1122334455")
        .roles("USER", "ADMIN")
        .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    
}