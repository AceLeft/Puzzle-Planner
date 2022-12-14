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

import java.io.FileNotFoundException;
import java.io.IOException;

public class PuzzlePlannerApplication extends Application {
    private final TextField taskInputField = new TextField();
    private final Button taskAddButton = new Button("Add");
    private final Button taskListSaveButton = new Button("Save List");
    private final Button taskListLoadButton = new Button("Load List");
    private final Label taskListLabel = new Label("Tasks:");
    private final Label instructionsLabel = new Label("Create a To-Do list by adding a task.");
    private TaskInventory taskInventory = new TaskInventory();
    private final TabPane tabPane = new TabPane();
    private final Button taskRemoveButton = new Button("Delete");
    private final ListView<String> taskListView = new ListView<>();

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
        VBox vbox = new VBox();
        defineVBoxChildren();
        addChildrenToVBox(vbox);
        return setTabs(vbox);
    }

    private void defineVBoxChildren() {
        setListViewItemsToTaskList();
        setTaskAddButtonAction();
        setTaskRemoveButtonAction();
        setTaskListSaveButtonAction();
        setTaskListLoadButtonAction();
    }

    private void setTaskAddButtonAction() {
        taskAddButton.setOnAction(pressAdd -> {
            String userInput = taskInputField.getText();
            taskInventory.addTask(userInput);
            setListViewItemsToTaskList();
            taskInputField.clear();
        });
    }

    private void setTaskRemoveButtonAction() {
        taskRemoveButton.setOnAction(pressRemove -> {
            try {
                taskInventory.removeTask(getListViewSelectedItem());
            } catch (IOException e) {
                e.printStackTrace();
            }
            setListViewItemsToTaskList();
        });
    }

    private void setTaskListSaveButtonAction() {
        taskListSaveButton.setOnAction(pressSave -> {
            FileSavePopUp fileSavePopUp = new FileSavePopUp(taskInventory);
            try {
                fileSavePopUp.showSavePopUp();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setTaskListLoadButtonAction() {
        taskListLoadButton.setOnAction(pressLoad -> {
            FileLoadPopUp fileLoadPopUp = new FileLoadPopUp();
            String path = fileLoadPopUp.showLoadPopUp();
            try {
                taskInventory = new TaskInventory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setListViewItemsToTaskList();
        });
    }

    private void addChildrenToVBox(VBox vbox) {
        vbox.getChildren().addAll(
                instructionsLabel,
                taskInputField,
                taskAddButton,
                taskRemoveButton,
                taskListLabel,
                taskListView,
                taskListSaveButton,
                taskListLoadButton
        );
    }

    private void setListViewItemsToTaskList() {
        ObservableList<String> taskOutputList = FXCollections.observableArrayList(taskInventory.getTaskList());
        taskListView.setItems(taskOutputList);
    }

    private String getListViewSelectedItem() {
        return taskListView.getSelectionModel().getSelectedItem();
    }

    private TabPane setTabs(VBox vbox) {
        addTaskTab(vbox);
        addWordGuesserGameTab();
        setTabPaneStyle();
        return tabPane;
    }

    private void addTaskTab(VBox vbox){
        Tab taskTab = new Tab("Tasks", vbox);
        tabPane.getTabs().add(taskTab);
    }

    private void addWordGuesserGameTab(){
        WordGuesserGameDisplay wordGuesserGameDisplay = new WordGuesserGameDisplay(taskInventory);
        Tab wordGuesserGameTab = wordGuesserGameDisplay.makeWordGuesserGameTab();
        tabPane.getTabs().add(wordGuesserGameTab);
    }

    private void setTabPaneStyle(){
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        String tabPanePadding = "5px";
        String tabPaneStyle = "-fx-padding:" + tabPanePadding;
        tabPane.setStyle(tabPaneStyle);
    }
}
