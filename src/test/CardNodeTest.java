package test;

import junit.framework.TestCase;
import main.Card;
import main.CardNode;
import main.House;
import main.Rank;

/**
 * Unit tests for CardNode Class
 */
public class CardNodeTest extends TestCase {

    /**
     * Test Get Data returns the correct card.
     */
    public void testGetData() {
        Card testCard = new Card(House.CLUBS, Rank.ACE);
        CardNode cardNodeTest = new CardNode(testCard);

        var expected = testCard.toString();
        var actual = cardNodeTest.getData().toString();

        assertEquals(expected, actual);
    }

    /**
     * Test Set Data, sets the data to the supplied Card.
     */
    public void testSetData() {
        Card testCard = new Card(House.CLUBS, Rank.ACE);
        CardNode cardNodeTest = new CardNode(null);

        //set the data
        cardNodeTest.setData(testCard);

        var expected = testCard.toString();
        var acctual = cardNodeTest.getData().toString();

        //Assert
        assertEquals(expected, acctual);
    }

    /**
     * Test get next Card Node returns the next CardNode
     */
    public void testGetNext() {
        Card testCard = new Card(House.CLUBS, Rank.ACE);
        Card testNextCard = new Card(House.CLUBS, Rank.TWO);
        CardNode cardNodeTest = new CardNode(testCard);

        //set next
        cardNodeTest.setNext(new CardNode(testNextCard));

        var expected = testNextCard.toString();
        var actual = cardNodeTest.getNext().getData().toString();

        //Assert
        assertEquals(expected, actual);
    }

    /**
     * test Set Next CardNode sets the correct next cardNode
     */
    public void testSetNext() {
        Card testCard = new Card(House.CLUBS, Rank.ACE);
        Card testNextCard = new Card(House.CLUBS, Rank.TWO);
        CardNode cardNodeTest = new CardNode(testCard);

        //set next
        cardNodeTest.setNext(new CardNode(testNextCard));

        var expected = testNextCard.toString();
        var actual = cardNodeTest.getNext().getData().toString();

        //Assert
        assertEquals(expected, actual);
    }
}