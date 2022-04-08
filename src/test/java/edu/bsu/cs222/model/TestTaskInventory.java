package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestTaskInventory {
    TaskInventory taskInventory = new TaskInventory("src/test/resources/testTasks.txt");


    public TestTaskInventory() throws IOException {
    }

    private void clearFileAddTasks() throws IOException {
        taskInventory.removeAllTasks();
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
    }

    @Test
    public void testAddTaskFirstItem() throws IOException {
        clearFileAddTasks();
        Assertions.assertEquals("stretch", taskInventory.getTaskAt(0));
    }

    @Test
    public void testAddTaskSecondItem() throws IOException {
        clearFileAddTasks();
        Assertions.assertEquals("dishes", taskInventory.getTaskAt(1));
    }

    @Test
    public void testRemoveTask() throws IOException {
        clearFileAddTasks();
        taskInventory.removeTask("stretch");
        Assertions.assertNotEquals("stretch", taskInventory.getTaskAt(0));
    }
}
