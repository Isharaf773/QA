package com.example.todo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc // <-- THIS ANNOTATION IS ALSO REQUIRED
public class TaskStepDefinitions {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository; // This will now be correctly injected
    
    private ResultActions resultActions;

    @Given("my to-do list is empty")
    public void my_to_do_list_is_empty() {
        taskRepository.deleteAll();
    }

    @When("I add a new task with the title {string} via the UI")
    public void i_add_a_new_task_with_the_title_via_the_ui(String title) throws Exception {
        resultActions = mockMvc.perform(post("/tasks").param("title", title));
    }

    @Then("the task {string} should be in my list on the UI")
    public void the_task_should_be_in_my_list_on_the_ui(String title) throws Exception {
        mockMvc.perform(get("/tasks"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString(title)));
    }
}
/*import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc // This sets up a fake environment to test our controller
public class TaskStepDefinitions {

    @Autowired
    private MockMvc mockMvc; // A tool to simulate sending HTTP requests

    @Autowired
    private TaskRepository taskRepository;

    private ResultActions resultActions;

    @Given("my to-do list is empty")
    public void my_to_do_list_is_empty() {
        taskRepository.deleteAll(); // Clean the database before the test
    }

    @When("I add a new task with the title {string}")
    public void i_add_a_new_task_with_the_title(String title) throws Exception {
        // Simulate sending a POST request to our API
        resultActions = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"" + title + "\"}"));
    }

    @Then("the task {string} should be in my list")
    public void the_task_should_be_in_my_list(String title) throws Exception {
        // Simulate sending a GET request to see if the task was saved
        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title", is(title))); // Check if the first task has the correct title
    }
}*/