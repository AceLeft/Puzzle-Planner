package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesserGame;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();
    private final Label instructionsLabel = new Label("");
    private final WordGuesserGame wordGuesser = new WordGuesserGame();
    private final StringBuilder previousGuesses = new StringBuilder();
    private final TaskInventory taskInventory;
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    private final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();

    public WordGuesserGameDisplay(TaskInventory taskInventory){
        this.taskInventory = taskInventory;
    }

    public Tab makeWordGuesserGameTab() {
        guessButton.setOnAction((event) -> {
            String guess = guessInputField.getText();
            String key = wordGuesser.makeClueFromGuess(guess);
            StringBuilder keyReformatted = new StringBuilder();
            for(char character : key.toCharArray()){
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
        return new Tab("Word Guesser",vbox);
    }

    public void createPuzzleDonePopUp() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Label taskExitLabel = new Label("Close the window when you're done.");
        Label dialogLabel = new Label(" You won! \n Complete this task to play again: \n   " + taskInventory.getRandom());
        dialogLabel.setFont(Font.font("Helvetica", 60));
        taskExitLabel.setFont(Font.font(30));
        VBox dialogBox = new VBox();
        dialogBox.setStyle("-fx-padding:5px");
        dialogBox.getChildren().addAll(
                dialogLabel,
                taskExitLabel
        );
        dialogStage.setScene(new Scene(dialogBox));
        dialogStage.setWidth(SCREEN_WIDTH);
        dialogStage.setHeight(SCREEN_HEIGHT);
        dialogStage.show();
        dialogStage.onCloseRequestProperty();
        wordGuesser.createNewTemplateWord();
        previousGuesses.setLength(0);
    }
}
