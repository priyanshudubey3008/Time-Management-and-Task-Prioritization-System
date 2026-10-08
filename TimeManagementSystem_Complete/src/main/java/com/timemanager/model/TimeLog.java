package com.timemanager.model;

import java.time.LocalDateTime;

public class TimeLog {
    private int logId;
    private int taskId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long durationSeconds;

    public TimeLog(int logId, int taskId, LocalDateTime startTime,
                   LocalDateTime endTime, long durationSeconds) {
        this.logId = logId;
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationSeconds = durationSeconds;
    }

    public int getLogId() { return logId; }
    public int getTaskId() { return taskId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public long getDurationSeconds() { return durationSeconds; }
}
