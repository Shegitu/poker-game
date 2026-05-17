package com.poker;

import java.util.*;

public class Evaluator {

    public static String evaluate(List<Card> hand) {

        Map<String, Integer> rankCount = new HashMap<>();
        Map<String, Integer> suitCount = new HashMap<>();

        List<Integer> values = new ArrayList<>();

        for (Card c : hand) {

            rankCount.put(c.getRank(),
                    rankCount.getOrDefault(c.getRank(), 0) + 1);

            suitCount.put(c.getSuit(),
                    suitCount.getOrDefault(c.getSuit(), 0) + 1);

            values.add(c.getValue());
        }

        Collections.sort(values);

        boolean flush = suitCount.containsValue(5);
        boolean straight = isStraight(values);

        int maxSame = getMaxCount(rankCount);

        // 🔥 Poker rules (priority order)

        if (maxSame == 4) return "Four of a Kind";

        if (maxSame == 3 && rankCount.containsValue(2))
            return "Full House";

        if (flush) return "Flush";

        if (straight) return "Straight";

        if (maxSame == 3) return "Three of a Kind";

        if (maxSame == 2 && isTwoPair(rankCount))
            return "Two Pair";

        if (maxSame == 2) return "Pair";

        return "High Card";
    }

    private static int getMaxCount(Map<String, Integer> map) {

        int max = 0;

        for (int v : map.values()) {
            max = Math.max(max, v);
        }

        return max;
    }

    private static boolean isTwoPair(Map<String, Integer> map) {

        int pairs = 0;

        for (int v : map.values()) {
            if (v == 2) pairs++;
        }

        return pairs == 2;
    }

    private static boolean isStraight(List<Integer> values) {

        Set<Integer> set = new HashSet<>(values);

        if (set.size() != 5) return false;

        int min = Collections.min(values);

        for (int i = 0; i < 5; i++) {
            if (!set.contains(min + i)) {
                return false;
            }
        }

        return true;
    }
}