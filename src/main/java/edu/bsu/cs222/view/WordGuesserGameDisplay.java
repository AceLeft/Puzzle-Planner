package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesserGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label( "");
    private final Label instructionsLabel = new Label("");
    private final WordGuesserGame wordGuesser = new WordGuesserGame();
    private final StringBuilder previousGuesses = new StringBuilder();
    private final TaskInventory taskInventory;
    private final int wordLength = wordGuesser.getTemplateWord().length();

    public WordGuesserGameDisplay(TaskInventory taskInventory) {
        this.taskInventory = taskInventory;
    }

    public Tab makeWordGuesserGameTab() {
        VBox vbox = createWordGuesserVbox();
        return new Tab("Word Guesser", vbox);
    }

    private VBox createWordGuesserVbox() {
        guessButton.setOnAction((event) -> processGuess());
        guessInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                processGuess();
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
        return vbox;
    }
    //TODO: break down?
    private void processGuess() {
        String guess = guessInputField.getText();
        String key = wordGuesser.makeHintListFromGuess(guess);
        StringBuilder reformattedKey = formatKey(key);
        showGuessAndKey(guess, reformattedKey);
        if (wordGuesser.matchesTemplate(guess)) {
            new PuzzleDonePopUp(taskInventory.getRandom());
            wordGuesser.createNewTemplateWord();
            previousGuesses.setLength(0);
        }
        guessInputField.clear();
    }

    private StringBuilder formatKey(String key) {
        StringBuilder reformattedKey = new StringBuilder();
        for (char character : key.toCharArray()) {
            reformattedKey.append(character).append(" ");
        }
        return reformattedKey;
    }

    private void showGuessAndKey(String guess, StringBuilder reformattedKey) {
        boolean guessInputFieldEmpty = guessInputField.getText().equals("");
        if (guessInputField.getText().length() <= wordLength && !guessInputFieldEmpty) {
            previousGuesses.append(guess).append("\t\t").append(reformattedKey).append("\n");
        }
        Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
    }


}
