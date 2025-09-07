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

    @GetMapping("/sjf")
    public ArrayList<ArrayList<Integer>> SolvingSJF() {
        List<CpuData> data = algorithmService.getData();
        int n = data.size();
        
        // Create arrays for easier manipulation
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] completed = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = data.get(i).getArrivalTime();
            burstTime[i] = data.get(i).getBurstTime();
        }
        
        int currentTime = 0;
        int completedProcesses = 0;
        
        while (completedProcesses < n) {
            int minBurstTime = Integer.MAX_VALUE;
            int shortestJob = -1;
            
            // Find the shortest job among available processes
            for (int i = 0; i < n; i++) {
                if (!completed[i] && arrivalTime[i] <= currentTime && burstTime[i] < minBurstTime) {
                    minBurstTime = burstTime[i];
                    shortestJob = i;
                }
            }
            
            if (shortestJob == -1) {
                // No process available, move time forward to next arrival
                currentTime++;
            } else {
                // Execute the shortest job
                currentTime += burstTime[shortestJob];
                completionTime[shortestJob] = currentTime;
                turnaroundTime[shortestJob] = completionTime[shortestJob] - arrivalTime[shortestJob];
                waitingTime[shortestJob] = turnaroundTime[shortestJob] - burstTime[shortestJob];
                completed[shortestJob] = true;
                completedProcesses++;
            }
        }
        
        // Convert back to ArrayLists in original order
        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();
        ArrayList<Integer> completion_time = new ArrayList<>();
        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arrival_time.add(arrivalTime[i]);
            burst_time.add(burstTime[i]);
            completion_time.add(completionTime[i]);
            turnaround_time.add(turnaroundTime[i]);
            waiting_time.add(waitingTime[i]);
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);
        
        return result;
    }

    @GetMapping("/srtf")
    public ArrayList<ArrayList<Integer>> SolvingSRTF() {
        List<CpuData> data = algorithmService.getData();
        int n = data.size();
        
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = data.get(i).getArrivalTime();
            burstTime[i] = data.get(i).getBurstTime();
            remainingTime[i] = burstTime[i];
        }
        
        int currentTime = 0;
        int completedProcesses = 0;
        int shortestJob = -1;
        int minRemainingTime = Integer.MAX_VALUE;
        
        while (completedProcesses < n) {
            // Find process with shortest remaining time
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] > 0) {
                    if (remainingTime[i] < minRemainingTime) {
                        minRemainingTime = remainingTime[i];
                        shortestJob = i;
                    }
                }
            }
            
            if (shortestJob == -1) {
                currentTime++;
            } else {
                remainingTime[shortestJob]--;
                minRemainingTime = remainingTime[shortestJob];
                
                if (minRemainingTime == 0) {
                    minRemainingTime = Integer.MAX_VALUE;
                }
                
                if (remainingTime[shortestJob] == 0) {
                    completedProcesses++;
                    completionTime[shortestJob] = currentTime + 1;
                    turnaroundTime[shortestJob] = completionTime[shortestJob] - arrivalTime[shortestJob];
                    waitingTime[shortestJob] = turnaroundTime[shortestJob] - burstTime[shortestJob];
                }
                currentTime++;
            }
        }
        
        // Convert to ArrayLists
        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();
        ArrayList<Integer> completion_time = new ArrayList<>();
        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arrival_time.add(arrivalTime[i]);
            burst_time.add(burstTime[i]);
            completion_time.add(completionTime[i]);
            turnaround_time.add(turnaroundTime[i]);
            waiting_time.add(waitingTime[i]);
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);
        
        return result;
    }

    @GetMapping("/rr")
    public ArrayList<ArrayList<Integer>> SolvingRR(@RequestParam(defaultValue = "3") int timeQuantum) {
        List<CpuData> data = algorithmService.getData();
        int n = data.size();
        
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = data.get(i).getArrivalTime();
            burstTime[i] = data.get(i).getBurstTime();
            remainingTime[i] = burstTime[i];
        }
        
        ArrayList<Integer> queue = new ArrayList<>();
        boolean[] inQueue = new boolean[n];
        
        int currentTime = 0;
        int completedProcesses = 0;
        
        // Add initially available processes to queue
        for (int i = 0; i < n; i++) {
            if (arrivalTime[i] <= currentTime) {
                queue.add(i);
                inQueue[i] = true;
            }
        }
        
        while (completedProcesses < n) {
            if (queue.isEmpty()) {
                currentTime++;
                // Add any newly arrived processes
                for (int i = 0; i < n; i++) {
                    if (arrivalTime[i] <= currentTime && !inQueue[i] && remainingTime[i] > 0) {
                        queue.add(i);
                        inQueue[i] = true;
                    }
                }
                continue;
            }
            
            int currentProcess = queue.remove(0);
            inQueue[currentProcess] = false;
            
            if (remainingTime[currentProcess] <= timeQuantum) {
                currentTime += remainingTime[currentProcess];
                remainingTime[currentProcess] = 0;
                completionTime[currentProcess] = currentTime;
                turnaroundTime[currentProcess] = completionTime[currentProcess] - arrivalTime[currentProcess];
                waitingTime[currentProcess] = turnaroundTime[currentProcess] - burstTime[currentProcess];
                completedProcesses++;
            } else {
                currentTime += timeQuantum;
                remainingTime[currentProcess] -= timeQuantum;
            }
            
            // Add newly arrived processes to queue
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && !inQueue[i] && remainingTime[i] > 0) {
                    queue.add(i);
                    inQueue[i] = true;
                }
            }
            
            // Add current process back to queue if not completed
            if (remainingTime[currentProcess] > 0) {
                queue.add(currentProcess);
                inQueue[currentProcess] = true;
            }
        }
        
        // Convert to ArrayLists
        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();
        ArrayList<Integer> completion_time = new ArrayList<>();
        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arrival_time.add(arrivalTime[i]);
            burst_time.add(burstTime[i]);
            completion_time.add(completionTime[i]);
            turnaround_time.add(turnaroundTime[i]);
            waiting_time.add(waitingTime[i]);
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);
        
        return result;
    }

    @GetMapping("/priority-np")
    public ArrayList<ArrayList<Integer>> SolvingPriorityNonPreemptive() {
        List<CpuData> data = algorithmService.getData();
        int n = data.size();
        
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        boolean[] completed = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = data.get(i).getArrivalTime();
            burstTime[i] = data.get(i).getBurstTime();
            priority[i] = data.get(i).getPriorityValue();
        }
        
        int currentTime = 0;
        int completedProcesses = 0;
        
        while (completedProcesses < n) {
            int highestPriority = Integer.MAX_VALUE;
            int selectedProcess = -1;
            
            // Find highest priority process among available ones
            for (int i = 0; i < n; i++) {
                if (!completed[i] && arrivalTime[i] <= currentTime && priority[i] < highestPriority) {
                    highestPriority = priority[i];
                    selectedProcess = i;
                }
            }
            
            if (selectedProcess == -1) {
                currentTime++;
            } else {
                currentTime += burstTime[selectedProcess];
                completionTime[selectedProcess] = currentTime;
                turnaroundTime[selectedProcess] = completionTime[selectedProcess] - arrivalTime[selectedProcess];
                waitingTime[selectedProcess] = turnaroundTime[selectedProcess] - burstTime[selectedProcess];
                completed[selectedProcess] = true;
                completedProcesses++;
            }
        }
        
        // Convert to ArrayLists
        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();
        ArrayList<Integer> completion_time = new ArrayList<>();
        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arrival_time.add(arrivalTime[i]);
            burst_time.add(burstTime[i]);
            completion_time.add(completionTime[i]);
            turnaround_time.add(turnaroundTime[i]);
            waiting_time.add(waitingTime[i]);
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);
        
        return result;
    }

    @GetMapping("/priority-p")
    public ArrayList<ArrayList<Integer>> SolvingPriorityPreemptive() {
        List<CpuData> data = algorithmService.getData();
        int n = data.size();
        
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] priority = new int[n];
        int[] remainingTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        
        for (int i = 0; i < n; i++) {
            arrivalTime[i] = data.get(i).getArrivalTime();
            burstTime[i] = data.get(i).getBurstTime();
            priority[i] = data.get(i).getPriorityValue();
            remainingTime[i] = burstTime[i];
        }
        
        int currentTime = 0;
        int completedProcesses = 0;
        int highestPriorityProcess = -1;
        int highestPriority = Integer.MAX_VALUE;
        
        while (completedProcesses < n) {
            // Find highest priority process among available ones
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] > 0) {
                    if (priority[i] < highestPriority) {
                        highestPriority = priority[i];
                        highestPriorityProcess = i;
                    }
                }
            }
            
            if (highestPriorityProcess == -1) {
                currentTime++;
            } else {
                remainingTime[highestPriorityProcess]--;
                highestPriority = priority[highestPriorityProcess];
                
                if (highestPriority == Integer.MAX_VALUE) {
                    highestPriority = Integer.MAX_VALUE;
                }
                
                if (remainingTime[highestPriorityProcess] == 0) {
                    completedProcesses++;
                    completionTime[highestPriorityProcess] = currentTime + 1;
                    turnaroundTime[highestPriorityProcess] = completionTime[highestPriorityProcess] - arrivalTime[highestPriorityProcess];
                    waitingTime[highestPriorityProcess] = turnaroundTime[highestPriorityProcess] - burstTime[highestPriorityProcess];
                    highestPriority = Integer.MAX_VALUE;
                }
                currentTime++;
            }
        }
        
        // Convert to ArrayLists
        ArrayList<Integer> arrival_time = new ArrayList<>();
        ArrayList<Integer> burst_time = new ArrayList<>();
        ArrayList<Integer> completion_time = new ArrayList<>();
        ArrayList<Integer> turnaround_time = new ArrayList<>();
        ArrayList<Integer> waiting_time = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            arrival_time.add(arrivalTime[i]);
            burst_time.add(burstTime[i]);
            completion_time.add(completionTime[i]);
            turnaround_time.add(turnaroundTime[i]);
            waiting_time.add(waitingTime[i]);
        }
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(arrival_time);
        result.add(burst_time);
        result.add(completion_time);
        result.add(turnaround_time);
        result.add(waiting_time);
        
        return result;
    }
}
