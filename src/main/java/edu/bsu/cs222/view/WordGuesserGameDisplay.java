package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesserGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();
    private final Label instructionsLabel = new Label("");
    private final WordGuesserGame wordGuesser = new WordGuesserGame();
    private final StringBuilder previousGuesses = new StringBuilder();
    private final TaskInventory taskInventory;

    public WordGuesserGameDisplay(TaskInventory taskInventory) {
        this.taskInventory = taskInventory;
    }

    private void formatGuessButton() {
        String guess = guessInputField.getText();
        String key = wordGuesser.makeClueFromGuess(guess);
        StringBuilder keyReformatted = new StringBuilder();
        for (char character : key.toCharArray()) {
            keyReformatted.append(character).append(" ");
        }
        if (guessInputField.getText().length() < 7 && !guessInputField.getText().equals("")) {
            previousGuesses.append(guess).append("\t\t").append(keyReformatted).append("\n");
        }
        Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
        if (wordGuesser.isTemplate(guess)) {
            createPuzzleDonePopUp();
        }
        guessInputField.clear();
    }

    public Tab makeWordGuesserGameTab() {
        guessButton.setOnAction((event) -> formatGuessButton());
        guessInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                formatGuessButton();
            }
        });
        instructionsLabel.setText("""
                Guess the 6 letter word that I'm thinking of.
                 When you input a word, you will receive a clue.
                 A "-" means the letter is not in my word
                 A "*" means the letter is in the word, but not in that spot.\s""");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                instructionsLabel,
                guessInputField,
                guessButton,
                guessesLabel
        );
        return new Tab("Word Guesser", vbox);
    }

    public void createPuzzleDonePopUp() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Label taskExitLabel = new Label("Close the window when you're done.");
        Label dialogLabel = new Label("You won! Complete this task to play again: " + taskInventory.getRandom());
        dialogLabel.setFont(Font.font("Helvetica", 40));
        taskExitLabel.setFont(Font.font(30));
        VBox dialogBox = new VBox();
        dialogBox.setStyle("-fx-padding:5px");
        dialogBox.autosize();
        dialogBox.getChildren().addAll(
                dialogLabel,
                taskExitLabel
        );
        dialogStage.setScene(new Scene(dialogBox));
        dialogStage.show();
        dialogStage.onCloseRequestProperty();
        wordGuesser.createNewTemplateWord();
        previousGuesses.setLength(0);
    }
}
