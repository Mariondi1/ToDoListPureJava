package ru.mikhailov.springcourse;

import ru.mikhailov.springcourse.Models.Task;
import ru.mikhailov.springcourse.Models.TaskStatus;

import java.time.LocalDate;

import java.util.Scanner;




public class Console {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();

        while (true) {
            System.out.print("Vvedite komandу (add, list, update, delete, filter, sort, exit): ");
            String command = scanner.next().toLowerCase();

            switch (command) {
                case "add":
                    scanner.nextLine(); // Ochishchaem bufer
                    System.out.print("Vvedite nazvanie zadachi: ");
                    String taskName = scanner.nextLine();

                    System.out.print("Vvedite opisanie zadachi: ");
                    String description = scanner.nextLine();

                    System.out.print("Vvedite status (TODO, IN_PROGRESS, DONE): ");
                    TaskStatus status = TaskStatus.valueOf(scanner.next().toUpperCase());

                    System.out.print("Vvedite dedlajn (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.next());

                    Task newTask = taskService.createTask(taskService.getTasks().size(), taskName, description, status, deadline);
                    System.out.println("Zadacha dobavlena: " + newTask);
                    break;

                case "list":
                    System.out.println("Spisok zadach:");
                    System.out.println(taskService.getTasks());

                    break;

                case "update":
                    System.out.print("Vvedite ID zadachi: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Vvedite novoe nazvanie: ");
                    String newTaskName = scanner.nextLine();

                    System.out.print("Vvedite novoe opisanie: ");
                    String newDescription = scanner.nextLine();

                    System.out.print("Vvedite novyj status (TODO, IN_PROGRESS, DONE): ");
                    TaskStatus newStatus = TaskStatus.valueOf(scanner.next().toUpperCase());

                    System.out.print("Vvedite novyj dedlajn (YYYY-MM-DD): ");
                    LocalDate newDeadline = LocalDate.parse(scanner.next());

                    taskService.updateTask(updateId, newTaskName, newDescription, newStatus, newDeadline);
                    System.out.println("Zadacha obnovlena!");
                    break;

                case "filter":
                    System.out.println("Vvedite status, po kotoromu khotite otfil'trovat' (TODO, IN_PROGRESS, DONE):");
                    TaskStatus filterMethod = TaskStatus.valueOf(scanner.next().toUpperCase());
                    if (filterMethod == TaskStatus.TODO) {
                        System.out.println(taskService.filterByStatus(TaskStatus.TODO));
                    } else if
                    (filterMethod == TaskStatus.IN_PROGRESS) {
                        System.out.println(taskService.filterByStatus(TaskStatus.IN_PROGRESS));
                    } else {
                        System.out.println(taskService.filterByStatus(TaskStatus.DONE));
                    }
                    break;

                case "sort":
                    System.out.println("Zadachi otsortirovany po dedlajnu:");
                    scanner.nextLine();
                    System.out.println(taskService.sortedByDeadline());
                    break;

                case "delete":
                    System.out.print("Vvedite ID zadachi: ");
                    int deleteId = scanner.nextInt();
                    taskService.deleteTask(deleteId);
                    System.out.println("Zadacha udalena!");
                    break;

                case "exit":
                    System.out.println("Vykhod iz programmy...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Neizvestnaya komanda!");
                    break;
            }
        }
    }
}


