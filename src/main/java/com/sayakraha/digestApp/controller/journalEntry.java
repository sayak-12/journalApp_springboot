package com.sayakraha.digestApp.controller;

import com.sayakraha.digestApp.entity.journalEntity;
import com.sayakraha.digestApp.services.journalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntry {
    // private Map<Long, journalEntity> journalEntries = new HashMap<>();

    @Autowired
    private journalServices journalServices;

    @GetMapping
    public List<journalEntity> getAll(){
//        return new ArrayList<>(journalEntries.values());
        return journalServices.getAll();
    }
    @GetMapping("find/{myID}")
    public journalEntity getbyID(@PathVariable ObjectId myID){
//        return (journalEntries.get(myID));
        return journalServices.findbyID(myID).orElse(null);
    }
    @PostMapping
    public journalEntity createEntry(@RequestBody journalEntity entry){
//        journalEntries.put(entry.getID(), entry);
        entry.setEntryDate(LocalDateTime.now());
        journalServices.saveEntry(entry);
        return entry;
    }
    @DeleteMapping("find/{myID}")
    public String deletebyID(@PathVariable ObjectId myID){
//        return journalEntries.remove(myID);
        return journalServices.deletebyID(myID);
    }
    @PutMapping("find/{myID}")
    public String updatebyID(@PathVariable ObjectId myID, @RequestBody journalEntity entry){
//       return journalEntries.put(myID, entry);
        journalEntity journal = journalServices.findbyID(myID).orElse(null);
        if(journal != null){
            journal.setHeading(entry.getHeading() != null && !entry.getHeading().equals("") ? entry.getHeading() : journal.getHeading());
            journal.setBody(entry.getBody() != null && !entry.getBody().equals("") ? entry.getBody() : journal.getBody());
        }
        journalServices.saveEntry(journal);
        return "Record updated successfully!";
    }
}
