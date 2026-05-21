package com.drbsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbsystem.entity.BackupMetadata;
import com.drbsystem.service.DatabaseBackupService;

@RestController
@RequestMapping("/backup")
public class BackupController {

    @Autowired
    private DatabaseBackupService dbService;

    @PostMapping("/start")
    public String startBackup() throws Exception {
        return dbService.backupDatabase();
    }
    
    @GetMapping("/status/{id}")
    public BackupMetadata getBackupStatus(@PathVariable Long id) {

        return dbService.getBackupStatus(id);
    }
    
    @GetMapping("/all")
    public List<BackupMetadata> getAllBackups() {

        return dbService.getAllBackups();
    }
}
