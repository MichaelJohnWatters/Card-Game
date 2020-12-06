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
    private Card test2Card = new Card(House.DIAMONDS,Rank.TWO);

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
            a new Card(House.CLUBS, Rank.ACE).getHouse() should equal House.CLUBS
         */
        assertEquals(test1Card.getHouse(), House.CLUBS);

        /*
            a new Card(House.DIAMONDS, Rank.TWO).getHouse() should equal House.DIAMONDS
         */
        assertEquals(test2Card.getHouse(), House.DIAMONDS);
    }

    /**
     * Test getRank()
     */
    public void testGetRank() {
        assertEquals(test1Card.getRank(),Rank.ACE);
        assertEquals(test2Card.getRank(),Rank.TWO);
    }

    /**
     * Test extractRankAsDigit()
     */
    public void testExtractRankAsDigit() {
        assertEquals(Card.extractRankAsDigit(test1Card), "A");
        assertEquals(Card.extractRankAsDigit(test2Card), "2");
    }

    /**
     * Test extractHouseAsDigit()
     */
    public void testExtractHouseAsDigit() {
        String COLOR_RED = "\u001B[31m";
        String COLOR_GREEN = "\u001B[32m";
        String COLOR_WHITE = "\u001B[0m";
        assertEquals(Card.extractHouseAsDigitWithColor(test1Card).toString(), COLOR_GREEN + "C" + COLOR_WHITE);
        assertEquals(Card.extractHouseAsDigitWithColor(test2Card).toString(),COLOR_RED + "D"+ COLOR_WHITE);
    }
}