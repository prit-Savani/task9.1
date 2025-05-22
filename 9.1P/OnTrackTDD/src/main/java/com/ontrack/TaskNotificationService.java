package com.ontrack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing task notifications and reminders.
 */
public class TaskNotificationService {
    
    private TaskService taskService;
    
    public TaskNotificationService(TaskService taskService) {
        this.taskService = taskService;
    }
    
    /**
     * Retrieves all tasks that are overdue (due date is before today) and not completed.
     * 
     * @return List of overdue tasks
     */
    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        return taskService.getAllTasks().stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate(), formatter);
                    return task.getStatus() != TaskStatus.COMPLETED 
                           && task.getStatus() != TaskStatus.CANCELLED
                           && dueDate.isBefore(today);
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves all tasks that are due today and not completed.
     * 
     * @return List of tasks due today
     */
    public List<Task> getTasksDueToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        return taskService.getAllTasks().stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate(), formatter);
                    return task.getStatus() != TaskStatus.COMPLETED 
                           && task.getStatus() != TaskStatus.CANCELLED
                           && dueDate.isEqual(today);
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves tasks that are due within the specified number of days (excluding today) and not completed.
     * 
     * @param days Number of days to look ahead
     * @return List of upcoming tasks within the specified period
     */
    public List<Task> getUpcomingTasks(int days) {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        
        return taskService.getAllTasks().stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate(), formatter);
                    return task.getStatus() != TaskStatus.COMPLETED 
                           && task.getStatus() != TaskStatus.CANCELLED
                           && dueDate.isAfter(today)
                           && (dueDate.isBefore(futureDate) || dueDate.isEqual(futureDate));
                })
                .collect(Collectors.toList());
    }
    
    /**
     * Generates notifications for tasks that need attention:
     * - Overdue tasks (HIGH priority)
     * - Tasks due today (MEDIUM priority)
     * - Tasks due tomorrow (LOW priority)
     * 
     * @return List of task notifications
     */
    public List<TaskNotification> getTaskReminders() {
        List<TaskNotification> notifications = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        
        // Check for overdue tasks (high priority)
        List<Task> overdueTasks = getOverdueTasks();
        for (Task task : overdueTasks) {
            String message = "URGENT: The task '" + task.getTitle() + "' is overdue!";
            notifications.add(new TaskNotification(task.getId(), message, NotificationPriority.HIGH));
        }
        
        // Check for tasks due today (medium priority)
        List<Task> todayTasks = getTasksDueToday();
        for (Task task : todayTasks) {
            String message = "REMINDER: The task '" + task.getTitle() + "' is due today.";
            notifications.add(new TaskNotification(task.getId(), message, NotificationPriority.MEDIUM));
        }
        
        // Check for tasks due tomorrow (low priority)
        taskService.getAllTasks().stream()
                .filter(task -> {
                    LocalDate dueDate = LocalDate.parse(task.getDueDate(), formatter);
                    return task.getStatus() != TaskStatus.COMPLETED 
                           && task.getStatus() != TaskStatus.CANCELLED
                           && dueDate.isEqual(tomorrow);
                })
                .forEach(task -> {
                    String message = "UPCOMING: The task '" + task.getTitle() + "' is due tomorrow.";
                    notifications.add(new TaskNotification(task.getId(), message, NotificationPriority.LOW));
                });
        
        return notifications;
    }
} 