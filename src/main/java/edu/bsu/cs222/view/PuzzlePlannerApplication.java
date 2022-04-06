package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.IOException;


public class PuzzlePlannerApplication extends Application {
    private final TextField taskInputField = new TextField();
    private final Button taskAddButton = new Button("Add");
    private final Label taskListLabel = new Label("Tasks:");
    private final Label instructionsLabel = new Label("Create a To-Do list by adding a task.");
    private final TaskInventory taskInventory = new TaskInventory();
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    private final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private final TabPane puzzlePlannerAppTabPane = new TabPane();
    private final Button removeButton = new Button("Delete");

    public PuzzlePlannerApplication() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.setHeight(SCREEN_HEIGHT / 1.3);
        primaryStage.setWidth((SCREEN_WIDTH) / 4.0);
        primaryStage.setX((SCREEN_WIDTH) / 4);
        primaryStage.setY(SCREEN_HEIGHT / 10);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            taskInventory.closePrintWriter();
            Platform.exit();
        });
    }

    private Parent createUI() {
        VBox vbox = new VBox();
        ListView<String> taskOutputTable = new ListView<>();
        taskAddButton.setOnAction((event) -> {
            String userInput = taskInputField.getText();
            taskInventory.addTask(userInput);
            ArrayList<String> taskOutput = new ArrayList();
            for (String task : taskInventory.getTaskList()) {
                taskOutput.add(task);
            }
            ObservableList<String> taskOutputList = FXCollections.observableArrayList(taskOutput);
            taskOutputTable.setItems(taskOutputList);
            taskOutputTable.getSelectionModel().getSelectedItem();
            removeButton.setOnAction((event2 -> {
                try{
                    taskOutputList.remove(taskOutputTable.getSelectionModel().getSelectedItem());
                    taskInventory.getTaskList().remove(taskOutputTable.getSelectionModel().getSelectedItem());
                }
                catch(Exception e){
                    //work in progress
                }

            }));
            taskInputField.clear();
        });

        vbox.getChildren().addAll(
                instructionsLabel,
                taskInputField,
                taskAddButton,
                taskListLabel,
                taskOutputTable,
                removeButton
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
