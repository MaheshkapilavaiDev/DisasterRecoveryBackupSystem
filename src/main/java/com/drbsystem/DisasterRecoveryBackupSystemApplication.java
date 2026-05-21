package com.drbsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DisasterRecoveryBackupSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisasterRecoveryBackupSystemApplication.class, args);
	}

}
