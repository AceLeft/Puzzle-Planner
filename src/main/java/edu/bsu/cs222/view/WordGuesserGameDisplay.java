package edu.bsu.cs222.view;

import edu.bsu.cs222.model.WordGuesser;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();
    private final WordGuesser wordGuesser = new WordGuesser();
    private final StringBuilder previousGuesses = new StringBuilder();

    public WordGuesserGameDisplay() throws IOException {
    }

    public VBox makeWordGuesserVBox(){
        guessButton.setOnAction((event) -> {
            String key = wordGuesser.doLettersMatch(guessInputField.getText());
            previousGuesses.append(guessInputField.getText()).append("\t").append(key).append("\n");
            Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
        });
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                guessInputField,
                guessButton,
                guessesLabel
        );
        return vbox;
    }
}
