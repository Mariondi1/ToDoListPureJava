package ru.mikhailov.springcourse.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskInterface {
    Task createTask(String taskName, String description, TaskStatus status, LocalDate deadline);
    List<Task> getTasks();
    Optional<Task> updateTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline);
    Optional<Task>  deleteTask(int id);
    List<Task> filterByStatus(TaskStatus status);
    List<Task> sortedByDeadline();
}
