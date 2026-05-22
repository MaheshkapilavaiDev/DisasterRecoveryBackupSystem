package com.drbsystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledBackupService {
	
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private CompressionService compressionService;
	
	@Autowired
	private AlertService alertService;
	
	@Autowired
	private AutoRecoveryService autoRecoveryService;

	@Value("${backup.location}")
	private String backupLocation;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Scheduled(cron = "0 */1 * * * *")
	public void autoBackup() {

		int retry = 3;

		while (retry > 0) {

			try {

				System.out.println("Scheduler Started");

				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

				String backupFile = backupLocation + "db_backup_" + timestamp + ".sql";

				ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe",
						"-u" + dbUser, "-p" + dbPassword, "drb_system", "--result-file=" + backupFile);

				Process process = pb.start();

				int exitCode = process.waitFor();

				if (exitCode == 0) {
					System.out.println("Backup Created Successfully: " + backupFile);
					
					String compressed =
							compressionService.compressFile(
				                    backupFile);

				    System.out.println("Compressed File: " + compressed);
				    
				    String encrypted =
				            encryptionService.encryptFile(compressed);

				    System.out.println("Encrypted File: " + encrypted);

					break;
				}else {
					
					throw new RuntimeException("Backup Process Failed");
				}

			} catch (Exception e) {

			    retry--;

			    System.out.println("Backup Failed. Retrying...");

			    if (retry == 0) {

			        System.out.println("All retries failed");

			        alertService.sendAlert("Database Backup Failed!");

			        autoRecoveryService.recoverDatabase();
			    }

			    e.printStackTrace();
			}
			
		}
	}
}
