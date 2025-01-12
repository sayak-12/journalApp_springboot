package com.sayakraha.digestApp.controller;

import com.sayakraha.digestApp.entity.journalEntity;
import com.sayakraha.digestApp.entity.userEntity;
import com.sayakraha.digestApp.services.userServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class userEntry {
    @Autowired
    private userServices userServices;
    @GetMapping
    public ResponseEntity<List> getAll(){
        List<userEntity> user = userServices.getAll();
        if(user!=null && !user.isEmpty()){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("find/{myID}")
    public ResponseEntity<?> getbyID(@PathVariable ObjectId myID){
        Optional<userEntity> user = userServices.findbyID(myID);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("{username}")
    public ResponseEntity<?> getbyName(@PathVariable String username){
        userEntity user = userServices.findByName(username);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
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
    @DeleteMapping("find/{myID}")
    public ResponseEntity<?> deletebyID(@PathVariable ObjectId myID){
//        return journalEntries.remove(myID);
        return new ResponseEntity<>(userServices.deletebyID(myID), HttpStatus.NO_CONTENT);
    }
    @PutMapping("find/{username}")
    public ResponseEntity<?> updatebyID(@PathVariable String username, @RequestBody userEntity entry){
        try{
            userEntity user = userServices.findByName(username);
            if(user != null){
                user.setUserName(entry.getUserName() != null && !entry.getUserName().equals("") ? entry.getUserName() : user.getUserName());
                user.setPassword(entry.getPassword() != null && !entry.getPassword().equals("") ? entry.getPassword() : user.getPassword());
            }
            userServices.saveEntry(user);
            return new ResponseEntity<>("Record updated succcessfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error occured: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
