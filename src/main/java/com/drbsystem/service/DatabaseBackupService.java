package com.drbsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.drbsystem.entity.BackupMetadata;
import com.drbsystem.repository.BackupRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DatabaseBackupService {
	
	@Autowired
	private BackupRepository backupRepo;
	
	 private static final Logger logger =
	            LoggerFactory.getLogger(DatabaseBackupService.class);

	@Value("${backup.location}")
	private String backupLocation;

	@Value("${backup.db.name}")
	private String dbName;

	@Value("${backup.db.username}")
	private String username;

	@Value("${backup.db.password}")
	private String password;

	public String backupDatabase() throws Exception {
		
		 logger.info("Database backup started");

		String fileName = "db-backup-" + System.currentTimeMillis() + ".sql";
		String filePath = backupLocation + fileName;

		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe", "-u" + username, "-p" + password, dbName);

		pb.redirectOutput(new File(filePath));

		Process process = pb.start();
		int exitCode = process.waitFor();

		if (exitCode == 0) {

		    BackupMetadata metadata = new BackupMetadata(
		            "FULL",
		            filePath,
		            "SUCCESS",
		            LocalDateTime.now()
		    );

		    backupRepo.save(metadata);

		    logger.info("Database backup completed successfully");

		    return filePath;

		} else {

		    BackupMetadata metadata = new BackupMetadata(
		            "FULL",
		            filePath,
		            "FAILED",
		            LocalDateTime.now()
		    );

		    backupRepo.save(metadata);

		    logger.error("Database backup failed");

		    throw new RuntimeException("DB Backup Failed");
		}
	}
	public BackupMetadata getBackupStatus(Long id) {

	    return backupRepo.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Backup Not Found"));
	}
	
	public List<BackupMetadata> getAllBackups() {

	    return backupRepo.findAll();
	}
}