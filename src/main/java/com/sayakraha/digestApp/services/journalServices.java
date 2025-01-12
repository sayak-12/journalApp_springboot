package com.sayakraha.digestApp.services;

import com.sayakraha.digestApp.entity.journalEntity;
import com.sayakraha.digestApp.entity.userEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.sayakraha.digestApp.repository.journalRepo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class journalServices {
    @Autowired
    private journalRepo journalRepo;
    @Autowired
    private userServices userServices;
    @Transactional
    public void saveEntry(journalEntity journalEntity, String username){
        userEntity user = userServices.findByName(username);
        journalEntity.setEntryDate(LocalDateTime.now());
        journalEntity.setAuthor(username);
        journalEntity save = journalRepo.save(journalEntity);
        user.getJournals().add(save);
        userServices.saveEntry(user);
    }
    public void saveEntry(journalEntity journalEntity){
        journalRepo.save(journalEntity);
    }
    public List<journalEntity> getAll(){
        return journalRepo.findAll();
    }
    public Optional<journalEntity> findbyID(ObjectId id){
        return journalRepo.findById(id);
    }
    @Transactional
    public String deletebyID(ObjectId id, String username){
        journalRepo.deleteById(id);
        userEntity user = userServices.findByName(username);
        user.getJournals().removeIf(x->x.getId().equals(id));
        userServices.saveEntry(user);
        return "Successfully deleted entry!";
    }

    @Transactional
    public String deleteAll() {
        List<journalEntity> journals = getAll();
        Set<String> uauthors = journals.stream().map(journalEntity::getAuthor).collect(Collectors.toSet());
        for(String username:uauthors){
            userEntity user = userServices.findByName(username);
            if (user!=null){
                user.getJournals().clear();
                userServices.saveEntry(user);
            }
        }
        journalRepo.deleteAll();
        return "successfully truncated entries!";
    }
}
