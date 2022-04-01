package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class PuzzlePlannerApplication extends Application {
    private final TextField taskInputField = new TextField();
    private final Button taskAddButton = new Button("Add");
    private final Button taskRemoveButton = new Button("X");
    private final Label taskListLabel = new Label("");
    private final Label instructionsLabel = new Label("Create a To-Do list by adding a task.");
    private final TaskInventory taskInventory = new TaskInventory();
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    private final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private final TabPane puzzlePlannerAppTabPane = new TabPane();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.setHeight(SCREEN_HEIGHT / 1.3);
        primaryStage.setWidth((SCREEN_WIDTH) / 4.0);
        primaryStage.setX((SCREEN_WIDTH) / 4);
        primaryStage.setY(SCREEN_HEIGHT / 10);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    private Parent createUI() {
        taskAddButton.setOnAction((event) -> {
            String userInput = taskInputField.getText();
            taskInventory.addTask(userInput);
            StringBuilder finalTaskListOutput = new StringBuilder("Tasks:\n");
            for (String task : taskInventory.getTaskList()) {
                finalTaskListOutput.append(task).append("\n");
            }
            Platform.runLater(() -> taskListLabel.setText(finalTaskListOutput.toString()));
            taskInputField.clear();
        });
        VBox vbox = new VBox();
        taskRemoveButton.setOnAction((event -> {
            vbox.getChildren().remove(taskListLabel);
            vbox.getChildren().remove(taskRemoveButton);
        }));


        vbox.getChildren().addAll(
                instructionsLabel,
                taskInputField,
                taskAddButton,
                taskListLabel
        );
        Tab taskTab = new Tab("Tasks", vbox);
        puzzlePlannerAppTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        puzzlePlannerAppTabPane.getTabs().add(taskTab);
        WordGuesserGameDisplay wordGuesserGameDisplay = new WordGuesserGameDisplay(taskInventory);
        puzzlePlannerAppTabPane.getTabs().add(wordGuesserGameDisplay.makeWordGuesserGameTab());
        puzzlePlannerAppTabPane.setStyle("-fx-padding: 5px");
        return puzzlePlannerAppTabPane;
    }
}
