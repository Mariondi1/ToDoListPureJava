package ru.mikhailov.springcourse;

import lombok.Getter;
import ru.mikhailov.springcourse.Models.Task;
import ru.mikhailov.springcourse.Models.TaskMethod;
import ru.mikhailov.springcourse.Models.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class TaskService implements TaskMethod {
    private final List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
    }


    public Task createTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline) {
        Task task = new Task(id, taskName, description, status, deadline);
        tasks.add(task);
        return task;
    }

    public List <Task> updateTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline) {
        Task task=tasks.stream().filter(task1 -> task1.getId()==id).findFirst().orElse(null);

        assert task != null;
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setStatus(status);
        task.setDeadline(deadline);
        return List.of(task);
    }

    public void deleteTask(int id){
        Task task=tasks.stream().filter(task1 -> task1.getId() == id).findFirst().orElse(null);
        tasks.remove(task);
    }

    public List<Task> filterByStatus(TaskStatus status){
        return tasks.stream()
                .filter(task -> task.getStatus()
                .equals(status))
                .collect(Collectors.toList());
    }

    public List<Task> sortedByDeadline(){
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
