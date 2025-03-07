package com.dev.JournalApp.service;


import com.dev.JournalApp.entity.JournalEntry;
import com.dev.JournalApp.entity.User;
import com.dev.JournalApp.repository.JournalEntryRespository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRespository journalEntryRespository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRespository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while saving journal entry " + e.toString());
            throw new RuntimeException("Error while saving journal entry", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRespository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRespository.findAll();
    }

    public Optional<JournalEntry> findByElementId(ObjectId id) {
        return journalEntryRespository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRespository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while deleting journal entry " + e.toString());
            throw new RuntimeException("Error while deleting journal entry", e);
        }
        return removed;
    }

}
