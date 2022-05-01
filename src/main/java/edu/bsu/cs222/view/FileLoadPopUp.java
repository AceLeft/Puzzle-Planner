package edu.bsu.cs222.view;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

public class FileLoadPopUp {
    private final FileChooser fileChooser;

    public FileLoadPopUp() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Load");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TEXT files (*.txt)", "*.txt"));
    }

    public String showLoadPopUp() {
        Stage fileLoadStage = new Stage();
        File taskTextFile = fileChooser.showOpenDialog(fileLoadStage);
        return taskTextFile.getAbsolutePath();
    }


}