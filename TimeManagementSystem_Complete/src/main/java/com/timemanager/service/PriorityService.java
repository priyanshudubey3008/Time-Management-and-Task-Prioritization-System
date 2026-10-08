package com.timemanager.service;

import com.timemanager.model.Task;
import java.util.Comparator;
import java.util.List;

public class PriorityService {
    // Polymorphism: works with any Task implementation of Prioritizable.
    public <T extends Task> void calculateAndSort(List<T> tasks) {
        tasks.forEach(t -> t.setPriorityScore(t.calculatePriorityScore()));
        tasks.sort(Comparator.comparingDouble(Task::getPriorityScore).reversed());
    }
}
