package edu.bsu.cs222.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();

    public VBox makeWordGuesserVBox(){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                guessInputField,
                guessButton,
                guessesLabel
        );
        return vbox;
    }
}
