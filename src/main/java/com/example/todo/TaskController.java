package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // This is for web pages
public class TaskController {

    @Autowired
    private TaskService taskService;

    // This handles requests for the HTML page at http://localhost:8080/tasks
    @GetMapping("/tasks")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("newTask", new Task());
        return "tasks";
    }

    // This handles the form submission from the HTML page
    @PostMapping("/tasks")
    public String addTask(@ModelAttribute Task newTask) {
        taskService.addTask(newTask);
        return "redirect:/tasks";
    }
}