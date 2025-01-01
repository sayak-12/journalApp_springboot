package com.sayakraha.digestApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Document
public class journalEntity {
    @Id
    private ObjectId id;
    private String heading;
    private String body;
    private String author;
    private LocalDateTime entryDate;

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public ObjectId getID(){
        return this.id;
    }
    public void setID(ObjectId id){
        this.id = id;
    }
    public String getHeading(){
        return this.heading;
    }
    public void setHeading(String h){
        this.heading = h;
    }
    public String getBody(){
        return this.body;
    }
    public void setBody(String body){
        this.body = body;
    }
}
