package com.tony.taskmanagement.services;

import com.tony.taskmanagement.entities.Task;
import com.tony.taskmanagement.enumerations.TaskStatus; 
import com.tony.taskmanagement.repositories.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    public List<Task> getAllTasks(){
    	return taskRepository.findAll();
    }

    public Task updateTaskStatus(Long taskId, String newStatus) {
        Task task = taskRepository.findById(taskId)
        		                                  .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(TaskStatus.valueOf(newStatus));
        return taskRepository.save(task);
    }
}
