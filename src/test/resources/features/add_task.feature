Feature: Task Management
  As a user of the to-do list application,
  I want to be able to add a new task,
  so that I can track my work.

  Scenario: User successfully adds a new task
    Given my to-do list is empty
    When I add a new task with the title "Learn BDD with Cucumber"
    Then the task "Learn BDD with Cucumber" should be in my list