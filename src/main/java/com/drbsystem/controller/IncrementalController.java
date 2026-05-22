package com.drbsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drbsystem.service.IncrementalBackupService;

@RestController
@RequestMapping("/incremental")
public class IncrementalController {

	@Autowired
	private IncrementalBackupService incrementalBackupService;

	@GetMapping("/last")
	public String lastBackup() throws Exception {

		return incrementalBackupService.getLastBackupTime();
	}
}
