package com.cpu.scheduling.CPUBackend.service;


import com.cpu.scheduling.CPUBackend.entity.CpuData;
import com.cpu.scheduling.CPUBackend.repository.AlgorithmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgorithmServiceImplementation implements AlgorithmService{

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Override
    public void saveData(CpuData data) {
        algorithmRepository.save(data);
    }

    @Override
    public void clear() {
        algorithmRepository.deleteAll();
    }

    @Override
    public List<CpuData> getData() {
        return algorithmRepository.findAll();
    }
}
