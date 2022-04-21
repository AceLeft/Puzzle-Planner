package edu.bsu.cs222.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PuzzleDonePopUp {
    private Label taskExitLabel;
    private Label taskCompleteLabel;
    private VBox dialogBox;
    private Stage dialogStage;
    private final String task;

    public PuzzleDonePopUp(String task) {
        this.task = task;
        defineDialogStage();
    }

    private void defineDialogStage() {
        dialogStage = new Stage();
        defineLabels();
        defineVBox();
        styleStage();
        dialogStage.show();
    }

    private void defineLabels() {
        taskExitLabel = new Label("Close the window when you're done.");
        taskCompleteLabel = new Label("You won! Complete this task to play again: \n" + task);
        styleLabels();
    }

    private void styleLabels() {
        int taskCompleteFontSize = 40;
        int taskExitFontSize = 30;
        taskCompleteLabel.setFont(Font.font("Helvetica", taskCompleteFontSize));
        taskExitLabel.setFont(Font.font(taskExitFontSize));
    }

    private void defineVBox() {
        dialogBox = new VBox();
        dialogBox.getChildren().addAll(
                taskCompleteLabel,
                taskExitLabel
        );
        styleVBox();
    }

    private void styleVBox() {
        String popUpPadding = "5px";
        String dialogBoxStyle = "-fx-padding:" + popUpPadding;
        dialogBox.setStyle(dialogBoxStyle);
        dialogBox.autosize();
    }

    private void styleStage() {
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setScene(new Scene(dialogBox));
        dialogStage.onCloseRequestProperty();
    }
}
