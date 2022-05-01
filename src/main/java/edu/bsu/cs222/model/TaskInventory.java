package edu.bsu.cs222.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskInventory {

    private final ArrayList<String> taskList = new ArrayList<>();
    private PrintWriter taskFile;
    private final String fileLocation;

    public TaskInventory() throws IOException {
        fileLocation = "src/main/resources/tasks.txt";
        setUpFileAndTaskList();
    }

    public TaskInventory(String fileLocation) throws IOException {
        this.fileLocation = fileLocation;
        setUpFileAndTaskList();
    }

    private void setUpFileAndTaskList() throws IOException {
        taskFile = new PrintWriter(new FileWriter(fileLocation, true));
        Scanner taskScanner = new Scanner(new File(fileLocation));
        while (taskScanner.hasNext()) {
            taskList.add(taskScanner.nextLine());
        }
        taskScanner.close();
    }

    public void addTask(String task) {
        if (!task.equals("")) {
            taskList.add(task);
            taskFile.println(task);
        }
    }

    public void removeTask(String task) throws IOException {
        taskList.remove(task);
        closePrintWriter();
        taskFile = new PrintWriter(new FileWriter(fileLocation));
        for (String otherTask : taskList) {
            taskFile.println(otherTask);
        }
    }

    public void removeAllTasks() throws IOException {
        closePrintWriter();
        taskFile = new PrintWriter(new FileWriter(fileLocation));
        taskList.clear();
    }

    public String getRandom() {
        int randomNumber = (int) (Math.random() * taskList.size());
        String task;
        try {
            task = taskList.get(randomNumber);
        } catch (IndexOutOfBoundsException e) {
            task = "You have no tasks in your task list.";
        }
        return task;
    }

    public String getTaskAt(int index) {
        return taskList.get(index);
    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }

    public void closePrintWriter() {
        taskFile.close();
    }

}
