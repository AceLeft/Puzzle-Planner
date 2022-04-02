package edu.bsu.cs222.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestTaskInventory {
    TaskInventory taskInventory = new TaskInventory();

    public TestTaskInventory() throws IOException {
    }

    @Test
    public void testAddTask() {
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
        Assertions.assertEquals("stretch", taskInventory.getTaskAt(0));
        Assertions.assertEquals("dishes", taskInventory.getTaskAt(1));

    }

    @Test
    public void testRemoveTask() throws IOException {
        taskInventory.addTask("stretch");
        taskInventory.addTask("dishes");
        taskInventory.removeTask("stretch");
        Assertions.assertNotEquals("stretch", taskInventory.getTaskAt(0));
    }
}
