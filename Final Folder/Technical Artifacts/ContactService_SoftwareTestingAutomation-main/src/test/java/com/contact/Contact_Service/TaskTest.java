package com.contact.Contact_Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        assertEquals("12345", task.getTaskId());
        assertEquals("TaskName", task.getName());
        assertEquals("This is a task description.", task.getDescription());
    }

    @Test
    public void testTaskInvalidId() {
        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task(null, "TaskName", "This is a task description.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable1);

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task("12345678901", "TaskName", "This is a task description.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable2);
    }

    @Test
    public void testTaskInvalidName() {
        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task("12345", null, "This is a task description.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable1);

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task("12345", "ThisIsAVeryLongTaskNameThatExceedsTwentyCharacters", "This is a task description.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable2);
    }

    @Test
    public void testTaskInvalidDescription() {
        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task("12345", "TaskName", null);
            }
        };
        assertThrows(IllegalArgumentException.class, executable1);

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Task("12345", "TaskName", "This is a very long task description that exceeds the fifty characters limit.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable2);
    }

    @Test
    public void testUpdateName() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        task.setName("NewTaskName");
        assertEquals("NewTaskName", task.getName());

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                task.setName(null);
            }
        };
        assertThrows(IllegalArgumentException.class, executable1);

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                task.setName("ThisIsAVeryLongTaskNameThatExceedsTwentyCharacters");
            }
        };
        assertThrows(IllegalArgumentException.class, executable2);
    }

    @Test
    public void testUpdateDescription() {
        final Task task = new Task("12345", "TaskName", "This is a task description.");
        task.setDescription("New task description.");
        assertEquals("New task description.", task.getDescription());

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                task.setDescription(null);
            }
        };
        assertThrows(IllegalArgumentException.class, executable1);

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                task.setDescription("This is a very long task description that exceeds the fifty characters limit.");
            }
        };
        assertThrows(IllegalArgumentException.class, executable2);
    }
}