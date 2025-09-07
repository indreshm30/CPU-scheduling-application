package com.cpu.scheduling.CPUBackend.service;

import com.cpu.scheduling.CPUBackend.entity.CpuData;

import java.util.List;

public interface AlgorithmService {

    public void saveData(CpuData data);

    public void clear();

    public List<CpuData> getData();
}
