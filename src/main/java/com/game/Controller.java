package com.poker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

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

        Deck deck = new Deck();

        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> computerHand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.dealCard());
            computerHand.add(deck.dealCard());
        }

        int playerScore = 0;
        int computerScore = 0;

        for (Card card : playerHand) {

            Label label = createCardLabel(card);

            playerCardsBox.getChildren().add(label);

            playerScore += card.getValue();
        }

        for (Card card : computerHand) {

            Label label = createCardLabel(card);

            computerCardsBox.getChildren().add(label);

            computerScore += card.getValue();
        }

        if (playerScore > computerScore) {
            resultLabel.setText("Player Wins!");
        }
        else if (computerScore > playerScore) {
            resultLabel.setText("Computer Wins!");
        }
        else {
            resultLabel.setText("Draw!");
        }
    }

    private Label createCardLabel(Card card) {

        Label label = new Label(card.toString());

        label.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-padding: 15;" +
                "-fx-background-color: white;"
        );

        return label;
    }
}