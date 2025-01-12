package com.sayakraha.digestApp.controller;

import com.sayakraha.digestApp.entity.journalEntity;
import com.sayakraha.digestApp.entity.userEntity;
import com.sayakraha.digestApp.services.journalServices;
import com.sayakraha.digestApp.services.userServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntry {
    // private Map<Long, journalEntity> journalEntries = new HashMap<>();

    @Autowired
    private journalServices journalServices;
    @Autowired
    private userServices userServices;
    @GetMapping
    public ResponseEntity<List> getAll(){
//        return new ArrayList<>(journalEntries.values()
        List<journalEntity> journal = journalServices.getAll();
        if(journal!=null && !journal.isEmpty()){
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("{username}")
    public ResponseEntity<?> getAllbyUser(@PathVariable String username){
        try{
            userEntity user = userServices.findByName(username);
            List<journalEntity> journal = user.getJournals();
            if(journal!=null && !journal.isEmpty()){
                return new ResponseEntity<>(journal, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (NullPointerException e) {
            return new ResponseEntity<>("User Not found "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Some error occured: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("find/{myID}")
    public ResponseEntity<?> getbyID(@PathVariable ObjectId myID){
//        return (journalEntries.get(myID));
        Optional<journalEntity> journal = journalServices.findbyID(myID);
        if (journal.isPresent()){
            return new ResponseEntity<>(journal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{username}")
    public ResponseEntity<?> createEntry(@RequestBody journalEntity entry, @PathVariable String username){
//        journalEntries.put(entry.getID(), entry);
        try{
            journalServices.saveEntry(entry, username);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        }
        catch (NullPointerException e){
            return new ResponseEntity<>("User not found!",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("find/{username}/{myID}")
    public ResponseEntity<?> deletebyID(@PathVariable ObjectId myID,@PathVariable String username){
//        return journalEntries.remove(myID);
        return new ResponseEntity<>(journalServices.deletebyID(myID, username), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<?> delete(){
        return new ResponseEntity<>(journalServices.deleteAll(), HttpStatus.NO_CONTENT);
    }


    @PutMapping("find/{myID}")
    public ResponseEntity<?> updatebyID(@PathVariable ObjectId myID, @RequestBody journalEntity entry){
//       return journalEntries.put(myID, entry);
        try{
            journalEntity journal = journalServices.findbyID(myID).orElse(null);
            if(journal != null){
                journal.setHeading(entry.getHeading() != null && !entry.getHeading().equals("") ? entry.getHeading() : journal.getHeading());
                journal.setBody(entry.getBody() != null && !entry.getBody().equals("") ? entry.getBody() : journal.getBody());
            }
            journalServices.saveEntry(journal);
            return new ResponseEntity<>("Record updated succcessfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Some error occured: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
