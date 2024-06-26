/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10384345.prog5121.poe;

import java.util.Scanner;
import java.util.regex.*;
import javax.swing.*;

class Task {
    
    
    private String Name_;
    private String Surname_;
    private String Username_Reg;
    private String Password_Reg;
    private String Username_Login;
    private String Password_Login;
    boolean flag;
    boolean flag1;
    boolean flag2;
    String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[\\]{}:;',?/*~$^+=<>]).{8,}$";
    private final Pattern pattern = Pattern.compile(regex);

    Scanner sc = new Scanner(System.in);

    public String getName_() {
        return Name_;
    }

    public void setName_(String Name_) {
        this.Name_ = Name_;
    }

    public String getSurname_() {
        return Surname_;
    }

    public void setSurname_(String Surname_) {
        this.Surname_ = Surname_;
    }

    public String getUsername_Reg() {
        return Username_Reg;
    }

    public void setUsername_Reg(String Username_Reg) {
        this.Username_Reg = Username_Reg;
    }

    public String getPassword_Reg() {
        return Password_Reg;
    }

    public void setPassword_Reg(String Password_Reg) {
        this.Password_Reg = Password_Reg;
    }

    public String getUsername_Login() {
        return Username_Login;
    }

    public void setUsername_Login(String Username_Login) {
        this.Username_Login = Username_Login;
    }

    public String getPassword_Login() {
        return Password_Login;
    }

    public void setPassword_Login(String Password_Login) {
        this.Password_Login = Password_Login;
    }

    // Method 1
    public boolean checkUserName(String Username_Reg) {
        flag = Username_Reg.contains("_") && Username_Reg.length() <= 5;
        return flag;
    }

    // Method 2
    public boolean checkPasswordComplexity(String Password_Reg) {
        Matcher matcher = pattern.matcher(Password_Reg);
        flag1 = matcher.matches();
        return flag1;
    }

    // Method 4
    public boolean LoginUser() {
        flag2 = Password_Login.equals(Password_Reg) && Username_Login.equals(Username_Reg);
        return flag2;
    }

    // Method 5
    public void returnLoginStatus() {
        while (!LoginUser()) {
            System.out.println("\nUsername or password incorrect, please try again.");
            System.out.print("Please re-enter your username >> ");
            Username_Login = sc.next();
            setUsername_Login(Username_Login);
            System.out.print("Please re-enter your password >> ");
            Password_Login = sc.next();
            setPassword_Login(Password_Login);
        }

        System.out.println("\nWelcome " + Name_ + " " + Surname_ + "! It is great to see you.");
    }
    
    
    private String choice;
    private int size;
    private int count;
    private String[] taskName;
    private String[] taskDesc;
    private String[] developerName;
    private String[] taskDur;
    private int[] status;
    private String[] taskID;
    private int total;

    // Setter for choice
    public void setChoice_(String choice) {
        this.choice = choice;
    }

    // Verifies the user's choice
    public String verifyChoice() {
        while (!choice.matches("[0-7]")) {
            choice = JOptionPane.showInputDialog(null, "Please select a valid option (0-7):", "Invalid Choice", JOptionPane.ERROR_MESSAGE);
        }
        return choice;
    }

    // Setter for task size
    public void setSize_(int size) {
        this.size = size;
    }

    // Setter for current task index
    public void setCount_(int count) {
        this.count = count;
    }
    
    
    public int[] getStatus() {
        return status;
    }

    public void setStatus(int[] status) {
        this.status = status;
    }

    // Setter for task name array
    public void setTask_Name(String[] taskName) {
        this.taskName = taskName;
    }

    // Verifies the task name (example: must not be empty)
    public String verifyTaskName() {
        while (taskName[count] == null || taskName[count].isEmpty()) {
            taskName[count] = JOptionPane.showInputDialog(null, "Task name cannot be empty. Please enter a valid task name:", "Invalid Task Name", JOptionPane.ERROR_MESSAGE);
        }
        return taskName[count];
    }

    // Setter for task description array
    public void setTask_Desc(String[] taskDesc) {
        this.taskDesc = taskDesc;
    }

    // Checks if task description is 50 characters or less
    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    // Setter for developer name array
    public void setDeveloper_Name(String[] developerName) {
        this.developerName = developerName;
    }

    // Verifies the developer name (example: must contain first and last name)
    public String verifyDeveloperName() {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\s+[a-zA-Z]+$");
        while (!pattern.matcher(developerName[count]).matches()) {
            developerName[count] = JOptionPane.showInputDialog(null, "Please enter a valid developer name (First Last):", "Invalid Developer Name", JOptionPane.ERROR_MESSAGE);
        }
        return developerName[count];
    }

    // Setter for task duration array
    public void setTask_Duration(String[] taskDur) {
        this.taskDur = taskDur;
    }

    // Verifies the task duration (example: must be a positive integer)
    public String verifyTaskDuration() {
        while (!taskDur[count].matches("\\d+") || Integer.parseInt(taskDur[count]) <= 0) {
            taskDur[count] = JOptionPane.showInputDialog(null, "Please enter a valid task duration in hours (positive integer):", "Invalid Duration", JOptionPane.ERROR_MESSAGE);
        }
        return taskDur[count];
    }

    // Setter for total task duration
    public void setTotal_(int total) {
        this.total = total;
    }

    // Generates a unique task ID (example format: TASK#)
    public String createTaskID() {
        return "TASK" + (count + 1);
    }

    // Prints task details
    public String printTaskDetails() {
        return "Task ID: " + createTaskID()
                + "\nTask Name: " + taskName[count]
                + "\nTask Description: " + taskDesc[count]
                + "\nDeveloper Name: " + developerName[count]
                + "\nTask Duration: " + taskDur[count]
                + "\nTask Status: " + (status[count] == 0 ? "To do" : status[count] == 1 ? "Done" : "Doing");
    }
    
     public String generateTaskID(String taskName, int taskNumber, String developerName) {
        // Extract first two letters of task name
        String taskPrefix = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
        // Extract first three letters of developer's name
        String devPrefix = developerName.substring(0, Math.min(developerName.length(), 3)).toUpperCase();
        // Combine components to form task ID
        return taskPrefix + ":" + (taskNumber + 1) + ":" + devPrefix;
    }
    




}
    

