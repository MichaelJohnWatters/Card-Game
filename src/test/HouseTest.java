package test;

import junit.framework.TestCase;
import main.House;

/**
 * Units Tests for the Class House
 */
public class HouseTest extends TestCase {

    /**
     * Test for House.toString()
     */
    public void testToString() {
        //Test House.CLUBS.toString() equals "Clubs"
        assertEquals(House.CLUBS.toString(), "Clubs");

        //Test House.DIAMONDS.toString() equals "Diamonds"
        assertEquals(House.DIAMONDS.toString(), "Diamonds");

        //Test House.SPADES.toString() equals "Spades"
        assertEquals(House.SPADES.toString(), "Spades");

        //Test House.HEARTS.toString() equals "Hearts"
        assertEquals(House.HEARTS.toString(), "Hearts");
    }
}