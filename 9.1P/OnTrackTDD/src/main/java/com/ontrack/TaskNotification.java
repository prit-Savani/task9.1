package com.ontrack;

/**
 * Represents a notification for a task in the system.
 */
public class TaskNotification {
    private long taskId;
    private String message;
    private NotificationPriority priority;
    
    public TaskNotification(long taskId, String message, NotificationPriority priority) {
        this.taskId = taskId;
        this.message = message;
        this.priority = priority;
    }
    
    public long getTaskId() {
        return taskId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public NotificationPriority getPriority() {
        return priority;
    }
} 