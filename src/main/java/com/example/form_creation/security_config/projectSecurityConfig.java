package com.example.form_creation.security_config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class projectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception
    {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());*/

        // http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
            // .formLogin(Customizer.withDefaults())
            // .httpBasic(Customizer.withDefaults());
        http.csrf((csrf) -> csrf.disable()/*ignoringRequestMatchers(PathRequest.toH2Console()).ignoringRequestMatchers("/new")*//* .disable()*/)
        .authorizeHttpRequests((requests) -> requests.requestMatchers("/home", "/login_page","/", "/error").permitAll()
        .requestMatchers("/signup").permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/**.html")).permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/assets/**")).permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/new")).permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/timetable")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/add_lecture")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/cancelLecture")).permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/getContactUs")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/ContactUs")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/AddLecture")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/main")).hasRole("ADMIN")
        .requestMatchers(mvcMatcherBuilder.pattern("/saveMsg")).permitAll()
        .requestMatchers(mvcMatcherBuilder.pattern("/student_timetable")).permitAll()

        // .requestMatchers("/timetable/**").hasRole("ADMIN")
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        )
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
            .defaultSuccessUrl("/main", true).failureUrl("/home").permitAll())
        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login1?logout=true")
            .invalidateHttpSession(true).permitAll())
        .httpBasic(Customizer.withDefaults())
        ;
         http.headers(headersConfigurer -> headersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
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