package edu.bsu.cs222.model;

import java.util.ArrayList;

public class TaskInventory {

    ArrayList<String> taskList = new ArrayList<>();

    public void addTask(String task) {
        taskList.add(task);
    }
    public void removeTask(String task) {
        taskList.remove(task);
    }

    public String getRandom() {
        int randomNumber = (int)(Math.random()*taskList.size());
        return taskList.get(randomNumber);
    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }
}
