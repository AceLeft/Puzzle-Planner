package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PuzzlePlannerApplication extends Application {
    private final TextField taskInputField = new TextField();
    private final Button taskAddButton = new Button("Add");
    private final Label taskListLabel = new Label("");
    private final TaskInventory taskInventory = new TaskInventory();

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        Stage gameStage = new Stage();
        WordGuesserGameDisplay wordGuesserGameDisplay = new WordGuesserGameDisplay(taskInventory);
        gameStage.setScene(new Scene(wordGuesserGameDisplay.makeWordGuesserVBox()));
        gameStage.show();
        gameStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private Parent createUI() {
        taskAddButton.setOnAction((event) -> {
            String userInput = taskInputField.getText();
            taskInventory.addTask(userInput);
            StringBuilder finalTaskListOutput = new StringBuilder("Tasks:\n");
            for (String task : taskInventory.getTaskList()){
                finalTaskListOutput.append(task).append("\n");
            }
            Platform.runLater(() -> taskListLabel.setText(finalTaskListOutput.toString()));
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                taskInputField,
                taskAddButton,
                taskListLabel
        );
        return vbox;
    }



}
