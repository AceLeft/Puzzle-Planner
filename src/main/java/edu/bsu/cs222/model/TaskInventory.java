package edu.bsu.cs222.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskInventory {

    private final ArrayList<String> taskList = new ArrayList<>();
    private PrintWriter tasksFile;

    public TaskInventory() throws IOException {
        PrintWriter tasksFile1;
        try {
            tasksFile1 = new PrintWriter(new FileWriter("src/main/resources/tasks.txt",true));
        }
        catch(FileNotFoundException e){
            tasksFile1 = new PrintWriter(new FileWriter("src/main/resources/tasks.txt"));
        }
        tasksFile = tasksFile1;
        Scanner taskScanner = new Scanner(new File("src/main/resources/tasks.txt"));
        while(taskScanner.hasNext()){
            taskList.add(taskScanner.nextLine());
        }
        taskScanner.close();
    }

    public void addTask(String task) {
        if (!task.equals("")) {
            taskList.add(task);
            tasksFile.println(task);
        }
    }

    public void removeTask(String task) throws IOException {
        taskList.remove(task);
        closePrintWriter();
        tasksFile = new PrintWriter(new FileWriter("src/main/resources/tasks.txt"));
        for(String otherTask : taskList){
            tasksFile.println(otherTask);
        }
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

    public ArrayList<String> getTaskList() {
        return taskList;
    }

    public void closePrintWriter() {
        tasksFile.close();
    }
}
