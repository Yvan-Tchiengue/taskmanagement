package com.tony.taskmanagement.controllers;
import com.tony.taskmanagement.entities.Task;
import com.tony.taskmanagement.services.TaskService;
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

    @PatchMapping("/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestParam String status) {
        return taskService.updateTaskStatus(taskId, status);
    }
}
