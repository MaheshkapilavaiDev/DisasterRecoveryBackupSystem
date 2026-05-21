package com.drbsystem.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledBackupService {

	@Value("${backup.location}")
	private String backupLocation;

	@Value("${spring.datasource.username}")
	private String dbUser;

	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Scheduled(cron = "0 */1 * * * *")
	public void autoBackup() {

		try {
			
			  System.out.println("Scheduler Started");

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

			String backupFile = backupLocation + "db_backup_" + timestamp + ".sql";

			ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe", "-u" + dbUser, "-p" + dbPassword, "drb_system",
					"--result-file=" + backupFile);

			Process process = pb.start();

			int exitCode = process.waitFor();

			if (exitCode == 0) {
				System.out.println("Backup Created Successfully: " + backupFile);
			} else {
				System.out.println("Backup Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
