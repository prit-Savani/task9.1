package com.ontrack;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create the task service and add some sample tasks
        TaskService taskService = new TaskService();
        
        // Add some sample tasks with different due dates
        String today = LocalDate.now().toString();
        String yesterday = LocalDate.now().minusDays(1).toString();
        String tomorrow = LocalDate.now().plusDays(1).toString();
        String nextWeek = LocalDate.now().plusDays(7).toString();
        
        taskService.addTask(new Task(1L, "Complete TDD Assignment", 
                                    "Submit the TDD and CI assignment for SIT707", 
                                    TaskStatus.TODO, yesterday));
                                    
        taskService.addTask(new Task(2L, "Review Assignment Feedback", 
                                    "Check feedback from tutor on submitted tasks", 
                                    TaskStatus.TODO, today));
                                    
        taskService.addTask(new Task(3L, "Prepare for Next Week", 
                                    "Read materials for next week's class", 
                                    TaskStatus.TODO, tomorrow));
                                    
        taskService.addTask(new Task(4L, "Group Project Meeting", 
                                    "Meet with team members to discuss progress", 
                                    TaskStatus.TODO, nextWeek));
                                    
        taskService.addTask(new Task(5L, "Completed Task", 
                                    "This task has already been completed", 
                                    TaskStatus.COMPLETED, yesterday));
        
        // Create the notification service
        TaskNotificationService notificationService = new TaskNotificationService(taskService);
        
        // Display all tasks
        System.out.println("All Tasks:");
        taskService.getAllTasks().forEach(task -> {
            System.out.println(task.getId() + ": " + task.getTitle() + 
                            " - Due: " + task.getDueDate() + 
                            " - Status: " + task.getStatus());
        });
        System.out.println();
        
        // Display overdue tasks
        System.out.println("Overdue Tasks:");
        notificationService.getOverdueTasks().forEach(task -> {
            System.out.println(task.getId() + ": " + task.getTitle());
        });
        System.out.println();
        
        // Display tasks due today
        System.out.println("Tasks Due Today:");
        notificationService.getTasksDueToday().forEach(task -> {
            System.out.println(task.getId() + ": " + task.getTitle());
        });
        System.out.println();
        
        // Display upcoming tasks (next 7 days)
        System.out.println("Upcoming Tasks (Next 7 days):");
        notificationService.getUpcomingTasks(7).forEach(task -> {
            System.out.println(task.getId() + ": " + task.getTitle());
        });
        System.out.println();
        
        // Display all notifications
        System.out.println("Task Notifications:");
        List<TaskNotification> notifications = notificationService.getTaskReminders();
        notifications.forEach(notification -> {
            System.out.println("[" + notification.getPriority() + "] " + notification.getMessage());
        });
    }
} 