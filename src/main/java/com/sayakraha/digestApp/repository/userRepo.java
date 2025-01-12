package com.sayakraha.digestApp.repository;

import com.sayakraha.digestApp.entity.userEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userRepo extends MongoRepository<userEntity, ObjectId> {
    userEntity findByUserName(String name);
}
