package com.drbsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BackupMetadata {

	@Id
	@GeneratedValue
	private Long id;

	private String type; // FULL / INCREMENTAL

	private String filePath;

	private String status; // SUCCESS / FAILED / IN_PROGRESS

	private LocalDateTime timestamp;

	public BackupMetadata() {
	}

	public BackupMetadata(String type, String filePath, String status, LocalDateTime timestamp) {

		this.type = type;
		this.filePath = filePath;
		this.status = status;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
