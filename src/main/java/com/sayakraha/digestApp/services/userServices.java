package com.sayakraha.digestApp.services;

import com.sayakraha.digestApp.entity.userEntity;
import com.sayakraha.digestApp.repository.userRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userServices {
    @Autowired
    private userRepo userRepo;

    public void saveEntry(userEntity userEntity){
        userRepo.save(userEntity);
    }
    public List<userEntity> getAll(){
        return userRepo.findAll();
    }
    public Optional<userEntity> findbyID(ObjectId id){
        return userRepo.findById(id);
    }
    public String deletebyID(ObjectId id){
        userRepo.deleteById(id);
        return "Successfully deleted entry!";
    }
    public userEntity findByName(String userName){
        return userRepo.findByUserName(userName);
    }
}
