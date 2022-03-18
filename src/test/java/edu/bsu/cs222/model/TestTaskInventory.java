package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTaskInventory {
    TaskInventory taskInventory = new TaskInventory();
    @Test
    public void testAddTask(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("cry");
        Assertions.assertEquals("stretch",taskInventory.taskList.get(0));
        Assertions.assertEquals("cry",taskInventory.taskList.get(1));
    }
    @Test
    public void testRemoveTask(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("cry");
        System.out.println("HERE IT IS " + taskInventory.taskList.get(0));
        taskInventory.removeTask("stretch");
        Assertions.assertNotEquals("stretch", taskInventory.taskList.get(0));
    }
    @Test
    public void testGrabRandom(){
        taskInventory.addTask("stretch");
        taskInventory.addTask("cry");
        taskInventory.addTask("cook");
        taskInventory.addTask("feed hungus");
        taskInventory.addTask("weep");
        taskInventory.addTask("profit");
        System.out.println("Da task be " + taskInventory.getRandom());
        System.out.println("Da task be " + taskInventory.getRandom());
        System.out.println("Da task be " + taskInventory.getRandom());
    }
}
