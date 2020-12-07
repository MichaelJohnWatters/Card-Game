package test;

import junit.framework.TestCase;
import main.Card;
import main.House;
import main.Rank;

/**
 * Unit tests for Card Class
 */
public class CardTest extends TestCase {
    private Card test1Card = new Card(House.CLUBS, Rank.ACE);
    private Card test2Card = new Card(House.DIAMONDS, Rank.TWO);
    private Card test3Card = new Card(House.DIAMONDS, Rank.KING);

    /**
     * Test ToString
     */
    public void testToString() {

        /*
          new Card(House.CLUBS, Rank.ACE).ToString() should equal "Ace of Clubs"
         */
        assertEquals(test1Card.toString(), "Ace of Clubs");

        /*
          new Card(House.DIAMONDS, Rank.TWO).ToString() should equal "2 of Diamonds"
         */
        assertEquals(test2Card.toString(), "2 of Diamonds");
    }

    /**
     * Test getHouse()
     */
    public void testGetHouse() {

        /*
            test that a new Card(House.CLUBS, Rank.ACE).getHouse() should equal House.CLUBS
         */
        assertEquals(test1Card.getHouse(), House.CLUBS);

        /*
            test that a new Card(House.DIAMONDS, Rank.TWO).getHouse() should equal House.DIAMONDS
         */
        assertEquals(test2Card.getHouse(), House.DIAMONDS);
    }

    /**
     * Test getRank()
     */
    public void testGetRank() {
         /*
            test that a new Card(House.CLUBS, Rank.ACE).getRank() should equal ,Rank.ACE
         */
        assertEquals(test1Card.getRank(), Rank.ACE);
         /*
            test that a new Card(House.DIAMONDS, Rank.TWO).getRank() should equal Rank.TWO
         */
        assertEquals(test2Card.getRank(), Rank.TWO);

         /*
            test that a new Card(House.DIAMONDS, Rank.KING).getRank() should equal Rank.KING
         */
        assertEquals(test3Card.getRank(), Rank.KING);
    }

    /**
     * Test extractRankAsDigit() returns the correct Digit, Face cards and A should return a letter and
     * all other cards should return a number
     */
    public void testExtractRankAsDigit() {
        assertEquals(Card.extractRankAsDigit(test1Card), "A"); // ace card
        assertEquals(Card.extractRankAsDigit(test2Card), "2"); // number card
        assertEquals(Card.extractRankAsDigit(test3Card), "K"); // face card
    }

    /**
     * Test extractHouseAsDigit() returns the correct colored Digit
     */
    public void testExtractHouseAsDigit() {
        String COLOR_RED = "\u001B[31m";
        String COLOR_GREEN = "\u001B[32m";
        String COLOR_WHITE = "\u001B[0m";
        assertEquals(Card.extractHouseAsDigitWithColor(test1Card).toString(), COLOR_GREEN + "C" + COLOR_WHITE);
        assertEquals(Card.extractHouseAsDigitWithColor(test2Card).toString(), COLOR_RED + "D" + COLOR_WHITE);
    }
}