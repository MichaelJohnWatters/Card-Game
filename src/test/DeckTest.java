package test;

import junit.framework.TestCase;
import main.*;
import org.junit.Test;

public class DeckTest extends TestCase {

    @Test
    public void testCreateFullDeckOfCards() {
        //Setup test
        Deck testDeck = new Deck();
        House[] houseArray = { House.SPADES};
        Rank[] rankArray = { Rank.ACE};

        for (House house: houseArray) {
            for (Rank rank: rankArray) {
                testDeck.push(new Card(house, rank));
            }
        }

        //Assert
        assertEquals("Ace of Spades", testDeck.peek());
    }

    @Test
    public void testPush() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        //Assert
        assertEquals("Ace of Spades", testDeck.peek());

    }

    @Test
    public void testPop() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        testDeck.pop();

        //Assert
        assertEquals("null", testDeck.peek());
    }

    @Test
    public void testPeek() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        var testPeek = testDeck.peek();

        //Assert
        assertEquals("Ace of Spades", testPeek);
    }

    @Test
    public void testIsEmpty() {
        //Setup test
        Deck testDeck = new Deck();

        //Assert
        assertFalse(testDeck.isEmpty());
    }

    @Test
    public void testClear() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        testDeck.clear();

        //Assert
        assertEquals("null", testDeck.peek());
    }

    @Test
    public void testRippleShuffle() {

    }

    @Test
    public void testRandomShuffle() {

    }
}