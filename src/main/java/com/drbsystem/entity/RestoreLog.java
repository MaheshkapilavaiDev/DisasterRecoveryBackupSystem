package com.drbsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RestoreLog {

    @Id
    @GeneratedValue
    private Long id;

    private String backupFile;

    private String status;

    private LocalDateTime restoreTime;

    public RestoreLog() {
    }

    public RestoreLog(String backupFile, String status, LocalDateTime restoreTime) {
        this.backupFile = backupFile;
        this.status = status;
        this.restoreTime = restoreTime;
    }

    public Long getId() {
        return id;
    }

    public String getBackupFile() {
        return backupFile;
    }

    public void setBackupFile(String backupFile) {
        this.backupFile = backupFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRestoreTime() {
        return restoreTime;
    }

    public void setRestoreTime(LocalDateTime restoreTime) {
        this.restoreTime = restoreTime;
    }
}