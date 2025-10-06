package com.example.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // We'll use a fake repository for our test

    @InjectMocks
    private TaskService taskService; // This is the class we want to test

    @Test
    void shouldSaveTaskWhenAddTaskIsCalled() {
        // Arrange: Create a new task
        Task task = new Task("Learn TDD");

        // Act: Call the method that we wish existed
        taskService.addTask(task);

        // Assert: Verify that the save method of our repository was called exactly 1 time
        verify(taskRepository, times(1)).save(task);
    }
    @Test
void shouldThrowErrorWhenAddingTaskWithEmptyTitle() {
    // Arrange: Create a task with an empty title
    Task taskWithEmptyTitle = new Task("");

    // Act & Assert: Check that an IllegalArgumentException is thrown
    assertThrows(IllegalArgumentException.class, () -> {
        taskService.addTask(taskWithEmptyTitle);
    });
}
}