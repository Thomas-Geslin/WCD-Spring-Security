package com.wildcodeschool.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShieldController {
    @GetMapping("/")
    public String home() {
        return "Welcome to the SHIELD !";
    }

    @GetMapping("/avengers/assemble")
    public String assemble() {
        return "Avengers..... Assemble";
    }

    @GetMapping("/secret-bases")
    public String[] bases() {
        return new String[] {"Bordeaux", "Nantes", "Lyon", "Lilles", "Paris", "Toulouse"};
    }
}
