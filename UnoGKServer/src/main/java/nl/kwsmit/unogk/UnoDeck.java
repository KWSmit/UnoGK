package nl.kwsmit.unogk;

import java.util.ArrayList;
import java.util.Random;

/**
 * UnoDeck
 */
public class UnoDeck {

    private static final String red0 = "R0";
    private static final String red1 = "R1";
    private static final String red2 = "R2";
    private static final String red3 = "R3";
    private static final String red4 = "R4";
    private static final String red5 = "R5";
    private static final String red6 = "R6";
    private static final String red7 = "R7";
    private static final String red8 = "R8";
    private static final String red9 = "R9";
    private static final String redSkip = "RSkip";
    private static final String redTurn = "RTurn";
    private static final String redTake2 = "RTake2";
    private static final String blue0 = "B0";
    private static final String blue1 = "B1";
    private static final String blue2 = "B2";
    private static final String blue3 = "B3";
    private static final String blue4 = "B4";
    private static final String blue5 = "B5";
    private static final String blue6 = "B6";
    private static final String blue7 = "B7";
    private static final String blue8 = "B8";
    private static final String blue9 = "B9";
    private static final String blueSkip = "BSkip";
    private static final String blueTurn = "BTurn";
    private static final String blueTake2 = "BTake2";
    private static final String green0 = "G0";
    private static final String green1 = "G1";
    private static final String green2 = "G2";
    private static final String green3 = "G3";
    private static final String green4 = "G4";
    private static final String green5 = "G5";
    private static final String green6 = "G6";
    private static final String green7 = "G7";
    private static final String green8 = "G8";
    private static final String green9 = "G9";
    private static final String greenSkip = "GSkip";
    private static final String greenTurn = "GTurn";
    private static final String greenTake2 = "GTake2";
    private static final String yellow0 = "Y0";
    private static final String yellow1 = "Y1";
    private static final String yellow2 = "Y2";
    private static final String yellow3 = "Y3";
    private static final String yellow4 = "Y4";
    private static final String yellow5 = "Y5";
    private static final String yellow6 = "Y6";
    private static final String yellow7 = "Y7";
    private static final String yellow8 = "Y8";
    private static final String yellow9 = "Y9";
    private static final String yellowSkip = "YSkip";
    private static final String yellowTurn = "YTurn";
    private static final String yellowTake2 = "YTake2";
    private static final String take4 = "Take4";
    private static final String changeColor = "ChangeColor";

    private ArrayList<String> cards;

    public UnoDeck() {
        cards = new ArrayList<>();
    }

    public int newStartDeck() {
        cards.add(red0);
        cards.add(blue0);
        cards.add(green0);
        cards.add(yellow0);
        for (int i = 0; i < 2; i++) {
            cards.add(red1);
            cards.add(red2);
            cards.add(red3);
            cards.add(red4);
            cards.add(red5);
            cards.add(red6);
            cards.add(red7);
            cards.add(red8);
            cards.add(red9);
            cards.add(redSkip);
            cards.add(redTurn);
            cards.add(redTake2);
            cards.add(blue1);
            cards.add(blue2);
            cards.add(blue3);
            cards.add(blue4);
            cards.add(blue5);
            cards.add(blue6);
            cards.add(blue7);
            cards.add(blue8);
            cards.add(blue9);
            cards.add(blueSkip);
            cards.add(blueTurn);
            cards.add(blueTake2);
            cards.add(green1);
            cards.add(green2);
            cards.add(green3);
            cards.add(green4);
            cards.add(green5);
            cards.add(green6);
            cards.add(green7);
            cards.add(green8);
            cards.add(green9);
            cards.add(greenSkip);
            cards.add(greenTurn);
            cards.add(greenTake2);
            cards.add(yellow1);
            cards.add(yellow2);
            cards.add(yellow3);
            cards.add(yellow4);
            cards.add(yellow5);
            cards.add(yellow6);
            cards.add(yellow7);
            cards.add(yellow8);
            cards.add(yellow9);
            cards.add(yellowSkip);
            cards.add(yellowTurn);
            cards.add(yellowTake2);
        }
        for (int i = 0; i < 4; i++) {
            cards.add(take4);
            cards.add(changeColor);
        }
        return cards.size();
    }

    public int getNrOfCards() {
        return cards.size();
    }

    public String getCardByIndex(int index) {
        return cards.get(index);
    }

    public void addCard(String card) {
        this.cards.add(0, card);
    }

    public void removeCard(String card) {
        this.cards.remove(card);
    }

    public void removeCardByIndex(int index) {
        this.cards.remove(index);
    }

    public void shuffle() {
        ArrayList<String> shuffledDeck = new ArrayList<>();
        Random random = new Random();
        // Create list with available indexes.
        ArrayList<Integer> availableIndexes = new ArrayList<>();
        for (int i = 0; i < this.getNrOfCards(); i++) {
            availableIndexes.add(i);
        }
        // Shuffle.
        for (int i = 0; i < this.getNrOfCards(); i++) {
            int nr = random.nextInt(availableIndexes.size());
            shuffledDeck.add(cards.get(availableIndexes.get(nr)));
            availableIndexes.remove(nr);
        }
        cards = shuffledDeck;
    }

    public void clear() {
        this.cards.clear();
    }
}
