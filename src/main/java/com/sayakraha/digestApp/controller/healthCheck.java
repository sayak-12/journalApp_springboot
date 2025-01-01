package com.sayakraha.digestApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {
    @GetMapping("/health")
    public String ffn(){
        return "ALL OK";
    }
}
