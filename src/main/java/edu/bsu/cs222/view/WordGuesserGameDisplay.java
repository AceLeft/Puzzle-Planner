package edu.bsu.cs222.view;

import edu.bsu.cs222.model.TaskInventory;
import edu.bsu.cs222.model.WordGuesser;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class WordGuesserGameDisplay {
    private final TextField guessInputField = new TextField();
    private final Button guessButton = new Button("Guess!");
    private final Label guessesLabel = new Label();
    private final Label instructionsLabel = new Label("");
    private final WordGuesser wordGuesser = new WordGuesser();
    private final StringBuilder previousGuesses = new StringBuilder();
    private final TaskInventory taskInventory;
    private final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    private final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();

    public WordGuesserGameDisplay(TaskInventory taskInventory) throws IOException {
        this.taskInventory = taskInventory;
    }

    public VBox makeWordGuesserVBox() {
        guessButton.setOnAction((event) -> {
            String key = wordGuesser.doLettersMatch(guessInputField.getText());
            if (guessInputField.getText().length() < 7 && !guessInputField.getText().equals("")) {
                previousGuesses.append(guessInputField.getText()).append("\t").append(key).append("\n");
            }
            Platform.runLater(() -> guessesLabel.setText(previousGuesses.toString()));
            if (wordGuesser.isTemplate(guessInputField.getText())) {
                try {
                    createPuzzleDonePopUp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        instructionsLabel.setText("""
                Guess the 6 letter word that I'm thinking of.
                 - means the letter is not in my word
                 * means the letter is in the word, but not in that spot.\s""");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                instructionsLabel,
                guessInputField,
                guessButton,
                guessesLabel
        );
        return vbox;
    }

    public void createPuzzleDonePopUp() throws IOException {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Label taskExitLabel = new Label("Close the window to play again");
        Label dialogLabel = new Label("Please do the task: " + taskInventory.getRandom());
        dialogLabel.setFont(Font.font("Helvetica", 70));
        taskExitLabel.setFont(Font.font(30));
        VBox dialogBox = new VBox();
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
