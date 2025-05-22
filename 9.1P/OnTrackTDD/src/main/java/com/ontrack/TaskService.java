package com.ontrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing tasks in the OnTrack task management system.
 */
public class TaskService {
    
    private List<Task> tasks;
    
    public TaskService() {
        this.tasks = new ArrayList<>();
    }
    
    /**
     * Adds a new task to the list.
     * 
     * @param task Task to add
     * @return The added task
     */
    public Task addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(task);
        return task;
    }
    
    /**
     * Retrieves all tasks.
     * 
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Finds a task by its ID.
     * 
     * @param id ID of the task to find
     * @return Optional containing the task if found, empty otherwise
     */
    public Optional<Task> getTaskById(long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }
    
    /**
     * Updates an existing task.
     * 
     * @param updatedTask Task with updated fields
     * @return true if task was updated, false if task not found
     */
    public boolean updateTask(Task updatedTask) {
        if (updatedTask == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == updatedTask.getId()) {
                tasks.set(i, updatedTask);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Deletes a task by its ID.
     * 
     * @param id ID of the task to delete
     * @return true if task was deleted, false if task not found
     */
    public boolean deleteTask(long id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
    
    /**
     * Finds tasks by their status.
     * 
     * @param status Status to filter by
     * @return List of tasks with the specified status
     */
    public List<Task> findTasksByStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
}

/**
 * Represents a task in the system.
 */
class Task {
    private long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String dueDate;
    
    public Task(long id, String title, String description, TaskStatus status, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}

/**
 * Enum representing the possible statuses of a task.
 */
enum TaskStatus {
    TODO,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
} 