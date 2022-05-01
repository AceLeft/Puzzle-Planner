package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileSavePopUp {
    private final FileChooser fileChooser;
    private final TaskInventory taskInventory;

    public FileSavePopUp(TaskInventory taskInventory) {
        this.taskInventory = taskInventory;
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TEXT files (*.txt)", "*.txt"));
    }

    public void showSavePopUp() throws FileNotFoundException {
        Stage fileSaveStage = new Stage();
        File taskTextFile = fileChooser.showSaveDialog(fileSaveStage);
        if (taskTextFile != null) {
            saveTextToFile(taskTextFile);
        }
    }

    private void saveTextToFile(File taskTextFile) throws FileNotFoundException {
        PrintWriter taskWriter;
        taskWriter = new PrintWriter(taskTextFile);
        for (String task : taskInventory.getTaskList()) {
            taskWriter.println(task);
        }
        taskWriter.close();

    }
}
