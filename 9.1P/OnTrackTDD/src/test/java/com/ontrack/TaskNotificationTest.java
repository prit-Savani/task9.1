package com.ontrack;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TaskNotificationService.
 */
public class TaskNotificationTest {
    
    private TaskNotificationService notificationService;
    private TaskService taskService;
    
    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
        notificationService = new TaskNotificationService(taskService);
    }
    
    @Test
    public void testGetOverdueTasks() {
        // Arrange
        String pastDate = LocalDate.now().minusDays(1).toString();
        String futureDate = LocalDate.now().plusDays(1).toString();
        
        Task overdueTask = new Task(1L, "Overdue Task", "This task is overdue", TaskStatus.TODO, pastDate);
        Task upcomingTask = new Task(2L, "Future Task", "This task is not due yet", TaskStatus.TODO, futureDate);
        Task completedTask = new Task(3L, "Completed Task", "This task is overdue but completed", TaskStatus.COMPLETED, pastDate);
        
        taskService.addTask(overdueTask);
        taskService.addTask(upcomingTask);
        taskService.addTask(completedTask);
        
        // Act
        List<Task> overdueTasks = notificationService.getOverdueTasks();
        
        // Assert
        assertEquals(1, overdueTasks.size());
        assertEquals(overdueTask, overdueTasks.get(0));
    }
    
    @Test
    public void testGetTasksDueToday() {
        // Arrange
        String todayDate = LocalDate.now().toString();
        String pastDate = LocalDate.now().minusDays(1).toString();
        String futureDate = LocalDate.now().plusDays(1).toString();
        
        Task todayTask = new Task(1L, "Today's Task", "This task is due today", TaskStatus.TODO, todayDate);
        Task pastTask = new Task(2L, "Past Task", "This task was due yesterday", TaskStatus.TODO, pastDate);
        Task futureTask = new Task(3L, "Future Task", "This task is due tomorrow", TaskStatus.TODO, futureDate);
        Task completedTodayTask = new Task(4L, "Completed Today Task", "This task is due today but completed", TaskStatus.COMPLETED, todayDate);
        
        taskService.addTask(todayTask);
        taskService.addTask(pastTask);
        taskService.addTask(futureTask);
        taskService.addTask(completedTodayTask);
        
        // Act
        List<Task> todayTasks = notificationService.getTasksDueToday();
        
        // Assert
        assertEquals(1, todayTasks.size());
        assertEquals(todayTask, todayTasks.get(0));
    }
    
    @Test
    public void testGetUpcomingTasks() {
        // Arrange
        String todayDate = LocalDate.now().toString();
        String tomorrowDate = LocalDate.now().plusDays(1).toString();
        String nextWeekDate = LocalDate.now().plusDays(6).toString();
        String farFutureDate = LocalDate.now().plusDays(14).toString();
        
        Task todayTask = new Task(1L, "Today's Task", "This task is due today", TaskStatus.TODO, todayDate);
        Task tomorrowTask = new Task(2L, "Tomorrow's Task", "This task is due tomorrow", TaskStatus.TODO, tomorrowDate);
        Task nextWeekTask = new Task(3L, "Next Week Task", "This task is due next week", TaskStatus.TODO, nextWeekDate);
        Task farFutureTask = new Task(4L, "Far Future Task", "This task is due in two weeks", TaskStatus.TODO, farFutureDate);
        
        taskService.addTask(todayTask);
        taskService.addTask(tomorrowTask);
        taskService.addTask(nextWeekTask);
        taskService.addTask(farFutureTask);
        
        // Act
        List<Task> upcomingTasks = notificationService.getUpcomingTasks(7); // Get tasks due in the next 7 days
        
        // Assert
        assertEquals(2, upcomingTasks.size());
        assertTrue(upcomingTasks.contains(tomorrowTask));
        assertTrue(upcomingTasks.contains(nextWeekTask));
        assertFalse(upcomingTasks.contains(todayTask));
        assertFalse(upcomingTasks.contains(farFutureTask));
    }
    
    @Test
    public void testGetTaskReminders() {
        // Arrange
        String todayDate = LocalDate.now().toString();
        String tomorrowDate = LocalDate.now().plusDays(1).toString();
        String pastDate = LocalDate.now().minusDays(1).toString();
        
        Task overdueTask = new Task(1L, "Overdue Task", "This task is overdue", TaskStatus.TODO, pastDate);
        Task todayTask = new Task(2L, "Today's Task", "This task is due today", TaskStatus.TODO, todayDate);
        Task tomorrowTask = new Task(3L, "Tomorrow's Task", "This task is due tomorrow", TaskStatus.TODO, tomorrowDate);
        
        taskService.addTask(overdueTask);
        taskService.addTask(todayTask);
        taskService.addTask(tomorrowTask);
        
        // Act
        List<TaskNotification> reminders = notificationService.getTaskReminders();
        
        // Assert
        assertEquals(3, reminders.size());
        
        // Check the notification messages
        boolean foundOverdue = false;
        boolean foundToday = false;
        boolean foundTomorrow = false;
        
        for (TaskNotification notification : reminders) {
            if (notification.getTaskId() == 1L) {
                assertEquals("URGENT: The task 'Overdue Task' is overdue!", notification.getMessage());
                assertEquals(NotificationPriority.HIGH, notification.getPriority());
                foundOverdue = true;
            } else if (notification.getTaskId() == 2L) {
                assertEquals("REMINDER: The task 'Today's Task' is due today.", notification.getMessage());
                assertEquals(NotificationPriority.MEDIUM, notification.getPriority());
                foundToday = true;
            } else if (notification.getTaskId() == 3L) {
                assertEquals("UPCOMING: The task 'Tomorrow's Task' is due tomorrow.", notification.getMessage());
                assertEquals(NotificationPriority.LOW, notification.getPriority());
                foundTomorrow = true;
            }
        }
        
        assertTrue(foundOverdue && foundToday && foundTomorrow);
    }
} 