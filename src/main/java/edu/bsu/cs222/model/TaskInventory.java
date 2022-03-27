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
        if (taskList.size() == 0) {
            return "No tasks.";
        }
        return taskList.get(randomNumber);
    }

    public String getTaskAt(int index){
        return taskList.get(index);
    }

    public ArrayList<String> getTaskList() {
        return taskList;
    }
}
