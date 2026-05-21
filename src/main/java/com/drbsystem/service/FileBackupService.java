package com.drbsystem.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileBackupService {

	@Value("${backup.location}")
	private String backupLocation;

	public String backupFiles(String sourceDir) throws Exception {

		String zipName = "file-backup-" + System.currentTimeMillis() + ".zip";
		String zipPath = backupLocation + zipName;

		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));

		File folder = new File(sourceDir);

		for (File file : Objects.requireNonNull(folder.listFiles())) {
			zos.putNextEntry(new ZipEntry(file.getName()));
			Files.copy(file.toPath(), zos);
			zos.closeEntry();
		}

		zos.close();

		return zipPath;
	}
}
