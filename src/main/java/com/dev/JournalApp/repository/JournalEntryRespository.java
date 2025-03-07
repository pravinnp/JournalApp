package com.dev.JournalApp.repository;

import com.dev.JournalApp.entity.JournalEntry;
import com.dev.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRespository extends MongoRepository<JournalEntry, ObjectId> {
    JournalEntry save(JournalEntry journalEntry);
}
