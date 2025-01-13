package com.sayakraha.digestApp.controller;

import com.sayakraha.digestApp.entity.userEntity;
import com.sayakraha.digestApp.services.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private userServices userServices;

    @PostMapping("/create-user")
    public ResponseEntity<?> createEntry(@RequestBody userEntity entry){
        try{
            entry.setEntryDate(LocalDateTime.now());
            userServices.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Some error occured: "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
