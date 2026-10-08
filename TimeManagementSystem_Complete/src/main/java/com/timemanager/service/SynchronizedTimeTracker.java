package com.timemanager.service;

public class SynchronizedTimeTracker {
    private long totalSeconds;

    public synchronized void addSeconds(long seconds) {
        totalSeconds += seconds;
    }

    public synchronized long getTotalSeconds() {
        return totalSeconds;
    }
}
