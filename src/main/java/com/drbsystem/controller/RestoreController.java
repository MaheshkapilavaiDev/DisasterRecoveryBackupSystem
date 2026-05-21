package com.drbsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.drbsystem.entity.RestoreLog;
import com.drbsystem.service.RestoreService;

@RestController
@RequestMapping("/restore")
public class RestoreController {

    @Autowired
    private RestoreService restoreService;

    @PostMapping("/{backupId}")
    public String restore(@PathVariable Long backupId)
            throws Exception {

        return restoreService.restoreDatabase(backupId);
    }
    
    @GetMapping("/status/{id}")
    public RestoreLog getRestoreStatus(@PathVariable Long id) {

        return restoreService.getRestoreStatus(id);
    }
}