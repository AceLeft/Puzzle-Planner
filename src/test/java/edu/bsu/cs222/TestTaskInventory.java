package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTaskInventory {
    @Test
    public void testAddTask(){
        TaskInventory taskInventory = new TaskInventory();
        taskInventory.addTask("task");
        Assertions.assertEquals("task",taskInventory.taskList.get(0));
    }
}
