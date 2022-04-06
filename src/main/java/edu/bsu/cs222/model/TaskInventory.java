package edu.bsu.cs222.model;

import java.util.ArrayList;

public class TaskInventory {

    private final ArrayList<String> taskList = new ArrayList<>();

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
}
