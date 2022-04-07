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
import javafx.stage.Stage;

import java.io.IOException;



public class PuzzlePlannerApplication extends Application {
    private final TextField taskInputField = new TextField();
    private final Button taskAddButton = new Button("Add");
    private final Label taskListLabel = new Label("Tasks:");
    private final Label instructionsLabel = new Label("Create a To-Do list by adding a task.");
    private final TaskInventory taskInventory = new TaskInventory();
    private final TabPane puzzlePlannerAppTabPane = new TabPane();
    private final Button removeButton = new Button("Delete");
    private final ListView<String> taskOutputTable = new ListView<>();

    public PuzzlePlannerApplication() throws IOException {
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(createUI()));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            taskInventory.closePrintWriter();
            Platform.exit();
        });
    }

    private Parent createUI() {
        taskAddButton.setDefaultButton(true);
        //TODO: rename taskOutputTable
        VBox vbox = new VBox();

        setListViewItemsToTaskList();
        taskAddButton.setOnAction((event) -> {
            String userInput = taskInputField.getText();
            taskInventory.addTask(userInput);
            setListViewItemsToTaskList();
            taskInputField.clear();
        });
        removeButton.setOnAction((event2 -> {
            try {
                taskInventory.removeTask(taskOutputTable.getSelectionModel().getSelectedItem());
            } catch (IOException e) {
                //TODO: i dunno
            }
            setListViewItemsToTaskList();
        }));

        vbox.getChildren().addAll(
                instructionsLabel,
                taskInputField,
                taskAddButton,
                taskListLabel,
                taskOutputTable,
                removeButton
        );

        return setTabs(vbox);
    }

    private void setListViewItemsToTaskList() {
        ObservableList<String> taskOutputList = FXCollections.observableArrayList(taskInventory.getTaskList());
        taskOutputTable.setItems(taskOutputList);
    }

    private TabPane setTabs(VBox vbox) { //refactor
        Tab taskTab = new Tab("Tasks", vbox);
        puzzlePlannerAppTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        puzzlePlannerAppTabPane.getTabs().add(taskTab);
        WordGuesserGameDisplay wordGuesserGameDisplay = new WordGuesserGameDisplay(taskInventory);
        puzzlePlannerAppTabPane.getTabs().add(wordGuesserGameDisplay.makeWordGuesserGameTab());
        puzzlePlannerAppTabPane.setStyle("-fx-padding: 5px");
        return puzzlePlannerAppTabPane;
    }
}
