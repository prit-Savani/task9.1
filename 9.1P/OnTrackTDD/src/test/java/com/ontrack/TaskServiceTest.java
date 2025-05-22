package com.ontrack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for TaskService.
 */
public class TaskServiceTest {
    
    private TaskService taskService;
    
    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }
    
    @Test
    public void testAddTask() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        
        // Act
        Task addedTask = taskService.addTask(task);
        
        // Assert
        assertEquals(task, addedTask);
        assertEquals(1, taskService.getAllTasks().size());
    }
    
    @Test
    public void testAddNullTask() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> taskService.addTask(null));
    }
    
    @Test
    public void testGetAllTasks() {
        // Arrange
        Task task1 = new Task(1L, "Task 1", "Description 1", TaskStatus.TODO, "2023-12-31");
        Task task2 = new Task(2L, "Task 2", "Description 2", TaskStatus.IN_PROGRESS, "2024-01-15");
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        // Act
        List<Task> tasks = taskService.getAllTasks();
        
        // Assert
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }
    
    @Test
    public void testGetTaskById_Found() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        taskService.addTask(task);
        
        // Act
        Optional<Task> foundTask = taskService.getTaskById(1L);
        
        // Assert
        assertTrue(foundTask.isPresent());
        assertEquals(task.getId(), foundTask.get().getId());
    }
    
    @Test
    public void testGetTaskById_NotFound() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        taskService.addTask(task);
        
        // Act
        Optional<Task> foundTask = taskService.getTaskById(999L);
        
        // Assert
        assertFalse(foundTask.isPresent());
    }
    
    @Test
    public void testUpdateTask_Successful() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        taskService.addTask(task);
        
        Task updatedTask = new Task(1L, "Updated Task", "Updated Description", TaskStatus.IN_PROGRESS, "2023-12-31");
        
        // Act
        boolean result = taskService.updateTask(updatedTask);
        
        // Assert
        assertTrue(result);
        Optional<Task> retrievedTask = taskService.getTaskById(1L);
        assertTrue(retrievedTask.isPresent());
        assertEquals("Updated Task", retrievedTask.get().getTitle());
        assertEquals("Updated Description", retrievedTask.get().getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, retrievedTask.get().getStatus());
    }
    
    @Test
    public void testUpdateTask_NotFound() {
        // Arrange
        Task updatedTask = new Task(999L, "Updated Task", "Updated Description", TaskStatus.IN_PROGRESS, "2023-12-31");
        
        // Act
        boolean result = taskService.updateTask(updatedTask);
        
        // Assert
        assertFalse(result);
    }
    
    @Test
    public void testUpdateNullTask() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(null));
    }
    
    @Test
    public void testDeleteTask_Successful() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        taskService.addTask(task);
        
        // Act
        boolean result = taskService.deleteTask(1L);
        
        // Assert
        assertTrue(result);
        assertEquals(0, taskService.getAllTasks().size());
    }
    
    @Test
    public void testDeleteTask_NotFound() {
        // Arrange
        Task task = new Task(1L, "Test Task", "Test Description", TaskStatus.TODO, "2023-12-31");
        taskService.addTask(task);
        
        // Act
        boolean result = taskService.deleteTask(999L);
        
        // Assert
        assertFalse(result);
        assertEquals(1, taskService.getAllTasks().size());
    }
    
    @Test
    public void testFindTasksByStatus() {
        // Arrange
        Task task1 = new Task(1L, "Task 1", "Description 1", TaskStatus.TODO, "2023-12-31");
        Task task2 = new Task(2L, "Task 2", "Description 2", TaskStatus.IN_PROGRESS, "2024-01-15");
        Task task3 = new Task(3L, "Task 3", "Description 3", TaskStatus.TODO, "2024-02-01");
        
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
        
        // Act
        List<Task> todoTasks = taskService.findTasksByStatus(TaskStatus.TODO);
        
        // Assert
        assertEquals(2, todoTasks.size());
        for (Task task : todoTasks) {
            assertEquals(TaskStatus.TODO, task.getStatus());
        }
    }
    
    @Test
    public void testFindTasksByNullStatus() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> taskService.findTasksByStatus(null));
    }
} 