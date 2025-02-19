import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mikhailov.springcourse.Models.Task;
import ru.mikhailov.springcourse.Models.TaskStatus;
import ru.mikhailov.springcourse.TaskService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void testCreateTask() {
        Task newTask = taskService.createTask(1, "TestTask", "TestDescription", TaskStatus.TODO, LocalDate.of(2025, 2, 20));

        // Проверяем, что задача добавлена в список
        assertEquals(1, taskService.getTasks().size());
        assertTrue(taskService.getTasks().contains(newTask));

        // Проверяем, что данные задачи соответствуют ожиданиям
        Task taskFromList = taskService.getTasks().get(0);
        assertEquals(1, taskFromList.getId());
        assertEquals("TestTask", taskFromList.getTaskName());
        assertEquals("TestDescription", taskFromList.getDescription());
        assertEquals(TaskStatus.TODO, taskFromList.getStatus());
        assertEquals(LocalDate.of(2025, 2, 20), taskFromList.getDeadline());
    }

    @Test
    void testUpdateTask() {
        Task newTask = taskService.createTask(1, "TestTask", "TestDescription", TaskStatus.TODO, LocalDate.of(2025, 2, 20));
        List<Task> updateTasks=taskService.updateTask(1,"UpdateTask","UpdateDescription",TaskStatus.DONE,LocalDate.of(2025,2,22));
        assertFalse(updateTasks.isEmpty());
        Task updateTask = updateTasks.get(0);


        assertEquals(1, taskService.getTasks().size());
        assertEquals("UpdateTask",updateTask.getTaskName());
        assertEquals("UpdateDescription",updateTask.getDescription());
        assertEquals(TaskStatus.DONE,updateTask.getStatus());
        assertEquals(LocalDate.of(2025, 2, 22),updateTask.getDeadline());
    }

    @Test
    void testDeleteTask() {
        Task newTask = taskService.createTask(1, "TestTask", "TestDescription", TaskStatus.TODO, LocalDate.of(2025, 2, 20));
        assertEquals(1, taskService.getTasks().size());
        assertTrue(taskService.getTasks().contains(newTask));

        taskService.deleteTask(1);
        assertEquals(0, taskService.getTasks().size());

    }

    @Test
    void testFilterByStatus() {
        Task newTask1 = taskService.createTask(1, "TestTask1", "TestDescription1", TaskStatus.TODO, LocalDate.of(2025, 2, 20));
        Task newTask2 = taskService.createTask(2, "TestTask2", "TestDescription2", TaskStatus.IN_PROGRESS, LocalDate.of(2025, 2, 20));
        Task newTask3 = taskService.createTask(3, "TestTask3", "TestDescription3", TaskStatus.DONE, LocalDate.of(2025, 2, 20));
        assertEquals(3, taskService.getTasks().size());

        List<Task> filteredTasks = taskService.filterByStatus(TaskStatus.DONE);
        assertEquals(1, filteredTasks.size());
        assertTrue(filteredTasks.contains(newTask3));

    }

    @Test
    void testSortedByDeadline() {
        Task newTask1 = taskService.createTask(1, "TestTask1", "TestDescription1", TaskStatus.TODO, LocalDate.of(2028, 2, 20));
        Task newTask2 = taskService.createTask(2, "TestTask2", "TestDescription2", TaskStatus.IN_PROGRESS, LocalDate.of(2027, 2, 20));
        Task newTask3 = taskService.createTask(3, "TestTask3", "TestDescription3", TaskStatus.DONE, LocalDate.of(2025, 2, 20));
        assertEquals(3, taskService.getTasks().size());
        assertTrue(taskService.getTasks().contains(newTask1));
        assertTrue(taskService.getTasks().contains(newTask2));
        assertTrue(taskService.getTasks().contains(newTask3));
        List<Task> sortedTasks=taskService.sortedByDeadline();
        assertEquals(3, sortedTasks.size());
        assertEquals("TestTask3", sortedTasks.get(0).getTaskName());
        assertEquals("TestTask2", sortedTasks.get(1).getTaskName());
        assertEquals("TestTask1", sortedTasks.get(2).getTaskName());
    }
}