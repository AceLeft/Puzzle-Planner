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
        String key = wordGuesser.makeClueFromGuess(guess);
        StringBuilder reformattedKey = formatKey(key);
        showGuessAndKey(guess, reformattedKey);
        if (wordGuesser.matchesTemplate(guess)) {
            createPuzzleDonePopUp();
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

    public void createPuzzleDonePopUp() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Label taskExitLabel = new Label("Close the window when you're done.");
        Label taskCompleteLabel = new Label("You won! Complete this task to play again: \n" + taskInventory.getRandom());
        int taskCompleteFontSize = 40;
        int taskExitFontSize = 30;
        taskCompleteLabel.setFont(Font.font("Helvetica", taskCompleteFontSize));
        taskExitLabel.setFont(Font.font(taskExitFontSize));
        VBox dialogBox = new VBox();
        String popUpPadding = "5px";
        String dialogBoxStyle = "-fx-padding:"+popUpPadding;
        dialogBox.setStyle(dialogBoxStyle);
        dialogBox.autosize();
        dialogBox.getChildren().addAll(
                taskCompleteLabel,
                taskExitLabel
        );
        dialogStage.setScene(new Scene(dialogBox));
        dialogStage.show();
        dialogStage.onCloseRequestProperty();
        wordGuesser.createNewTemplateWord();
        previousGuesses.setLength(0);
    }
}
