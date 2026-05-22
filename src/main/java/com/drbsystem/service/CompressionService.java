package com.drbsystem.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class CompressionService {

	public String compressFile(String sourceFile) throws Exception {

		String zipFile =
                "C:/backups/compressed/compressed-"
                + System.currentTimeMillis()
                + ".zip";


		FileInputStream fis = new FileInputStream(sourceFile);

		FileOutputStream fos = new FileOutputStream(zipFile);

		ZipOutputStream zos = new ZipOutputStream(fos);

		zos.putNextEntry(new ZipEntry(sourceFile));

		byte[] buffer = new byte[1024];

		int length;

		while ((length = fis.read(buffer)) > 0) {

			zos.write(buffer, 0, length);
		}

		zos.closeEntry();

		zos.close();

		fis.close();

		return zipFile;
	}
}