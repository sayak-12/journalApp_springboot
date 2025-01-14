package com.sayakraha.digestApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Document
@Data
public class journalEntity {
    @Id
    private ObjectId id;
    @NonNull
    private String heading;
    @NonNull
    private String body;
    private String author;
    private LocalDateTime entryDate;
}
