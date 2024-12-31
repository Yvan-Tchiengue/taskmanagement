package com.tony.taskmanagement.services;

import com.tony.taskmanagement.entities.Task;
import com.tony.taskmanagement.enumerations.TaskStatus; 
import com.tony.taskmanagement.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setStatus(TaskStatus.valueOf(status));
            return taskRepository.save(task);
        }
        return null;
    }
}
