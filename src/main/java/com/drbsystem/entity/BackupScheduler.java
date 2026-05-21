package com.drbsystem.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.drbsystem.service.DatabaseBackupService;

@Component
public class BackupScheduler {

    @Autowired
    private DatabaseBackupService dbService;

    @Scheduled(cron = "0 0/30 * * * *") // every 30 min
    public void runBackup() throws Exception {
        dbService.backupDatabase();
    }
}
