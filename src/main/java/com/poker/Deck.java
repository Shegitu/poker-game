package com.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {

        cards = new ArrayList<>();

        String[] suits = {"♠", "♥", "♦", "♣"};
        String[] ranks = {
                "2", "3", "4", "5", "6",
                "7", "8", "9", "10",
                "J", "Q", "K", "A"
        };

        int value = 2;

        for (String suit : suits) {

            value = 2;

            for (String rank : ranks) {

                cards.add(new Card(suit, rank, value));

                value++;
            }
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {

        if (!cards.isEmpty()) {
            return cards.remove(0);
        }

        return null;
    }
}