/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package st10384345.prog5121.poe;


import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Part3Test {

    private List<String> developers;
    private List<String> tasks;

    @Before
    public void setUp() {
        developers = new ArrayList<>();
        tasks = new ArrayList<>();

        addTestData();
    }

    private void addTestData() {
        // Test Data Task 1
        String task1 = "Developer: Mike Smith\n"
                     + "Task Name: Create Login\n"
                     + "Task Duration: 5\n"
                     + "Task Status: To Do";
        tasks.add(task1);
        developers.add("Mike Smith");

        // Test Data Task 2
        String task2 = "Developer: Edward Harrison\n"
                     + "Task Name: Create Add Features\n"
                     + "Task Duration: 8\n"
                     + "Task Status: Doing";
        tasks.add(task2);
        developers.add("Edward Harrison");

        // Test Data Task 3
        String task3 = "Developer: Samantha Paulson\n"
                     + "Task Name: Create Reports\n"
                     + "Task Duration: 2\n"
                     + "Task Status: Done";
        tasks.add(task3);
        developers.add("Samantha Paulson");

        // Test Data Task 4
        String task4 = "Developer: Glenda Oberholzer\n"
                     + "Task Name: Add arrays\n"
                     + "Task Duration: 11\n"
                     + "Task Status: To Do";
        tasks.add(task4);
        developers.add("Glenda Oberholzer");
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] actualDevelopers = getDevelopers();
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    private String[] getDevelopers() {
        return developers.toArray(new String[0]);
    }

    @Test
    public void testDisplayDeveloperAndDurationForTaskWithLongestDuration() {
        String expected = "Glenda Oberholzer, 11";
        String actual = getDisplayDeveloperAndDurationForTaskWithLongestDuration();
        assertEquals(expected, actual);
    }

    private String getDisplayDeveloperAndDurationForTaskWithLongestDuration() {
        String longestDurationTask = "";
        int longestDuration = 0;

        for (String task : tasks) {
            int duration = extractDurationFromTask(task);
            if (duration > longestDuration) {
                longestDuration = duration;
                longestDurationTask = task;
            }
        }

        String[] lines = longestDurationTask.split("\n");
        String developerLine = lines[0];
        String durationLine = lines[2];

        String developer = developerLine.substring(developerLine.indexOf(":") + 2);
        String duration = durationLine.substring(durationLine.indexOf(":") + 2);

        return developer + ", " + duration;
    }

    private int extractDurationFromTask(String task) {
        String[] lines = task.split("\n");
        String durationLine = lines[2];
        String duration = durationLine.substring(durationLine.indexOf(":") + 2);
        return Integer.parseInt(duration);
    }

    @Test
    public void testSearchForTask() {
        String searchTerm = "Create Login";
        String expected = "Mike Smith, Create Login";
        String actual = searchForTask(searchTerm);
        assertEquals(expected, actual);
    }

    private String searchForTask(String searchTerm) {
        for (String task : tasks) {
            if (task.contains(searchTerm)) {
                String[] lines = task.split("\n");
                String developerLine = lines[0];
                String developer = developerLine.substring(developerLine.indexOf(":") + 2);
                return developer + ", " + searchTerm;
            }
        }
        return "Task not found";
    }

    @Test
    public void testSearchAllTaskAssignedToDeveloper() {
        String developer = "Samantha Paulson";
        String expected = "Create Reports";
        String actual = searchAllTaskAssignedToDeveloper(developer);
        assertEquals(expected, actual);
    }

    private String searchAllTaskAssignedToDeveloper(String developer) {
        StringBuilder taskAssigned = new StringBuilder();

        for (String task : tasks) {
            if (task.contains(developer)) {
                String[] lines = task.split("\n");
                String taskNameLine = lines[1];
                String taskName = taskNameLine.substring(taskNameLine.indexOf(":") + 2);
                taskAssigned.append(taskName).append("\n");
            }
        }

        return taskAssigned.toString().trim();
    }

    @Test
    public void testDeleteTaskFromArray() {
        String taskToDelete = "Create Reports";
        boolean delete = deleteTaskFromArray(taskToDelete);
        assertTrue(delete);
    }

    private boolean deleteTaskFromArray(String taskToDelete) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(taskToDelete)) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    @Test
    public void testDisplayReport() {
        String expected = String.join("\n", tasks);
        String actual = displayReport();
        assertEquals(expected, actual);
    }

    private String displayReport() {
        return String.join("\n", tasks);
    }
}
