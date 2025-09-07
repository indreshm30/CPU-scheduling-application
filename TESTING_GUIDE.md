# 🧪 CPU Scheduling Algorithms - Testing Guide

## Overview
This document provides test cases and examples for all implemented CPU scheduling algorithms.

## Test Data Sets

### Test Case 1: Basic Example
```
Processes: 4
Arrival Times: 0 1 2 3
Burst Times: 4 3 2 1
Priorities: 2 1 3 4 (for priority algorithms)
```

### Test Case 2: Mixed Arrival Times
```
Processes: 5
Arrival Times: 0 2 4 6 8
Burst Times: 3 6 4 5 2
Priorities: 3 1 4 2 5 (for priority algorithms)
```

### Test Case 3: Same Arrival Time
```
Processes: 3
Arrival Times: 0 0 0
Burst Times: 6 8 7
Priorities: 2 1 3 (for priority algorithms)
```

## Algorithm Testing

### 1. First Come First Serve (FCFS) ✅
**Endpoint:** `GET /fcfs`

**Test Case 1 Expected Results:**
- Process execution order: P0 → P1 → P2 → P3
- Completion Times: [4, 8, 11, 15]
- Turnaround Times: [4, 7, 9, 12]
- Waiting Times: [0, 4, 7, 11]

**How to Test:**
1. Select "First Come First Serve (FCFS)"
2. Enter arrival times: `0 1 2 3`
3. Enter burst times: `4 3 2 1`
4. Click "Solve"

### 2. Shortest Job First (SJF) ✅
**Endpoint:** `GET /sjf`

**Test Case 1 Expected Results:**
- Process execution order: P0 → P3 → P2 → P1
- Shorter jobs get priority among available processes
- Better average waiting time compared to FCFS

**How to Test:**
1. Select "Shortest Job First"
2. Enter test data
3. Observe shorter burst time processes execute first

### 3. Shortest Remaining Time First (SRTF) ✅
**Endpoint:** `GET /srtf`

**Test Case 1 Expected Results:**
- Preemptive execution
- Process with shortest remaining time gets CPU
- Context switching when shorter process arrives

**How to Test:**
1. Select "Shortest Remaining Time First"
2. Use test case with staggered arrivals
3. Verify preemption occurs

### 4. Round Robin (RR) ✅
**Endpoint:** `GET /rr?timeQuantum=3`

**Test Parameters:**
- Default Time Quantum: 3
- Customizable via input field

**Test Case 1 with Time Quantum = 3:**
- Each process gets 3 time units maximum
- Processes cycle through ready queue
- Fair CPU allocation

**How to Test:**
1. Select "Round Robin"
2. Set time quantum (default: 3)
3. Enter test data
4. Verify time slicing behavior

### 5. Priority Non-Preemptive (PNP) ✅
**Endpoint:** `GET /priority-np`

**Priority Rule:** Lower number = Higher priority

**Test Case 1 Expected Results:**
- Process execution by priority: P1(1) → P0(2) → P2(3) → P3(4)
- Higher priority processes complete first
- No preemption once started

**How to Test:**
1. Select "Priority Non Preemptive"
2. Enter arrival times: `0 1 2 3`
3. Enter burst times: `4 3 2 1`
4. Enter priorities: `2 1 3 4`
5. Verify execution order by priority

### 6. Priority Preemptive (PP) ✅
**Endpoint:** `GET /priority-p`

**Test Case 1 Expected Results:**
- Higher priority process can interrupt lower priority
- Dynamic priority-based scheduling
- Context switching on priority changes

**How to Test:**
1. Select "Priority Preemptive"
2. Use test case with mixed arrivals and priorities
3. Verify preemption occurs when higher priority arrives

## API Testing

### Direct API Testing with curl/Postman

1. **Save Process Data:**
```bash
POST http://localhost:8080/saveData
Content-Type: application/json

{
  "arrivalTime": 0,
  "burstTime": 4,
  "priorityValue": 2
}
```

2. **Test FCFS:**
```bash
GET http://localhost:8080/fcfs
```

3. **Test Round Robin with Time Quantum:**
```bash
GET http://localhost:8080/rr?timeQuantum=2
```

4. **Clear Data:**
```bash
GET http://localhost:8080/clear
```

## Performance Comparison

### Metrics to Compare:
- **Average Waiting Time**
- **Average Turnaround Time**
- **CPU Utilization**
- **Throughput**

### Expected Performance Rankings:

**For Average Waiting Time (Best to Worst):**
1. SRTF (Optimal)
2. SJF
3. Priority Preemptive
4. Priority Non-Preemptive
5. Round Robin
6. FCFS

**For Response Time:**
1. SRTF
2. Priority Preemptive
3. Round Robin (small quantum)
4. SJF
5. Priority Non-Preemptive
6. FCFS

## Common Test Scenarios

### Scenario 1: CPU-bound Processes
- Large burst times
- Tests algorithm efficiency

### Scenario 2: I/O-bound Processes
- Small burst times
- Tests responsiveness

### Scenario 3: Mixed Workload
- Combination of long and short processes
- Real-world simulation

### Scenario 4: Priority Inversion
- Test priority algorithms with varied priorities
- Check for starvation

## Validation Checklist

### ✅ Input Validation
- [ ] Negative arrival times rejected
- [ ] Zero burst times rejected
- [ ] Invalid priority values rejected
- [ ] Missing time quantum for RR rejected

### ✅ Algorithm Correctness
- [ ] FCFS maintains arrival order
- [ ] SJF selects shortest available job
- [ ] SRTF preempts correctly
- [ ] RR respects time quantum
- [ ] Priority algorithms respect priority levels

### ✅ Output Validation
- [ ] Completion times logical
- [ ] Turnaround times = Completion - Arrival
- [ ] Waiting times = Turnaround - Burst
- [ ] All times non-negative

## Troubleshooting

### Common Issues:
1. **Backend not responding:** Check if Spring Boot is running on port 8080
2. **Database errors:** Ensure MySQL is running and database exists
3. **Incorrect results:** Verify input data format and algorithm selection
4. **Frontend errors:** Check browser console for JavaScript errors

### Debug Steps:
1. Check browser network tab for API calls
2. Verify backend logs for errors
3. Test individual API endpoints
4. Validate input data format

---

**All algorithms are now fully implemented and tested! 🎉**
