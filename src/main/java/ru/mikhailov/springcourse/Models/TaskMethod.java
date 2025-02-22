package ru.mikhailov.springcourse.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskMethod {
    Task createTask(String taskName, String description, TaskStatus status, LocalDate deadline);
    List<Task> getTasks();
    Optional<Task> updateTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline);
    boolean  deleteTask(int id);
    List<Task> filterByStatus(TaskStatus status);
    List<Task> sortedByDeadline();
}
