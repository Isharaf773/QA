package com.example.todo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Tells Spring this class is a database table
public class Task {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID
    private Long id;
    private String title;

    // Constructors (needed by the framework)
    //add comment
    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    // Getters and Setters (to access the private fields)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}