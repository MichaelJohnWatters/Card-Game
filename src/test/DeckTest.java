package test;

import junit.framework.TestCase;
import main.Card;
import main.Deck;
import main.House;
import main.Rank;
import org.junit.Test;

public class DeckTest extends TestCase {

    @Test
    public void testCreateFullDeckOfCards() {
        //Setup test
        Deck acctualDeck = new Deck();
        Deck expectedDeck = new Deck();

        //valid Pack of cards
        Card[] cards = {
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

        //Actual
        acctualDeck.createFullDeckOfCards();

        //for each house, loop through each rank and push that card to the stack.
        for (Card card: cards) {
            expectedDeck.push(card);
        }

        Card[] actualAsArray = acctualDeck.toArray();
        Card[] expectedAsArray = expectedDeck.toArray();

        // We had to do this as we could not use the library Arrays.
        // this was the only successful solution we could find without using illegal libraries, as we are not allowed to use an java collections.
        boolean testResult = true;
        for (int i = 0; i < actualAsArray.length; i++) {
           if(actualAsArray[i].getHouse() != expectedAsArray[i].getHouse() && actualAsArray[i].getRank() != expectedAsArray[i].getRank()) {
               testResult = false;
           }
        }

        assertTrue(testResult);
    }

    @Test
    public void testPush() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        //Assert
        assertEquals("Ace of Spades", testDeck.peek().toString());

    }

    @Test
    public void testPop() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        testDeck.pop();

        //Assert
        assertNull(testDeck.peek());
    }

    @Test
    public void testPeek() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        var testPeek = testDeck.peek();

        //Assert
        assertEquals("Ace of Spades", testPeek.toString());
    }

    @Test
    public void testIsEmpty() {
        //Setup test
        Deck testDeck = new Deck();

        //Assert
        assertEquals(true, testDeck.isEmpty());
    }

    @Test
    public void testClear() {
        //Setup test
        Deck testDeck = new Deck();
        testDeck.push(new Card(House.SPADES, Rank.ACE));

        testDeck.clear();

        //Assert
        assertNull(testDeck.peek());
    }

    @Test
    public void testRippleShuffle() {


    }

    @Test
    public void testRandomShuffle() {

    }
}