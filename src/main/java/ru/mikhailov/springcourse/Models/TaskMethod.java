package ru.mikhailov.springcourse.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskMethod {
    Task createTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline);
    List<Task> getTasks();
    List <Task> updateTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline);
    void  deleteTask(int id);
    List<Task> filterByStatus(TaskStatus status);
    List<Task> sortedByDeadline();
}
