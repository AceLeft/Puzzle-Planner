package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileSavePopUp {
    private final FileChooser fileChooser;
    private final TaskInventory taskInventory;

    public FileSavePopUp(TaskInventory taskInventory) {
        this.taskInventory = taskInventory;
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TEXT files (*.txt)", "*.txt"));
    }

    public void showSavePopUp() {
        Stage stage = new Stage();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            saveTextToFile(file);
        }

    }

    private void saveTextToFile(File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            for (String task : taskInventory.getTaskList()) {
                writer.println(task);
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileSavePopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
