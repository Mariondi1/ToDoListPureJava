package ru.mikhailov.springcourse.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class Task {
    private int id;
    private String taskName;
    private String description;
    private TaskStatus status;
    private LocalDate deadline;
}
