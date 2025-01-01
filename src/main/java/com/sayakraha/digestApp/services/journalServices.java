package com.sayakraha.digestApp.services;

import com.sayakraha.digestApp.entity.journalEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.sayakraha.digestApp.repository.journalRepo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class journalServices {
    @Autowired
    private journalRepo journalRepo;

    public void saveEntry(journalEntity journalEntity){
        journalRepo.save(journalEntity);
    }
    public List<journalEntity> getAll(){
        return journalRepo.findAll();
    }
    public Optional<journalEntity> findbyID(ObjectId id){
        return journalRepo.findById(id);
    }
    public String deletebyID(ObjectId id){
        journalRepo.deleteById(id);
        return "Successfully deleted entry!";
    }
}
