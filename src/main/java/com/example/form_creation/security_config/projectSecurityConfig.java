package com.example.form_creation.security_config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
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
        http.csrf((csrf) -> csrf.ignoringRequestMatchers(PathRequest.toH2Console())/* .disable()*/)
        .authorizeHttpRequests((requests) -> requests.requestMatchers("/home", "/login","/**").permitAll()
        .requestMatchers("/assets/**").permitAll()
        .requestMatchers("/signup").permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll())
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
            .defaultSuccessUrl("/dashboard").failureUrl("/login1?error=true").permitAll())
        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login1?logout=true")
            .invalidateHttpSession(true).permitAll())
        .httpBasic(Customizer.withDefaults())
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService()
    {
        // UserDetails user = User.withDefaultPasswordEncoder()
        // .username("user")
        // .password("12345")
        // .roles("USER")
        // .build();
        // UserDetails admin = User.withDefaultPasswordEncoder()
        // .username("admin")
        // .password("1122334455")
        // .roles("USER", "ADMIN")
        // .build();
        // return new InMemoryUserDetailsManager(user, admin);
        return new UserInfoUserDetailsService();
    }
    
}