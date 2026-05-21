package com.drbsystem.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drbsystem.entity.BackupMetadata;
import com.drbsystem.entity.RestoreLog;
import com.drbsystem.repository.BackupRepository;
import com.drbsystem.repository.RestoreLogRepository;

@Service
public class RestoreService {

	private static final Logger logger = LoggerFactory.getLogger(RestoreService.class);

	@Autowired
	private BackupRepository backupRepo;

	@Autowired
	private RestoreLogRepository restoreLogRepo;

	// =========================
	// DATABASE RESTORE
	// =========================

	public String restoreDatabase(Long backupId) throws Exception {

		BackupMetadata metadata = backupRepo.findById(backupId)
				.orElseThrow(() -> new RuntimeException("Backup Not Found"));

		String backupFilePath = metadata.getFilePath();

		logger.info("Database restore started");

		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe", "-uroot",
				"-proot9099", "drb_system");

		pb.redirectInput(new File(backupFilePath));

		Process process = pb.start();

		int code = process.waitFor();

		if (code == 0) {

			logger.info("Database restore successful");

			RestoreLog log = new RestoreLog(backupFilePath, "SUCCESS", LocalDateTime.now());

			restoreLogRepo.save(log);

			return "Restore Successful";

		} else {

			String error = new String(process.getErrorStream().readAllBytes());

			logger.error("Restore Error: {}", error);

			RestoreLog log = new RestoreLog(backupFilePath, "FAILED", LocalDateTime.now());

			restoreLogRepo.save(log);

			throw new RuntimeException(error);
		}
	}

	// =========================
	// FILE RESTORE
	// =========================

	public String restoreFiles(String zipFilePath, String restoreDir) throws Exception {

		File dir = new File(restoreDir);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));

		ZipEntry entry;

		while ((entry = zis.getNextEntry()) != null) {

			File newFile = new File(restoreDir + File.separator + entry.getName());

			FileOutputStream fos = new FileOutputStream(newFile);

			byte[] buffer = new byte[1024];

			int len;

			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}

			fos.close();

			zis.closeEntry();
		}

		zis.close();

		return "Files Restored Successfully";
	}

	public RestoreLog getRestoreStatus(Long id) {

		return restoreLogRepo.findById(id).orElseThrow(() -> new RuntimeException("Restore Log Not Found"));
	}
	
	
}