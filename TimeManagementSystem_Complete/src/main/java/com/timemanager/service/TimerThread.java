package com.timemanager.service;

public class TimerThread extends Thread {
    private volatile boolean running = true;
    private long elapsedSeconds = 0;

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                elapsedSeconds++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public synchronized long getElapsedSeconds() {
        return elapsedSeconds;
    }

    public synchronized void stopTimer() {
        running = false;
    }
}
