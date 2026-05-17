package com.poker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

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

        List<Card> playerHand = new ArrayList<>();
        List<Card> computerHand = new ArrayList<>();

        // deal 5 cards each
        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.dealCard());
            computerHand.add(deck.dealCard());
        }

        // show player cards
        for (Card c : playerHand) {
            playerCardsBox.getChildren().add(createCardLabel(c));
        }

        // show computer cards
        for (Card c : computerHand) {
            computerCardsBox.getChildren().add(createCardLabel(c));
        }

        // evaluate hands using Evaluator (renamed from HandEvaluator)
        String playerResult = Evaluator.evaluate(playerHand);
        String computerResult = Evaluator.evaluate(computerHand);

        // show result
        resultLabel.setText(
                "Player: " + playerResult +
                " | Computer: " + computerResult +
                " | " + decideWinner(playerResult, computerResult)
        );
    }

    // compare results
    private String decideWinner(String p, String c) {

        int pScore = score(p);
        int cScore = score(c);

        if (pScore > cScore) return "Player Wins!";
        if (cScore > pScore) return "Computer Wins!";
        return "Draw!";
    }

    // ranking system
    private int score(String hand) {

    switch (hand) {

        case "Four of a Kind":
            return 7;

        case "Full House":
            return 6;

        case "Flush":
            return 5;

        case "Straight":
            return 4;

        case "Three of a Kind":
            return 3;

        case "Two Pair":
            return 2;

        case "Pair":
            return 1;

        default:
            return 0;
    }
}
    

    // UI card display
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