package com.poker;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private int playerScore = 0;
    private int computerScore = 0;
    private boolean darkMode = false;

    @FXML private HBox playerCardsBox;
    @FXML private HBox computerCardsBox;
    @FXML private Label resultLabel;
    @FXML private Label scoreLabel;
    @FXML private Label historyLabel;

    // DEAL CARDS
    @FXML
    public void dealCards() {

        playerCardsBox.getChildren().clear();
        computerCardsBox.getChildren().clear();

        Deck deck = new Deck();

        List<Card> playerHand = new ArrayList<>();
        List<Card> computerHand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            playerHand.add(deck.dealCard());
            computerHand.add(deck.dealCard());
        }

        for (Card c : playerHand) {
            playerCardsBox.getChildren().add(createCardLabel(c));
        }

        for (Card c : computerHand) {
            computerCardsBox.getChildren().add(createCardLabel(c));
        }

        String playerResult = Evaluator.evaluate(playerHand);
        String computerResult = Evaluator.evaluate(computerHand);

        String winner = decideWinner(playerResult, computerResult);

        //  SCORE UPDATE
        if (winner.equals("Player Wins!")) playerScore++;
        else if (winner.equals("Computer Wins!")) computerScore++;

        scoreLabel.setText("Player: " + playerScore + " | Computer: " + computerScore);

        resultLabel.setText(
                "Player: " + playerResult +
                " | Computer: " + computerResult +
                " | " + winner
        );

        // SAFE HISTORY
        String newEntry =
                "Player: " + playerResult +
                " vs Computer: " + computerResult +
                " → " + winner;

        if (historyLabel.getText() == null ||
                historyLabel.getText().equals("Game History:") ||
                historyLabel.getText().equals("Game History:\n") ||
                historyLabel.getText().trim().isEmpty()) {

            historyLabel.setText("Game History:\n" + newEntry);

        } else {
            historyLabel.setText(historyLabel.getText() + "\n" + newEntry);
        }
    }

    //  WINNER LOGIC
    private String decideWinner(String p, String c) {

        int pScore = score(p);
        int cScore = score(c);

        if (pScore > cScore) return "Player Wins!";
        if (cScore > pScore) return "Computer Wins!";
        return "Draw!";
    }

    // RANKING SYSTEM
    private int score(String hand) {

        switch (hand) {
            case "Four of a Kind": return 7;
            case "Full House": return 6;
            case "Flush": return 5;
            case "Straight": return 4;
            case "Three of a Kind": return 3;
            case "Two Pair": return 2;
            case "Pair": return 1;
            default: return 0;
        }
    }

    //  UI
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

    // RESTART GAME 
    @FXML
    public void restartGame() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("New Game");
        alert.setHeaderText("Game has been reset!");
        alert.setContentText("Scores and history cleared.");

        alert.showAndWait();

        playerScore = 0;
        computerScore = 0;

        playerCardsBox.getChildren().clear();
        computerCardsBox.getChildren().clear();

        resultLabel.setText("Game Restarted!");
        scoreLabel.setText("Player: 0 | Computer: 0");
        historyLabel.setText("Game History:");
    }

    // EXIT 
    @FXML
    public void exitGame() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Game");
        alert.setHeaderText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    // RULES 
    @FXML
    public void showRules() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Poker Rules");
        alert.setHeaderText("How to Play");

        alert.setContentText(
                "Poker Ranking:\n" +
                "Pair < Two Pair < Three of a Kind < Straight < Flush < Full House < Four of a Kind\n\n" +
                "Highest hand wins the round."
        );

        alert.showAndWait();
    }
    @FXML
private BorderPane rootPane; // IMPORTANT (we need root access)

@FXML
public void toggleTheme() {

    if (!darkMode) {

        // DARK MODE
        rootPane.setStyle("-fx-background-color: #121212;");

        playerCardsBox.setStyle("-fx-background-color: transparent;");
        computerCardsBox.setStyle("-fx-background-color: transparent;");

        resultLabel.setStyle("-fx-text-fill: gold;");
        scoreLabel.setStyle("-fx-text-fill: white;");
        historyLabel.setStyle("-fx-text-fill: white;");

        darkMode = true;

    } else {

        // LIGHT MODE
        rootPane.setStyle("-fx-background-color: white;");

        playerCardsBox.setStyle("");
        computerCardsBox.setStyle("");

        resultLabel.setStyle("-fx-text-fill: black;");
        scoreLabel.setStyle("-fx-text-fill: black;");
        historyLabel.setStyle("-fx-text-fill: black;");

        darkMode = false;
    }
}

    //  ABOUT 
    @FXML
    public void showAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Poker Game");
        alert.setContentText("JavaFX Poker Project\nCreated by Student 🎓");

        alert.showAndWait();
    }
}