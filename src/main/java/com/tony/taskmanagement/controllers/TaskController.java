package com.tony.taskmanagement.controllers;
import com.tony.taskmanagement.entities.Project;
import com.tony.taskmanagement.entities.Task;
import com.tony.taskmanagement.services.TaskService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    
    @PatchMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
    	return taskService.updateTaskStatus(taskId, newStatus);
    }
}
