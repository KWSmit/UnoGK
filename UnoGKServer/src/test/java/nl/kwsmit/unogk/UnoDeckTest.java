package nl.kwsmit.unogk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UnoDeckTest {

    @Test
    public void givenNone_whenNewStartDeck_ReturnNrOfCards() {
        UnoDeck deck = new UnoDeck();
        int nrOfCards = deck.newStartDeck();
        assertEquals(nrOfCards, 108);
        assertEquals(deck.getCardByIndex(0), "R0");
        assertEquals(deck.getCardByIndex(107), "ChangeColor");
    }

    @Test
    public void givenId_whenAddCard_thenCardAdded() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test");
        assertEquals(deck.getNrOfCards(), 1);
        assertEquals(deck.getCardByIndex(0), "test");
    }

    @Test
    public void givenDeck_whenGetNrOfCards_thenReturnNrOfCards() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test1");
        deck.addCard("test2");
        deck.addCard("test3");
        deck.addCard("test4");
        assertEquals(deck.getNrOfCards(), 4);
    }

    @Test
    public void givenId_whenRemoveCardById_thenCardRemoved() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test");
        deck.removeCard("test");
        assertEquals(deck.getNrOfCards(), 0);
    }

    @Test
    public void givenId_whenRemoveCardByIndex_thenCardRemoved() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test");
        deck.removeCardByIndex(0);
        assertEquals(deck.getNrOfCards(), 0);
    }

    @Test
    public void givenDeck_whenClear_thenEmptyDeck() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test1");
        deck.addCard("test2");
        deck.addCard("test3");
        deck.addCard("test4");
        deck.clear();
        assertEquals(deck.getNrOfCards(), 0);
    }

    @Test
    public void givenDeck_whenShuffle_thenProperDeck() {
        UnoDeck deck = new UnoDeck();
        deck.addCard("test1");
        deck.addCard("test2");
        deck.addCard("test3");
        deck.addCard("test4");
        ArrayList<String> cards = new ArrayList<>();
        cards.add("test1");
        cards.add("test2");
        cards.add("test3");
        cards.add("test4");
        deck.shuffle();
        assertTrue(cards.contains(deck.getCardByIndex(0)));
        assertTrue(cards.contains(deck.getCardByIndex(1)));
        assertTrue(cards.contains(deck.getCardByIndex(2)));
        assertTrue(cards.contains(deck.getCardByIndex(3)));
    }

}
