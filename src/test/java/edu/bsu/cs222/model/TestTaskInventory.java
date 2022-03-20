package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTaskInventory {
    TaskInventory taskInventory = new TaskInventory();
    @Test
    public void testAddTask(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
        Assertions.assertEquals("stretch",taskInventory.taskList.get(0));
        Assertions.assertEquals("dishes",taskInventory.taskList.get(1));
    }
    @Test
    public void testRemoveTask(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
        taskInventory.removeTask("stretch");
        Assertions.assertNotEquals("stretch", taskInventory.taskList.get(0));
    }
    @Test
    public void testGrabRandom(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
        taskInventory.addTask("cook");
        taskInventory.addTask("laundry");
        taskInventory.addTask("profit");
        System.out.println("task: " + taskInventory.getRandom());
        System.out.println("task: " + taskInventory.getRandom());
        System.out.println("task: " + taskInventory.getRandom());
    }
}
