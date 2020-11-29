package test;

import junit.framework.TestCase;
import main.Rank;

public class RankTest extends TestCase {

    private Rank testRankKing = Rank.KING;
    private Rank testRankAce = Rank.ACE;
    private Rank testRankTwo = Rank.TWO;

    public void testGetRank() {
        assertEquals(testRankKing.getRank(),"King");
        assertEquals(testRankAce.getRank(),"Ace");
        assertEquals(testRankTwo.getRank(),"Two");
    }

    public void testGetValue() {
        assertEquals(testRankKing.getValue(),-1);
        assertEquals(testRankAce.getValue(),1);
        assertEquals(testRankTwo.getValue(),2);
    }

    public void testTestToString() {
        assertEquals(testRankKing.toString(), "King");
        assertEquals(testRankAce.toString(), "Ace");
        assertEquals(testRankTwo.toString(), "Two");
    }
}