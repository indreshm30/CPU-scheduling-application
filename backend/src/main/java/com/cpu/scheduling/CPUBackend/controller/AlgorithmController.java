package com.cpu.scheduling.CPUBackend.controller;

import com.cpu.scheduling.CPUBackend.entity.CpuData;
import com.cpu.scheduling.CPUBackend.service.AlgorithmService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Builder
@CrossOrigin(origins = "*")
public class AlgorithmController {

    // Controller -> Service -> Repository
    @Autowired
    private AlgorithmService algorithmService;

    @PostMapping("/saveData")
    public String saveCPUData(@RequestBody CpuData cpuData){
        algorithmService.saveData(cpuData);
        return "Successful";
    }

    @GetMapping("/clear")
    public void clearData() {
        algorithmService.clear();
    }

    @GetMapping("/fcfs")
    public ArrayList<ArrayList<Integer>> SolvingFCFS() {
        List<CpuData> data = algorithmService.getData();
        data.sort((d1, d2) -> {
            return d1.getArrivalTime() - d2.getArrivalTime();
        });

        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();

        for (CpuData datum : data) {
            arrival_time.add(datum.getArrivalTime());
            burst_time.add(datum.getBurstTime());
        }

        ArrayList<Integer> completion_time = new ArrayList<>();
        int start_time = 0;

        int n = arrival_time.size();
        for(int i = 0; i < n; i++) {
            if(start_time >= arrival_time.get(i)) {
                start_time += burst_time.get(i);
                completion_time.add(start_time);
            }
            else{
                int difference = arrival_time.get(i) - start_time;
                start_time += difference;
                start_time += burst_time.get(i);
                completion_time.add(start_time);
            }
        }

        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            turnaround_time.add(completion_time.get(i) - arrival_time.get(i));
        }

        for(int i = 0; i < n; i++) {
            waiting_time.add(turnaround_time.get(i) - burst_time.get(i));
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);

        return result;
    }
}
