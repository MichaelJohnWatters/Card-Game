package test;

import junit.framework.TestCase;
import main.Rank;

/**
 * Unit tests for the class Rank
 */
public class RankTest extends TestCase {

    private Rank testRankKing = Rank.KING;
    private Rank testRankAce = Rank.ACE;
    private Rank testRankTwo = Rank.TWO;

    /**
     * Test getRank() returns the correct Rank of the Card
     */
    public void testGetRank() {
        //a card with Rank.KING .getRank() should equal "King"
        assertEquals(testRankKing.getRank(), "King");

        //a card with Rank.ACE .getRank() should equal "Ace"
        assertEquals(testRankAce.getRank(), "Ace");

        //a card with Rank.TWO .getRank() should equal "Two"
        assertEquals(testRankTwo.getRank(), "Two");
    }

    /**
     * Test getValue() returns the correct value of the card
     */
    public void testGetValue() {
        //a card with Rank.KING .getRank() should equal -1
        assertEquals(testRankKing.getValue(), -1);

        //a card with Rank.Ace .getRank() should equal 1
        assertEquals(testRankAce.getValue(), 1);

        //a card with Rank.Two .getRank() should equal 2
        assertEquals(testRankTwo.getValue(), 2);
    }

    /**
     * Test toString returns the Rank as a String
     */
    public void testTestToString() {
        //a card with Rank.KING .toString() should equal "King"
        assertEquals(testRankKing.toString(), "King");

        //a card with Rank.ACE .toString() should equal "Ace"
        assertEquals(testRankAce.toString(), "Ace");

        //a card with Rank.TWO .toString() should equal "Two"
        assertEquals(testRankTwo.toString(), "Two");
    }
}