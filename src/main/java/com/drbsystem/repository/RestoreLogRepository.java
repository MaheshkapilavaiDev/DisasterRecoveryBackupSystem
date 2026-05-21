package com.drbsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drbsystem.entity.RestoreLog;

@Repository
public interface RestoreLogRepository extends JpaRepository<RestoreLog, Long>{

}
