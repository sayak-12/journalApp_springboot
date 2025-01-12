package com.sayakraha.digestApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity{
public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
    return http.authorizeHttpRequests(request->request
            .requestMatchers("/public/**").permitAll()
            .requestMatchers("/journal/**", "/user/**").authenticated()
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .build();
}
}
