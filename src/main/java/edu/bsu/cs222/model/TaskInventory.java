package edu.bsu.cs222.model;

import java.io.*;
import java.util.ArrayList;

public class TaskInventory {

    private final ArrayList<String> taskList = new ArrayList<>();
    private final PrintWriter tasksFile;

    public TaskInventory() throws IOException {
        PrintWriter tasksFile1;
        try {
            tasksFile1 = new PrintWriter("src/main/resources/tasks.txt");
        }
        catch(FileNotFoundException e){
            tasksFile1 = new PrintWriter(new FileWriter("src/main/resources/tasks.txt"));
        }
        tasksFile = tasksFile1;
    }

    public void addTask(String task) {
        if (!task.equals("")) {
            taskList.add(task);
        }
    }

    public void removeTask(String task) {
        taskList.remove(task);
    }

    public String getRandom() {
        int randomNumber = (int) (Math.random() * taskList.size());
        String task;
        try{
            task = taskList.get(randomNumber);
        }
        catch(IndexOutOfBoundsException e){
            task = "You have no tasks in your task list.";
        }
        return task;
    }

    public String getTaskAt(int index){
        return taskList.get(index);
    }
}
