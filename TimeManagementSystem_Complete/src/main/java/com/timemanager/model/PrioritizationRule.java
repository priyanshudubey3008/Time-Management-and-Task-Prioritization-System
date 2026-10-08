package com.timemanager.model;

public class PrioritizationRule {
    private int ruleId;
    private String name;
    private int importanceWeight;
    private int deadlineWeight;
    private boolean active;

    public PrioritizationRule(int ruleId, String name, int importanceWeight,
                              int deadlineWeight, boolean active) {
        this.ruleId = ruleId;
        this.name = name;
        this.importanceWeight = importanceWeight;
        this.deadlineWeight = deadlineWeight;
        this.active = active;
    }

    public int getRuleId() { return ruleId; }
    public String getName() { return name; }
    public int getImportanceWeight() { return importanceWeight; }
    public int getDeadlineWeight() { return deadlineWeight; }
    public boolean isActive() { return active; }
}
