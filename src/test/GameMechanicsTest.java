package test;

import main.Card;
import main.GameMechanics;
import main.House;
import main.Rank;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for GameMechanics Class
 */
public class GameMechanicsTest {

    private Card testCardAce  = new Card(House.CLUBS, Rank.ACE);
    private Card testCardTen  = new Card(House.CLUBS, Rank.TEN);
    private Card testCardKing = new Card(House.CLUBS, Rank.KING);
    private Card testCardQueen = new Card(House.CLUBS, Rank.QUEEN);
    private Card testCardJack = new Card(House.CLUBS, Rank.JACK);

    /**
     * Unit test for isFaceCard
     *
     * Test 1, returns true when the card param is a face Card.
     * Test 2, returns false when the card param is a not a face Card.
     */
    @Test
    public void isFaceCard() {
        //Test 1, returns true when the card param is a face Card.
        Assert.assertTrue(GameMechanics.isFaceCard(testCardKing));

        //Test 2, returns false when the card param is a not a face Card.
        Assert.assertFalse(GameMechanics.isFaceCard(testCardAce));
    }

    /**
     * Unit Test for isFacePairs
     *
     * Test 1, returns true when the cards supplied are face pairs eg King, Queen and Jack.
     * Test 2, returns false when the cards supplied are not face pairs.
     * Test 3, returns false when the cards supplied are not face pairs and one is null.
     */
    @Test
    public void isFacePairs() {
        //Test 1, returns true when the cards supplied are face pairs eg King, Queen and Jack.
        Assert.assertTrue(GameMechanics.isFacePairs(testCardKing, testCardJack, testCardQueen));

        //Test 2, returns false when the cards supplied are not face pairs.
        Assert.assertFalse(GameMechanics.isFacePairs(testCardAce, testCardJack, testCardQueen));

        //Test 3, returns false when the cards supplied are not face pairs and one is null.
        Assert.assertFalse(GameMechanics.isFacePairs(testCardAce, testCardJack, null));
    }

    /**
     * Unit tests for isElevensPair
     *
     * Test 1, returns true when isElevensPair() input params are an elevens pair
     * Test 2, returns false when isElevensPair() input params are not an elevens pair
     * Test 3, returns false when isElevensPair() input params are not an elevens pair and 1 is null
     */
    @Test
    public void isElevensPair() {
        //Test 1, returns true when isElevensPair() input params are an elevens pair
        Assert.assertTrue(GameMechanics.isElevensPair(testCardAce, testCardTen));

        //Test 2, returns false when isElevensPair() input params are not an elevens pair
        Assert.assertFalse(GameMechanics.isElevensPair(testCardAce, testCardJack));

        //Test 3, returns false when isElevensPair() input params are not an elevens pair and 1 is null
        Assert.assertFalse(GameMechanics.isElevensPair(testCardAce, null));
    }

    /**
     * Units tests for cardSelectionCharToInt
     *
     * Test 1, GameMechanics.cardSelectionCharToInt() returns 0 when input is 'a'
     * Test 2, GameMechanics.cardSelectionCharToInt() returns 1 when input is 'b'
     * Test 3, GameMechanics.cardSelectionCharToInt() returns 2 when input is 'c'
     * Test 4, GameMechanics.cardSelectionCharToInt() returns 3 when input is 'd'
     * Test 5, GameMechanics.cardSelectionCharToInt() returns 4 when input is 'e'
     * Test 6, GameMechanics.cardSelectionCharToInt() returns 5 when input is 'f'
     * Test 7, GameMechanics.cardSelectionCharToInt() returns 6 when input is 'g'
     * Test 8, GameMechanics.cardSelectionCharToInt() returns 7 when input is 'h'
     * Test 9, GameMechanics.cardSelectionCharToInt() returns 8 when input is 'i'
     * Test 10, GameMechanics.cardSelectionCharToInt() returns -1 when input is 'z'
     */
    @Test
    public void cardSelectionCharToInt() {
        //Test 1, GameMechanics.cardSelectionCharToInt() returns 0 when input is 'a'
        Assert.assertEquals(0, GameMechanics.cardSelectionCharToInt('a'));

        //Test 2, GameMechanics.cardSelectionCharToInt() returns 1 when input is 'b'
        Assert.assertEquals(1, GameMechanics.cardSelectionCharToInt('b'));

        //Test 3, GameMechanics.cardSelectionCharToInt() returns 2 when input is 'c'
        Assert.assertEquals(2, GameMechanics.cardSelectionCharToInt('c'));

        //Test 4, GameMechanics.cardSelectionCharToInt() returns 3 when input is 'd'
        Assert.assertEquals(3, GameMechanics.cardSelectionCharToInt('d'));

        //Test 5, GameMechanics.cardSelectionCharToInt() returns 4 when input is 'e'
        Assert.assertEquals(4, GameMechanics.cardSelectionCharToInt('e'));

        //Test 6, GameMechanics.cardSelectionCharToInt() returns 5 when input is 'f'
        Assert.assertEquals(5, GameMechanics.cardSelectionCharToInt('f'));

        //Test 7, GameMechanics.cardSelectionCharToInt() returns 6 when input is 'g'
        Assert.assertEquals(6, GameMechanics.cardSelectionCharToInt('g'));

        //Test 8, GameMechanics.cardSelectionCharToInt() returns 7 when input is 'h'
        Assert.assertEquals(7, GameMechanics.cardSelectionCharToInt('h'));

        //Test 9, GameMechanics.cardSelectionCharToInt() returns 8 when input is 'i'
        Assert.assertEquals(8, GameMechanics.cardSelectionCharToInt('i'));

        //Test 10, GameMechanics.cardSelectionCharToInt() returns -1 when input is 'z'
        Assert.assertEquals(-1, GameMechanics.cardSelectionCharToInt('z'));
    }


    /**
     * Unit tests for cardSelectionNumberToString
     *
     * Test 1, GameMechanics.cardSelectionCharToInt() returns 'a' when input is 0
     * Test 2, GameMechanics.cardSelectionCharToInt() returns 'b' when input is 1
     * Test 3, GameMechanics.cardSelectionCharToInt() returns 'c' when input is 2
     * Test 4, GameMechanics.cardSelectionCharToInt() returns 'd' when input is 3
     * Test 5, GameMechanics.cardSelectionCharToInt() returns 'e' when input is 4
     * Test 6, GameMechanics.cardSelectionCharToInt() returns 'f' when input is 5
     * Test 7, GameMechanics.cardSelectionCharToInt() returns 'g' when input is 6
     * Test 8, GameMechanics.cardSelectionCharToInt() returns 'h' when input is 7
     * Test 9, GameMechanics.cardSelectionCharToInt() returns 'i' when input is 8
     * Test 10, GameMechanics.cardSelectionCharToInt() returns -'z' when input is -1
     */
    @Test
    public void cardSelectionNumberToString() {
        //Test 1, GameMechanics.cardSelectionNumberToString() returns a when input is 0
        Assert.assertEquals("a", GameMechanics.cardSelectionNumberToString(0));

        //Test 2, GameMechanics.cardSelectionNumberToString() returns b when input is 1
        Assert.assertEquals("b", GameMechanics.cardSelectionNumberToString(1));

        //Test 3, GameMechanics.cardSelectionNumberToString() returns c when input is 2
        Assert.assertEquals("c", GameMechanics.cardSelectionNumberToString(2));

        //Test 4, GameMechanics.cardSelectionNumberToString() returns d when input is 3
        Assert.assertEquals("d", GameMechanics.cardSelectionNumberToString(3));

        //Test 5, GameMechanics.cardSelectionNumberToString() returns e when input is 4
        Assert.assertEquals("e", GameMechanics.cardSelectionNumberToString(4));

        //Test 6, GameMechanics.cardSelectionNumberToString() returns f when input is 5
        Assert.assertEquals("f", GameMechanics.cardSelectionNumberToString(5));

        //Test 7, GameMechanics.cardSelectionNumberToString() returns g when input is 6
        Assert.assertEquals("g", GameMechanics.cardSelectionNumberToString(6));

        //Test 8, GameMechanics.cardSelectionNumberToString() returns h when input is 7
        Assert.assertEquals("h", GameMechanics.cardSelectionNumberToString(7));

        //Test 9, GameMechanics.cardSelectionNumberToString() returns i when input is 8
        Assert.assertEquals("i", GameMechanics.cardSelectionNumberToString(8));

        //Test 10, GameMechanics.cardSelectionNumberToString() returns ERROR when input is 99
        Assert.assertEquals("ERROR", GameMechanics.cardSelectionNumberToString(99));
    }

    /**
     * Unit tests for validStringSelection
     *
     * Test 1, returns true when 3 valid chars are selected
     * Test 2, returns true when 2 valid chars are selected
     * Test 3, returns false when 3 invalid chars are selected
     * Test 4, returns false when 2 invalid chars are selected
     * Test 5, returns false when 2 valid and 1 invalid chars are selected
     * Test 6, returns false when 1 valid and 1 invalid chars are selected
     * Test 7, returns false when 1 letter is provided
     * Test 8, returns false when 0 letters is provided
     */
    @Test
    public void validStringSelection() {
        // Test 1, returns true when 3 valid chars are selected
        Assert.assertTrue(GameMechanics.validStringSelection("abc"));

        // Test 2, returns true when 2 valid chars are selected
        Assert.assertTrue(GameMechanics.validStringSelection("de"));

        // Test 3, returns false when 3 invalid chars are selected
        Assert.assertFalse(GameMechanics.validStringSelection("xyz"));

        // Test 4, returns false when 2 invalid chars are selected
        Assert.assertFalse(GameMechanics.validStringSelection("pq"));

        // Test 5, returns false when 2 valid and 1 invalid chars are selected
        Assert.assertFalse(GameMechanics.validStringSelection("abz"));

        // Test 6, returns false when 1 valid and 1 invalid chars are selected
        Assert.assertFalse(GameMechanics.validStringSelection("az"));

        // Test 7, returns false when 1 letter is provided
        Assert.assertFalse(GameMechanics.validStringSelection("a"));

        // Test 8, returns false when 0 letters is provided
        Assert.assertFalse(GameMechanics.validStringSelection("a"));
    }

    /**
     * Unit tests for allowedCharacter
     *
     * Test 1, returns true when input is a
     * Test 2, returns true when input is b
     * Test 3, returns true when input is c
     * Test 4, returns true when input is d
     * Test 5, returns true when input is e
     * Test 6, returns true when input is f
     * Test 7, returns true when input is g
     * Test 8, returns true when input is h
     * Test 9, returns true when input is i
     * Test 10, returns false when not one of (a b c d e f g h i)
     */
    @Test
    public void allowedCharacter() {
        // Test 1, returns true when input is a
        Assert.assertTrue(GameMechanics.allowedCharacter('a'));

        // Test 2, returns true when input is b
        Assert.assertTrue(GameMechanics.allowedCharacter('b'));

        // Test 3, returns true when input is c
        Assert.assertTrue(GameMechanics.allowedCharacter('c'));

        // Test 4, returns true when input is d
        Assert.assertTrue(GameMechanics.allowedCharacter('d'));

        // Test 5, returns true when input is e
        Assert.assertTrue(GameMechanics.allowedCharacter('e'));

        // Test 6, returns true when input is f
        Assert.assertTrue(GameMechanics.allowedCharacter('f'));

        // Test 7, returns true when input is g
        Assert.assertTrue(GameMechanics.allowedCharacter('g'));

        // Test 8, returns true when input is h
        Assert.assertTrue(GameMechanics.allowedCharacter('h'));

        // Test 9, returns true when input is i
        Assert.assertTrue(GameMechanics.allowedCharacter('i'));

        // Test 10, returns false when not one of (a b c d e f g h i)
        Assert.assertFalse(GameMechanics.allowedCharacter('z'));
    }
}