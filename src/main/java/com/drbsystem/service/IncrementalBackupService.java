package com.drbsystem.service;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class IncrementalBackupService {

	private final String FILE_PATH = "C:/backups/incremental/last-backup.txt";

	public void saveLastBackupTime() throws Exception {

		FileWriter writer = new FileWriter(FILE_PATH);

		writer.write(LocalDateTime.now().toString());

		writer.close();

		System.out.println("Last Backup Time Saved");
	}

	public String getLastBackupTime() throws Exception {

		File file = new File(FILE_PATH);

		if (!file.exists()) {

			return "No Previous Backup";
		}

		return Files.readString(file.toPath());
	}
}
