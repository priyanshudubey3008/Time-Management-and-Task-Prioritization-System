package com.timemanager.model;

import java.time.LocalDate;

public class Task implements Prioritizable {
    private int taskId;
    private int userId;
    private String taskName;
    private String description;
    private LocalDate deadline;
    private String importance;
    private Integer categoryId;
    private String status;
    private double priorityScore;

    public Task() {}

    public Task(int taskId, int userId, String taskName, String description,
                LocalDate deadline, String importance, Integer categoryId,
                String status, double priorityScore) {
        this.taskId = taskId;
        this.userId = userId;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.importance = importance;
        this.categoryId = categoryId;
        this.status = status;
        this.priorityScore = priorityScore;
    }

    @Override
    public double calculatePriorityScore() {
        double importanceScore = switch (importance) {
            case "HIGH" -> 100;
            case "MEDIUM" -> 60;
            default -> 30;
        };
        long days = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        double deadlineScore = days <= 0 ? 100 : Math.max(10, 100 - days * 5);
        return (importanceScore * 0.6) + (deadlineScore * 0.4);
    }

    public int getTaskId() { return taskId; }
    public int getUserId() { return userId; }
    public String getTaskName() { return taskName; }
    public String getDescription() { return description; }
    public LocalDate getDeadline() { return deadline; }
    public String getImportance() { return importance; }
    public Integer getCategoryId() { return categoryId; }
    public String getStatus() { return status; }
    public double getPriorityScore() { return priorityScore; }

    public void setTaskId(int v) { taskId = v; }
    public void setUserId(int v) { userId = v; }
    public void setTaskName(String v) { taskName = v; }
    public void setDescription(String v) { description = v; }
    public void setDeadline(LocalDate v) { deadline = v; }
    public void setImportance(String v) { importance = v; }
    public void setCategoryId(Integer v) { categoryId = v; }
    public void setStatus(String v) { status = v; }
    public void setPriorityScore(double v) { priorityScore = v; }
}
