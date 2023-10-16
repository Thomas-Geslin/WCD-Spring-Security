package com.wildcodeschool.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class config {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/avengers/assemble").hasRole("HERO")
                        .requestMatchers("/secret-bases").hasRole("DIRECTOR")
                        .anyRequest().authenticated()

        )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails steve = User
                .withUsername("Steve Guy")
                .password(encoder.encode("heroPassword"))
                .roles("HERO")
                .build();

        UserDetails tony = User
                .withUsername("Tony Stark")
                .password(encoder.encode("heroPassword"))
                .roles("HERO")
                .build();

        UserDetails fury = User
                .withUsername("Nick Fury")
                .password(encoder.encode("directorPassword"))
                .roles("DIRECTOR")
                .build();

        return new InMemoryUserDetailsManager(List.of(steve, tony, fury));
    }
}
