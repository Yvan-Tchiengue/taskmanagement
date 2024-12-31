package com.tony.taskmanagement.entities;


import jakarta.persistence.*;
import java.time.LocalDateTime;

import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Lob
    private String description;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() {
    	return id;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public LocalDateTime getCreatedAt() {
    	return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
    	this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
    	return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
    	this.updatedAt = updatedAt;
    }
}
