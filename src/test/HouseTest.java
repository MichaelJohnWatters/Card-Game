package test;

import junit.framework.TestCase;
import main.House;

public class HouseTest extends TestCase {

    public void testToString() {
        assertEquals(House.CLUBS.toString(),"Clubs");
        assertEquals(House.DIAMONDS.toString(),"Diamonds");
        assertEquals(House.SPADES.toString(),"Spades");
        assertEquals(House.HEARTS.toString(),"Hearts");
    }
}