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
        //verifier que le status est valide
        if(!isValidStatus(newStatus)) {
        	throw new IllegalArgumentException("Statut invalide");
        }
        
        //mettre a jour le status
        //task.setStatus(TaskStatus.valueOf(newStatus));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

	private boolean isValidStatus(String status) {
		// liste des status valide
		return status.equals("TO_DO") || status.equals("IN_PROGRESS") || status.equals("IN_REVIEW") || status.equals("DONE") ;
	}
}
