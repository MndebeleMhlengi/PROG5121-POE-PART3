/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10384345.prog5121.poe;

import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.*;

public class Part3 {

   
    public static void main(String[] args) {
        // TODO code application logic here
        
        JDialog window = new JDialog();
        Task taskObj = new Task();

        Scanner input = new Scanner(System.in);

        String name;
        String surname;
        String usernameReg;
        String passwordReg;
        String usernameLog;
        String passwordLog;

        System.out.print("Hello there!"
                + "\nTo register an account with us, please follow the instructions below.");

        System.out.print("\nStep 1: Enter your first name >> ");
        name = input.next();
        taskObj.setName_(name);

        System.out.print("\nStep 2: Enter your surname >> ");
        surname = input.next();
        taskObj.setSurname_(surname);

        // Username registration
        System.out.print("\nStep 3: Create a username"
                + "\n* Please note that your username must meet these criteria:"
                + "\n- Must not exceed 5 characters in length,"
                + "\n- Must contain an underscore (_)."
                + "\nEnter a username >> ");
        while (true) {
            usernameReg = input.next();
            taskObj.setUsername_Reg(usernameReg);
            if (taskObj.checkUserName(usernameReg)) {
                break;
            } 
            else {
                System.out.print("Username is not correctly formatted, "
                        + "please make sure that your username contains an underscore and it's not more than 5 characters in length."
                        + "\nEnter a username >> ");
            }
        }

        // Password registration
        System.out.print("\nStep 4: Create a password"
                + "\n* Please note that your password must:"
                + "\n- Be at least 8 characters long,"
                + "\n- Contain a capital letter,"
                + "\n- Contain a number and"
                + "\n- Contain a special character."
                + "\nEnter a password >> ");
        while (true) {
            passwordReg = input.next();
            taskObj.setPassword_Reg(passwordReg);
            if (taskObj.checkPasswordComplexity(passwordReg)) {
                break;
            } 
            else {
                System.out.print("Password is not correctly formatted, "
                        + "please ensure that the password contains at least 8 characters long, a capital letter, a number and a special character."
                        + "\nEnter a password >> ");
            }
        }

        System.out.println("\nCongratulations! You have successfully registered your account.");

        // Login
        System.out.print("\nTo login, please enter your username and password"
                + "\nPlease enter your username >> ");
        usernameLog = input.next();
        taskObj.setUsername_Login(usernameLog);

        System.out.print("\nPlease enter your password >> ");
        passwordLog = input.next();
        taskObj.setPassword_Login(passwordLog);

        taskObj.returnLoginStatus();
        
       String choice;
        String[] taskName = null;
        String[] taskDesc = null; // Added for task description
        String[] developerName = null;
        String[] taskDur = null;
        int[] status = null;
        String[] taskID = null;
        int size = 0;
        int total = 0;
        String[] del = {"Delete", "Cancel"};
        String[] options = {"To do", "Done", "Doing"};

      
 

        // More options for user to choose
        choice = JOptionPane.showInputDialog(null,
                "Please select an action to proceed with:"
                        + "\n1. Add task"
                        + "\n2. Show report"
                        + "\n3. View all completed tasks"
                        + "\n4. Display task with longest duration"
                        + "\n5. Search for task"
                        + "\n6. Search for task developer"
                        + "\n7. Delete a task"
                        + "\n\n0. Quit", "Welcome to EasyKanban", JOptionPane.PLAIN_MESSAGE);
        taskObj.setChoice_(choice);

        choice = taskObj.verifyChoice();

        // Beginning of a loop
        while (!choice.equals("0")) {
            switch (choice) {
                case "1":
                    // Prompting for task amount
                    size = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the amount of tasks you want to create:", null, JOptionPane.PLAIN_MESSAGE));
                    taskObj.setSize_(size);

                    // Initialize arrays based on user input size
                    taskName = new String[size];
                    taskDesc = new String[size]; // Added for task description
                    developerName = new String[size];
                    taskDur = new String[size];
                    status = new int[size];
                    taskID = new String[size];

                    for (int count = 0; count < size; count++) {
                        taskObj.setCount_(count);

                        // Prompt user for task details
                        taskName[count] = JOptionPane.showInputDialog(null, "Enter the name of Task " + (count + 1), "Task name", JOptionPane.PLAIN_MESSAGE);
                        taskDesc[count] = JOptionPane.showInputDialog(null, "Enter the description of Task " + (count + 1) + " (50 characters or less):", "Task description", JOptionPane.PLAIN_MESSAGE);
                        developerName[count] = JOptionPane.showInputDialog(null, "Enter first name and last name of developer assigned to Task " + (count + 1), "Developer details", JOptionPane.PLAIN_MESSAGE);
                        taskDur[count] = JOptionPane.showInputDialog(null, "Enter the estimated task duration in hours for Task " + (count + 1), "Task duration", JOptionPane.PLAIN_MESSAGE);
                        total += Integer.parseInt(taskDur[count]);

                        // Set task details in Task object
                        taskObj.setTask_Name(taskName);
                        taskObj.setTask_Desc(taskDesc);
                        taskObj.setDeveloper_Name(developerName);
                        taskObj.setTask_Duration(taskDur);
                        taskObj.setTotal_(total);

                        // Prompt user for task status
                        status[count] = JOptionPane.showOptionDialog(null, "Please specify the status of Task " + (count + 1), "Task status", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

                        // Generate task ID
                        taskID[count] = taskObj.generateTaskID(taskName[count], count, developerName[count]);

                        // Display task details
                        JOptionPane.showMessageDialog(null,
                                "Task Name: " + taskName[count] + "\n" +
                                        "Task Description: " + taskDesc[count] + "\n" +
                                        "Developer: " + developerName[count] + "\n" +
                                        "Task Duration: " + taskDur[count] + " hours\n" +
                                        "Task Status: " + options[status[count]] + "\n" +
                                        "Task ID: " + taskID[count],
                                "Task Details", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "2":
                    // Display report of all tasks
                    StringBuilder report = new StringBuilder();

                    for (int i = 0; i < size; i++) {
                        if (taskName[i] != null) {
                            report.append("Task name: ").append(taskName[i])
                                    .append("\nTask description: ").append(taskDesc[i])
                                    .append("\nDeveloper: ").append(developerName[i])
                                    .append("\nDuration: ").append(taskDur[i]).append(" hours")
                                    .append("\nTask ID: ").append(taskID[i])
                                    .append("\nStatus: ").append(options[status[i]]).append("\n\n");
                        }
                    }

                    if (size == 0) {
                        JOptionPane.showMessageDialog(null, "Please add tasks to view report", "No task to display", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "3":
                    // Display completed tasks
                    StringBuilder completedTasks = new StringBuilder();
                    int found = 0;

                    if (status != null && developerName != null && taskName != null && taskDur != null) {
                        for (int i = 0; i < size; i++) {
                            if (status[i] == 1) {
                                completedTasks.append("Task name: ").append(taskName[i])
                                        .append("\nTask description: ").append(taskDesc[i])
                                        .append("\nDeveloper: ").append(developerName[i])
                                        .append("\nTask duration: ").append(taskDur[i]).append(" hours\n\n");
                                found++;
                            }
                        }
                    }

                    if (found > 0) {
                        JOptionPane.showMessageDialog(null, completedTasks.toString(), "Completed Tasks", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No tasks have been completed yet", "Completed Tasks", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "4":
                    // Display task with longest duration
                    if (size == 0) {
                        JOptionPane.showMessageDialog(null, "Please add tasks to proceed with this action", "No task to display", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        int longestDur = 0;
                        String longestDeveloper = null;

                        for (int i = 0; i < size; i++) {
                            if (Integer.parseInt(taskDur[i]) > longestDur) {
                                longestDur = Integer.parseInt(taskDur[i]);
                                longestDeveloper = developerName[i];
                            }
                        }

                        JOptionPane.showMessageDialog(null, "Developer: " + longestDeveloper + "\nDuration: " + longestDur + " hours", "Task with Longest Duration", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "5":
                    // Search for task by name
                    String search = JOptionPane.showInputDialog(null, "Enter a task name", "Search by Name", JOptionPane.PLAIN_MESSAGE);
                    boolean matchFound = false;

                    for (int i = 0; i < size; i++) {
                        if (search.equalsIgnoreCase(taskName[i])) {
                            JOptionPane.showMessageDialog(null,
                                    "Task Name: " + taskName[i] + "\n" +
                                            "Task Description: " + taskDesc[i] + "\n" +
                                            "Developer: " + developerName[i] + "\n" +
                                            "Task Duration: " + taskDur[i] + " hours\n" +
                                            "Task Status: " + options[status[i]] + "\n" +
                                            "Task ID: " + taskID[i],
                                    "Task Found", JOptionPane.PLAIN_MESSAGE);
                            matchFound = true;
                            break; // Exit loop on first match found
                        }
                    }

                    if (!matchFound) {
                        JOptionPane.showMessageDialog(null, "Sorry, this task does not exist", "No Match Found", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "6":
                    // Search for tasks by developer
                    String searchDev = JOptionPane.showInputDialog(null, "Enter a developer's name", "Search by Developer", JOptionPane.PLAIN_MESSAGE);
                    boolean devMatchFound = false;
                    StringBuilder searchResults = new StringBuilder();

                    for (int i = 0; i < size; i++) {
                        if (searchDev.equalsIgnoreCase(developerName[i])) {
                            searchResults.append("Task name: ").append(taskName[i])
                                    .append("\nTask description: ").append(taskDesc[i])
                                    .append("\nStatus: ").append(options[status[i]]).append("\n\n");
                            devMatchFound = true;
                        }
                    }

                    if (devMatchFound) {
                        JOptionPane.showMessageDialog(null, searchResults.toString(), "Tasks assigned to " + searchDev, JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "This developer is not assigned to any tasks", "No Match Found", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                case "7":
                    // Delete a task by name
                    String taskToDelete = JOptionPane.showInputDialog(null, "Enter the task name to delete", "Delete Task", JOptionPane.PLAIN_MESSAGE);
                    boolean taskDeleted = false;

                    for (int i = 0; i < size; i++) {
                        if (taskToDelete.equalsIgnoreCase(taskName[i])) {
                            int confirmDelete = JOptionPane.showOptionDialog(null, "Are you sure you want to delete " + taskName[i] + "?", "Deleting Task", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, del, null);

                            if (confirmDelete == 0) {
                                taskName[i] = null;
                                taskDesc[i] = null; // Clear task description
                                developerName[i] = null;
                                taskDur[i] = null;
                                status[i] = 3; // Mark as deleted status
                                taskID[i] = null;
                                JOptionPane.showMessageDialog(null, "Task successfully deleted", "Deleted", JOptionPane.PLAIN_MESSAGE);
                                taskDeleted = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Task not deleted", "Cancel", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }

                    if (!taskDeleted) {
                        JOptionPane.showMessageDialog(null, "This task does not exist", "No Match Found", JOptionPane.PLAIN_MESSAGE);
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid option.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }

            // Prompt user for the next action
            choice = JOptionPane.showInputDialog(null,
                    "Please select an action to proceed with:"
                            + "\n1. Add task"
                            + "\n2. Show report"
                            + "\n3. View all completed tasks"
                            + "\n4. Display task with longest duration"
                            + "\n5. Search for task"
                            + "\n6. Search for task developer"
                            + "\n7. Delete a task"
                            + "\n\n0. Quit", "Welcome to EasyKanban", JOptionPane.PLAIN_MESSAGE);
            taskObj.setChoice_(choice);

            choice = taskObj.verifyChoice();
        }
    }
}

