# Disaster Recovery Backup System

## Project Overview

This project is a Disaster Recovery Backup System developed using:

- Java 21
- Spring Boot
- MySQL
- REST APIs
- Spring Scheduler

The system supports:

- Database Backup
- File Backup
- Database Restore
- File Restore
- Scheduled Automatic Backup
- Compression
- Encryption
- Incremental Backup
- Retry Strategy
- Auto Recovery
- Checksum Validation
- Backup Metadata Tracking

---

# Features

## Backup Types

### Full Backup

Stores complete database backup.

### Incremental Backup

Stores only changed data after last backup.

---

# Backup Storage

```text
C:/backups/

│
├── full/
│
├── incremental/
│      └── last-backup.txt
│
├── compressed/
│
├── encrypted/
│
├── files/
│
└── restore/
```

---

# Database Backup

## Features

- Automatic Scheduled Backup
- Manual Backup
- MySQL Schema + Data Backup
- Backup Versioning
- Retry Strategy
- Metadata Tracking

---

# File Backup

## Features

- ZIP File Backup
- Large File Support
- File Compression
- Checksum Validation

---

# Restore Features

## Database Restore

Restores database from backup file.

## File Restore

Restores deleted or lost files from ZIP backup.

---

# Failure Handling

## Retry Strategy

If backup fails:

```java
retry--;
System.out.println("Backup Failed. Retrying...");
```

System retries automatically.

---

## Auto Recovery

If database crash occurs:

```java
autoRecoveryService.recoverDatabase();
```

Recovery starts automatically.

---

# Compression

## Purpose

Reduces backup size.

## Output Example

```text
backup.sql.zip
```

---

# Encryption

## Purpose

Secures backup files.

## Algorithm

AES Encryption

## Output Example

```text
backup.sql.zip.enc
```

---

# Incremental Backup

## Purpose

Stores only modified data after last backup.

## File Used

```text
C:/backups/incremental/last-backup.txt
```

Stores last successful backup timestamp.

---

# Scheduler

## Purpose

Automatically creates database backups at scheduled intervals.

## Example

```java
@Scheduled(cron = "0 */1 * * * *")
```

Runs backup every 1 minute.

---

# Checksum Validation

## Purpose

Verifies file integrity using SHA-256 checksum.

## Example Response

```text
b7b2842e25127f086a5b2170b2028d5476cd398d9a0ef8f25f7dcf284e5a38c0
```

---

# API Endpoints

# Database APIs

## Start Manual Database Backup

```http
POST /backup/start
```

Creates manual database backup.

---

## Get Backup Status

```http
GET /backup/status/{id}
```

### Example

```http
GET /backup/status/1
```

Checks backup metadata.

---

## Restore Database

```http
POST /restore/{backupId}
```

### Example

```http
POST /restore/1
```

Restores database using backup ID.

---

## Restore Status

```http
GET /restore/status/{id}
```

Checks restore logs.

---

# File APIs

## Backup Files

```http
POST /files/backup
```

Creates ZIP backup of files.

---

## Restore Files

```http
POST /files/restore
```

Restores files from ZIP backup.

---

## File Checksum Validation

```http
GET /files/checksum?fileName=file-backup-1747801682134.zip
```

Returns SHA-256 checksum value.

---

# Important Services

## DatabaseBackupService

Handles:

- Manual database backup
- Backup metadata

---

## FileBackupService

Handles:

- ZIP file backup

---

## RestoreService

Handles:

- Database restore
- File restore

---

## CompressionService

Handles:

- ZIP compression

---

## EncryptionService

Handles:

- AES encryption

---

## ScheduledBackupService

Handles:

- Automatic scheduled backups

---

## AutoRecoveryService

Handles:

- Crash recovery

---

# Backup Flow

```text
Application Starts
       ↓
Scheduler Runs
       ↓
Database Backup Created
       ↓
Compression
       ↓
Encryption
       ↓
Stored in Backup Folder
```

---

# Database Restore Flow

```text
Take Backup ID
      ↓
Read SQL Backup
      ↓
Restore Database
      ↓
Update Restore Logs
```

---

# File Restore Flow

```text
Read ZIP Backup
      ↓
Extract Files
      ↓
Restore into Target Folder
```

---

# Sample Backup Files

```text
backup-1747801682134.sql

backup-1747801682134.sql.zip

backup-1747801682134.sql.zip.enc
```

---

# Testing Flow

# Database Backup Test

## Step 1

Insert records into database.

## Step 2

Call:

```http
POST /backup/start
```

## Step 3

Verify backup file inside:

```text
C:/backups/full/
```

---

# Database Restore Test

## Step 1

Delete records manually.

## Step 2

Call:

```http
POST /restore/1
```

## Step 3

Verify data restored.

---

# File Backup Test

## Step 1

Place files inside:

```text
C:/testfiles/
```

## Step 2

Call:

```http
POST /files/backup
```

## Step 3

ZIP backup created.

---

# File Restore Test

## Step 1

Delete files from:

```text
C:/testfiles/
```

## Step 2

Call:

```http
POST /files/restore
```

## Step 3

Files restored successfully.

---

# Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Scheduler
- ZIP Streams
- AES Encryption

---

# Future Enhancements

- AWS S3 Backup
- Multi-region Backup
- Email Alerts
- Docker Support
- Kubernetes Deployment
- Monitoring Dashboard

---

# Conclusion

This project demonstrates:

- Disaster Recovery
- Backup Automation
- Restore Mechanism
- Failure Handling
- Data Integrity
- Secure Backup Storage
- Enterprise Backup Concepts