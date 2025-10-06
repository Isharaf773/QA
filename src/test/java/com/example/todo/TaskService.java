package com.example.todo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    
    public void addTask(Task task) {
    // Add this validation block
    if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
        throw new IllegalArgumentException("Task title cannot be empty.");
    }
    taskRepository.save(task);
}
}