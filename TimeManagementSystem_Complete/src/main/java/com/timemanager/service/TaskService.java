package com.timemanager.service;

import com.timemanager.dao.TaskDAO;
import com.timemanager.exception.ValidationException;
import com.timemanager.model.Task;

public class TaskService {
    private final TaskDAO dao = new TaskDAO();

    public int create(Task task) throws Exception {
        validate(task);
        task.setPriorityScore(task.calculatePriorityScore());
        return dao.add(task);
    }

    private void validate(Task task) throws ValidationException {
        if(task.getTaskName()==null || task.getTaskName().isBlank())
            throw new ValidationException("Task name is required.");
        if(task.getDeadline()==null)
            throw new ValidationException("Deadline is required.");
        if(task.getImportance()==null)
            throw new ValidationException("Importance is required.");
    }
}
