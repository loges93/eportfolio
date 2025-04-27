package com.contact.Contact_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void testAddTask() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        taskService.addTask(task);
        assertEquals(task, taskService.getTask("12345"));
    }

    @Test
    public void testAddTaskDuplicateId() {
        final Task task1 = new Task("12345", "TaskName1", "This is task description 1.");
        final Task task2 = new Task("12345", "TaskName2", "This is task description 2.");
        taskService.addTask(task1);

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                taskService.addTask(task2);
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Task ID already exists", thrown.getMessage());
    }

    @Test
    public void testDeleteTask() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        taskService.addTask(task);
        taskService.deleteTask("12345");
        assertNull(taskService.getTask("12345"));
    }

    @Test
    public void testUpdateTaskName() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        taskService.addTask(task);
        taskService.updateTask("12345", "NewTaskName", null);
        assertEquals("NewTaskName", task.getName());
    }

    @Test
    public void testUpdateTaskDescription() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        taskService.addTask(task);
        taskService.updateTask("12345", null, "New task description.");
        assertEquals("New task description.", task.getDescription());
    }

    @Test
    public void testUpdateNonExistentTask() {
        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                taskService.updateTask("99999", "NewTaskName", "New task description.");
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Task not found", thrown.getMessage());
    }
}