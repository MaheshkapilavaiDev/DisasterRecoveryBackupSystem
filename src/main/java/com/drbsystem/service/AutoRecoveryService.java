package com.drbsystem.service;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.springframework.stereotype.Service;

@Service
public class AutoRecoveryService {

    public void recoverDatabase() {

        File folder = new File("C:/backups/");

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".sql"));

        if (files == null || files.length == 0) {
            System.out.println("No Backup Found");
            return;
        }

        File latestFile = Arrays.stream(files)
                .max(Comparator.comparingLong(File::lastModified))
                .get();

        System.out.println("Recovering Using: " + latestFile.getName());

        // restore logic here
    }
}
