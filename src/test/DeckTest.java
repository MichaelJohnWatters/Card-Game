package test;

import junit.framework.TestCase;
import main.Card;
import main.Deck;
import main.House;
import main.Rank;
import org.junit.Test;

/**
 * Units test for the class Deck
 */
public class DeckTest extends TestCase {

    //A valid array of cards
    private Card[] validDeckOfCardsArray = {
            new Card(House.SPADES, Rank.KING),   new Card(House.SPADES, Rank.QUEEN),   new Card(House.SPADES, Rank.JACK),     new Card(House.SPADES, Rank.TEN),
            new Card(House.SPADES, Rank.NINE),   new Card(House.SPADES, Rank.EIGHT),   new Card(House.SPADES, Rank.SEVEN),    new Card(House.SPADES, Rank.SIX),
            new Card(House.SPADES, Rank.FIVE),   new Card(House.SPADES, Rank.FOUR),    new Card(House.SPADES, Rank.THREE),    new Card(House.SPADES, Rank.TWO),
            new Card(House.SPADES, Rank.ACE),    new Card(House.DIAMONDS, Rank.KING),  new Card(House.DIAMONDS, Rank.QUEEN),  new Card(House.DIAMONDS, Rank.JACK),
            new Card(House.DIAMONDS, Rank.TEN),  new Card(House.DIAMONDS, Rank.NINE),  new Card(House.DIAMONDS, Rank.EIGHT),  new Card(House.DIAMONDS, Rank.SEVEN),
            new Card(House.DIAMONDS, Rank.SIX),  new Card(House.DIAMONDS, Rank.FIVE),  new Card(House.DIAMONDS, Rank.FOUR),   new Card(House.DIAMONDS, Rank.THREE),
            new Card(House.DIAMONDS, Rank.TWO),  new Card(House.DIAMONDS, Rank.ACE),   new Card(House.CLUBS, Rank.KING),      new Card(House.CLUBS, Rank.QUEEN),
            new Card(House.CLUBS, Rank.JACK),    new Card(House.CLUBS, Rank.TEN),      new Card(House.CLUBS, Rank.NINE),      new Card(House.CLUBS, Rank.EIGHT),
            new Card(House.CLUBS, Rank.SEVEN),   new Card(House.CLUBS, Rank.SIX),      new Card(House.CLUBS, Rank.FIVE),      new Card(House.CLUBS, Rank.FOUR),
            new Card(House.CLUBS, Rank.THREE),   new Card(House.CLUBS, Rank.TWO),      new Card(House.CLUBS, Rank.ACE),       new Card(House.HEARTS, Rank.KING),
            new Card(House.HEARTS, Rank.QUEEN),  new Card(House.HEARTS, Rank.JACK),    new Card(House.HEARTS, Rank.TEN),      new Card(House.HEARTS, Rank.NINE),
            new Card(House.HEARTS, Rank.EIGHT),  new Card(House.HEARTS, Rank.SEVEN),   new Card(House.HEARTS, Rank.SIX),      new Card(House.HEARTS, Rank.FIVE),
            new Card(House.HEARTS, Rank.FOUR),   new Card(House.HEARTS, Rank.THREE),   new Card(House.HEARTS, Rank.TWO),      new Card(House.HEARTS, Rank.ACE)
    };

    /**
     * Utility method used in this test file to check if a card is within a Card Array, as we cant use Java.Arrays Lib.
     * @param card card to search for.
     * @return true if the input card can be found in the valid array of card
     */
    private boolean validArrayOfCardsContains(Card card) {
        boolean found = false;
        for (int i = 0; i < validDeckOfCardsArray.length; i++) {
            if (card.toString().equals(validDeckOfCardsArray[i].toString())){
                found = true;
                break;
            }
        }
        return found;
    }


    /**
     * Test that create fullDeckOfCards creates a valid deck of cards.\
     */
    @Test
    public void testCreateFullDeckOfCards() {
        //Setup test
        Deck actualDeck = new Deck();
        Deck expectedDeck = new Deck();

        //Actual
        actualDeck.createFullDeckOfCards();

        //for each house, loop through each rank and push that card to the stack.
        for (Card card: validDeckOfCardsArray) {
            expectedDeck.push(card);
        }

        Card[] actualAsArray = actualDeck.toArray();
        Card[] expectedAsArray = expectedDeck.toArray();

        // We had to do this as we could not use the library Arrays.
        // this was the only successful solution we could find without using illegal libraries, as we are not allowed to use an java collections.
        boolean testResult = true;
        for (int i = 0; i < actualAsArray.length; i++) {
           if(actualAsArray[i].getHouse() != expectedAsArray[i].getHouse() && actualAsArray[i].getRank() != expectedAsArray[i].getRank()) {
               testResult = false;
           }
        }

        //Assert
        assertTrue(testResult);
    }

    /**
     * 1. Test push() can push a card to an empty deck.
     * 2. Test push() can push a card to a stack with a top Card already existing.
     */
    @Test
    public void testPush() {
        //Setup test
        Deck testDeck = new Deck();

        testDeck.push(new Card(House.SPADES, Rank.ACE));

        //Assertions
        //1. Test push() can push a card to an empty deck..
        assertEquals("Ace of Spades", testDeck.peek().toString());

        testDeck.push(new Card(House.SPADES, Rank.TWO));

        //2. Test push() can push a card to a stack with a top Card already existing.
        assertEquals("2 of Spades", testDeck.peek().toString());

    }

    /**
     * 1.Test that pop() removes a card and returns it from the top of the deck.
     * 2.Test that pop() returns null when there is no top card.
     */
    @Test
    public void testPop() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        var actual = testDeck.pop();
        var expected = new Card(House.SPADES, Rank.ACE);

        //1.Test that pop() removes a card and returns it from the top of the deck.
        assertEquals(actual.toString(), expected.toString());

        //2.Test that pop() returns null when there is no top card.
        assertNull(testDeck.peek());
    }

    /**
     * Test to peek the top card of a deck, should return the top card of the deck, and not remove it
     */
    @Test
    public void testPeek() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        var testPeek = testDeck.peek();

        //Assert peek top card
        assertEquals("Ace of Spades", testPeek.toString());

        //Second Assert to make sure the last peek did not remove the Card.
        assertEquals("Ace of Spades", testPeek.toString());
    }

    /**
     * Test to check if isEmpty returns true when a deck is empty
     */
    @Test
    public void testIsEmpty() {
        //Setup test
        Deck testDeck = new Deck();

        //Assert
        assertTrue(testDeck.isEmpty());
    }

    /**
     * Make sure .clear() clears the deck
     */
    @Test
    public void testClear() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        testDeck.clear();

        //Assert
        assertNull(testDeck.peek());
    }

    /**
     * Test rigorousShuffle() to make sure the deck is not altered, eg a card is remove and the deck is no longer
     * a valid 52 card deck.
     */
    @Test
    public void testRigorousShuffle() {
        Deck actualDeck = new Deck();
        actualDeck.createFullDeckOfCards();

        //shuffle
        actualDeck.rigorousShuffle();

        //make sure shuffle does alter the deck.
        Card[] actualAsArray = actualDeck.toArray();

        // We had to do this as we could not use the library Arrays.
        boolean testResult = true;
        for (int i = 0; i < actualAsArray.length; i++) {
            if(!validArrayOfCardsContains(actualAsArray[i])) {
                //set to false if we cant find the card.
                testResult = false;
            }
        }

        //Assert
        assertTrue(testResult);
    }
}