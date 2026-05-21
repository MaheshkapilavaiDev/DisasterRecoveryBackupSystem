package com.drbsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drbsystem.entity.BackupMetadata;

@Repository
public interface BackupRepository extends JpaRepository<BackupMetadata, Long>{

}
