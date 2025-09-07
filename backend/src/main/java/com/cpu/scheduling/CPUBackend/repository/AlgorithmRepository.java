package com.cpu.scheduling.CPUBackend.repository;

import com.cpu.scheduling.CPUBackend.entity.CpuData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmRepository extends JpaRepository<CpuData, Long> {
}
