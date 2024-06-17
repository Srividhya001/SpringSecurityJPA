package com.example.SpringSecurityJPA.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();

    }

    //for authorisation
    //We need httpSecurity Object to configure authorisation it is found in filterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
             .authorizeHttpRequests()
                     //.requestMatchers("/**").authenticated()
                    .requestMatchers("/admin").hasAuthority("admin")
                     .requestMatchers("/user").hasAnyAuthority("user","admin")
                     .requestMatchers("/registerUser").permitAll()
                     .requestMatchers("/test").permitAll()

                     //
                    // .requestMatchers("/test").permitAll()


                     .and()
                 .formLogin()
                 .and().httpBasic();

                 return http.build();


    }
}
