package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesser;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();
    private final WordGuesser wordGuesser = new WordGuesser();
    private final StringBuilder previousGuesses = new StringBuilder();
    private final TaskInventory taskInventory;

    public WordGuesserGameDisplay(TaskInventory taskInventory) throws IOException {
        this.taskInventory = taskInventory;
        previousGuesses.append(wordGuesser.getTemplate());
    }

    public VBox makeWordGuesserVBox(){
        guessButton.setOnAction((event) -> {
            String key = wordGuesser.doLettersMatch(guessInputField.getText());
            previousGuesses.append(guessInputField.getText()).append("\t").append(key).append("\n");
            Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
            if (wordGuesser.isTemplate(guessInputField.getText())){
                createPuzzleDonePopUp();
            }
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                guessInputField,
                guessButton,
                guessesLabel
        );
        return vbox;
    }
    public void createPuzzleDonePopUp(){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Label dialogLabel = new Label("Please do the task: " +taskInventory.getRandom());
        VBox dialogBox = new VBox();
        dialogBox.getChildren().addAll(dialogLabel);
        dialogStage.setScene(new Scene(dialogBox));
        dialogStage.show();
        dialogStage.onCloseRequestProperty();
    }
}
