package com.drbsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drbsystem.service.FileBackupService;
import com.drbsystem.service.RestoreService;

@RestController
@RequestMapping("/files")
public class FileBackupController {

	@Autowired
	private FileBackupService fileBackupService;

	@Autowired
	private RestoreService restoreService;

	@PostMapping("/backup")
	public String backupFiles() throws Exception {

		return fileBackupService.backupFiles("C:/testfiles/");
	}

	@PostMapping("/restore")
	public String restoreFiles(@RequestParam String zipName)
	        throws Exception {

	    String zipPath = "C:/backups/" + zipName;

	    return restoreService.restoreFiles(
	            zipPath,
	            "C:/testfiles/"
	    );
	}
	@GetMapping("/checksum")
	public String getChecksum(@RequestParam String fileName)
	        throws Exception {

	    String filePath = "C:/backups/" + fileName;

	    return fileBackupService.generateChecksum(filePath);
	}
}