package com.poker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML
    private HBox playerCardsBox;

    @FXML
    private HBox computerCardsBox;

    @FXML
    private Label resultLabel;

    @FXML
    public void dealCards() {

        playerCardsBox.getChildren().clear();
        computerCardsBox.getChildren().clear();

        Label playerCard = new Label("A♠");
        playerCard.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-border-color: black;" +
                "-fx-padding: 15;"
        );

        Label computerCard = new Label("K♥");
        computerCard.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-border-color: black;" +
                "-fx-padding: 15;"
        );

        playerCardsBox.getChildren().add(playerCard);
        computerCardsBox.getChildren().add(computerCard);

        resultLabel.setText("Game Started!");
    }
}