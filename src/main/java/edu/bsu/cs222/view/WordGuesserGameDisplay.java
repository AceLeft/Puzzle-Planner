package edu.bsu.cs222.view;

import edu.bsu.cs222.model.Hint;
import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesserGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.List;

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

    private void processGuess() {
        String guess = guessInputField.getText();
        List<Hint> hintList = wordGuesser.makeHintListFromGuess(guess);
        showGuessAndDisplayableHint(guess, hintList);
        if (wordGuesser.matchesTemplate(guess)) {
            new PuzzleDonePopUp(taskInventory.getRandom());
            resetPuzzle();
        }
        guessInputField.clear();
    }

    private StringBuilder formatHintListForDisplay(List<Hint> hintList, String guess) {
        StringBuilder hintString = new StringBuilder();
        String incorrectSymbol = "-";
        String semicorrectSymbol = "*";
        //correctSymbol is the correct letter
        for (int i = 0; i < hintList.size(); i++) {
            if (hintList.get(i).equals(Hint.INCORRECT)) {
                hintString.append(incorrectSymbol);
            } else if (hintList.get(i).equals(Hint.SEMICORRECT)) {
                hintString.append(semicorrectSymbol);
            } else {
                hintString.append(guess.charAt(i));
            }
        }
        return hintString;
    }

    private void showGuessAndDisplayableHint(String guess, List<Hint> hintList) {
        StringBuilder hintString = formatHintListForDisplay(hintList, guess);
        boolean guessInputFieldEmpty = guessInputField.getText().equals("");
        if (guessInputField.getText().length() <= wordLength && !guessInputFieldEmpty) {
            previousGuesses.append(guess).append("\t\t").append(hintString).append("\n");
        }
        Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
    }

    private void resetPuzzle(){
        wordGuesser.createNewTemplateWord();
        previousGuesses.setLength(0);
    }
}
