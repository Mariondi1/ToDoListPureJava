package ru.mikhailov.springcourse;

import lombok.Getter;
import ru.mikhailov.springcourse.Models.Task;
import ru.mikhailov.springcourse.Models.TaskInterface;
import ru.mikhailov.springcourse.Models.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Getter
public class TaskRepository implements TaskInterface {
    private final List<Task> tasks;
    private static int nextId = 1;




    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }


    public Task createTask(String taskName, String description, TaskStatus status, LocalDate deadline) {
        int id=nextId++;
        Task task = new Task(id, taskName, description, status, deadline);
        tasks.add(task);
        return task;
    }

    public Optional <Task> updateTask(int id, String taskName, String description, TaskStatus status, LocalDate deadline) {

        Optional<Task> optionalTask = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();


        optionalTask.ifPresent(task -> {
            task.setTaskName(taskName);
            task.setDescription(description);
            task.setStatus(status);
            task.setDeadline(deadline);
        });
        return optionalTask;
    }

    public Optional<Task> deleteTask(int id){
        Optional<Task> optionalTask=tasks.stream()
                .filter(task -> task.getId()==id)
                .findFirst();
        optionalTask.ifPresent(tasks::remove);
        return optionalTask;
    }

    public List<Task> filterByStatus(TaskStatus status){
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> sortedByDeadline(){
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
