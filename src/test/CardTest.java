package test;
import junit.framework.TestCase;
import main.Card;
import main.House;
import main.Rank;

public class CardTest extends TestCase {
    private Card test1Card = new Card(House.CLUBS, Rank.ACE);
    private Card test2Card = new Card(House.DIAMONDS,Rank.TWO);

    public void testToString() {
        assertEquals(test1Card.toString(), "1 of Clubs");
        assertEquals(test2Card.toString(), "2 of Diamonds");
    }

    public void testGetHouse() {
        assertEquals(test1Card.getHouse(),House.CLUBS);
        assertEquals(test2Card.getHouse(),House.DIAMONDS);
    }

    public void testGetRank() {
        assertEquals(test1Card.getRank(),Rank.ACE);
        assertEquals(test2Card.getRank(),Rank.TWO);
    }

    public void testExtractRankAsDigit() {
        assertEquals(Card.extractRankAsDigit(test1Card), "A");
        assertEquals(Card.extractRankAsDigit(test2Card), "2");
    }

    public void testExtractHouseAsDigit() {
        assertEquals(Card.extractHouseAsDigitWithColor(test1Card),"C");
        assertEquals(Card.extractHouseAsDigitWithColor(test2Card),"D");
    }
}