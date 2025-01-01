package com.sayakraha.digestApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sayakraha.digestApp.entity.journalEntity;
import org.springframework.stereotype.Component;

public interface journalRepo extends MongoRepository<journalEntity, ObjectId> {

}
