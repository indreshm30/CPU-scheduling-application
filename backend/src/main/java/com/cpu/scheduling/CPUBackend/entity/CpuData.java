package com.cpu.scheduling.CPUBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CpuData implements Comparable<CpuData> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    private int arrivalTime;
    private int burstTime;
    private int priorityValue;

    @Override
    public int compareTo(@NotNull CpuData data) {
        return data.getArrivalTime() - this.arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriorityValue() {
        return priorityValue;
    }
}
