package test;

import main.Card;
import main.CardSlotsBag;
import main.House;
import main.Rank;
import org.junit.Assert;
import org.junit.Test;

/***
 * Units test for all the methods in CardSlotsBag
 */
public class CardSlotsBagTest {

    private Card testCard1 = new Card(House.CLUBS, Rank.ACE);
    private Card testCard2 = new Card(House.DIAMONDS, Rank.TWO);
    private Card testCard3 = new Card(House.SPADES, Rank.KING);

    private Card testCardElevensPair1 = new Card(House.SPADES, Rank.ACE);
    private Card testCardElevensPair2 = new Card(House.SPADES, Rank.TEN);

    private Card testKing = new Card(House.SPADES, Rank.KING);
    private Card testQueen = new Card(House.SPADES, Rank.QUEEN);
    private Card testJack = new Card(House.SPADES, Rank.JACK);

    /**
     * Test 1 Assert containsCardValue() returns true when it finds a card with the same int value
     * Test 2 Assert containsCardValue() returns false when it does not find a card with the input in value.
     */
    @Test
    public void containsCardValue() {
        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Test 1 Assert containsCardValue() returns true when it finds a card with the same int value
        Assert.assertTrue(bag.containsCardValue(1));

        //Test 2 Assert containsCardValue() returns false when it does not find a card with the input in value.
        Assert.assertFalse(bag.containsCardValue(2));
    }

    /**
     * Test 1 Assert findsAndReturnsCardValue() finds and returns a card value when the asked for card are present
     * Test 2 Assert findsAndReturnsCardValue() returns null when the asked for cards are not in the bag
     */
    @Test
    public void findsAndReturnsCardValue() {
        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Test 1 Assert findsAndReturnsCardValue() finds and returns a card value when the asked for card are present
        Assert.assertEquals(bag.findsAndReturnsCardValue(1), testCard1);

        //Test 2 Assert findsAndReturnsCardValue() returns null when the asked for cards are not in the bag
        CardSlotsBag bag2 = new CardSlotsBag();
        Assert.assertNull(bag2.findsAndReturnsCardValue(1));
    }

    /**
     * Test 1 Assert containsKingQueenJack() should return false when there is no face cards in the bag
     * Test 2 Assert containsKingQueenJack() should return false when there is only a King and a Queen in the bag.
     * Test 3 Assert containsKingQueenJack() should return true when there is a King Queen and a Jack in the bag
     */
    @Test
    public void containsKingQueenJack() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert containsKingQueenJack() should return false when there is no face cards in the bag.
        Assert.assertFalse(bag.containsKingQueenJack());

        //Test 2 Assert containsKingQueenJack() should return false when there is only a King and a Queen in the bag
        bag.addNewEntry(testKing);
        bag.addNewEntry(testQueen);
        Assert.assertFalse(bag.containsKingQueenJack());

        //Test 3 Assert containsKingQueenJack() should return true when there is a King Queen and a Jack in the bag
        bag.addNewEntry(testJack);
        Assert.assertTrue(bag.containsKingQueenJack());
    }

    /**
     * Test 1 Assert findAndReturnKingQueenJackPair() returns null when the bag does not contain a King Queen Jack set.
     * Test 2 Assert findAndReturnKingQueenJackPair() finds and returns a card King Queen Jack set are in the bag.
     */
    @Test
    public void findAndReturnKingQueenJackPair() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert findAndReturnKingQueenJackPair() returns null when the bag does not contain a King Queen Jack set.
        Assert.assertNull(bag.findAndReturnKingQueenJackPair());

        //Test 2 Assert findAndReturnKingQueenJackPair() finds and returns a card King Queen Jack set are in the bag.
        Card[] test2Expected = {testKing,testQueen,testJack};
        bag.addNewEntry(testKing);
        bag.addNewEntry(testQueen);
        bag.addNewEntry(testJack);

        Assert.assertEquals(test2Expected, bag.findAndReturnKingQueenJackPair());
    }

    /**
     * Test 1 Assert containsElevensPair() returns null when it cant find a valid elevens pair when the bag is empty
     * Test 2 Assert containsElevensPair() returns false when the bag does not contain an  elevens pair
     * Test 3 Assert  containsElevensPair() returns true when the bag contains an elevens pair
     */
    @Test
    public void containsElevensPair() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert containsElevensPair() returns null when it cant find a valid elevens pair when the bag is empty
        Assert.assertFalse(bag.containsElevensPair());

        //Test 2Assert containsElevensPair() returns false when the bag does not contain an  elevens pair
        bag.addNewEntry(testCard2);
        bag.addNewEntry(testCard3);
        Assert.assertFalse(bag.containsElevensPair());

        //Test 3Assert  containsElevensPair() returns true when the bag contains an elevens pair
        bag.addNewEntry(testCardElevensPair1);
        bag.addNewEntry(testCardElevensPair2);
        Assert.assertTrue(bag.containsElevensPair());
    }

    /**
     * Test 1 Assert returns null when it cant find a valid elevens pair when the bag is empty
     * Test 2 Assert returns null when it cant find a valid elevens pair.
     * Test 3 Assert findAndReturnElevensPair() returns the found elevens pair cards
     */
    @Test
    public void findAndReturnElevensPair() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert returns null when it cant find a valid elevens pair when the bag is empty
        Assert.assertNull(bag.findAndReturnElevensPair());

        //Test 2 Assert returns null when it cant find a valid elevens pair.
        bag.addNewEntry(testCard2);
        bag.addNewEntry(testCard3);
        Assert.assertNull(bag.findAndReturnElevensPair());

        //Test 3 Assert findAndReturnElevensPair() returns the found elevens pair cards
        bag.addNewEntry(testCardElevensPair1);
        bag.addNewEntry(testCardElevensPair2);
        Card[] test2Expected = {testCardElevensPair1, testCardElevensPair2};
        Assert.assertEquals(bag.findAndReturnElevensPair(), test2Expected);
    }

    /**
     * Test 1 Assert that countCards() returns 0 when there is zero cards
     * Test 2 Assert that countCards() returns 2 when there is 2 cards
     * Test 3 Assert that countCards() returns 9 when the bag is full
     */
    @Test
    public void countCards() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert that countCards() returns 0 when there is zero cards
        Assert.assertEquals(bag.countCards(),0);

        //Test 2 Assert that countCards() returns 2 when there is 2 cards
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        Assert.assertEquals(bag.countCards(),2);

        //Test 3 Assert that countCards() returns 9 when the bag is full
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        Assert.assertEquals(bag.countCards(),9);
    }

    /**
     * Test 1 Assert that countEmptySlots() returns 9 when there is zero cards in the bag.
     * Test 2 Assert that countEmptySlots() returns 2 when there is 2 empty slots
     * Test 3 Assert that countEmptySlots() returns 0 when the bag is full
     */
    @Test
    public void countEmptySlots() {
        CardSlotsBag bag = new CardSlotsBag();

        //Fill up the cardSlotBag
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);

        //Test 1 Assert that countEmptySlots() returns 9 when there is zero cards in the bag.
        Assert.assertEquals(bag.countEmptySlots(),0);

        //Test 2 Assert that countEmptySlots() returns 2 when there is 2 empty slots
        bag.remove();
        bag.remove();
        Assert.assertEquals(bag.countEmptySlots(),2);

        //Test 3 Assert that countEmptySlots() returns 0 when the bag is full
        bag.remove();
        bag.remove();
        bag.remove();
        bag.remove();
        bag.remove();
        bag.remove();
        bag.remove();
        Assert.assertEquals(bag.countEmptySlots(),9);
    }

    /**
     * Test 1 Assert cardAtPosition() returns null when the slot asked for is empty.
     * Test 2 Assert cardAtPosition() returns a Card at the correct position when the slot is not empty
     */
    @Test
    public void cardAtPosition() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert cardAtPosition() returns null when the slot asked for is empty.
        Assert.assertNull(bag.cardAtPosition(0));

        //Test 2 Assert cardAtPosition() returns a Card at the correct position when the slot is not empty
        bag.addNewEntry(testCard1);
        Assert.assertEquals(bag.cardAtPosition(0),testCard1);
    }

    /**
     *  Test 1 Assert getCurrentSize() returns 0 when bag is empty
     *  Test 2 Assert getCurrentSize() returns 2 when bag has 2 cards
     *  Test 3 Assert getCurrentSize() returns 9 when the bag is full
     *  Test 4 Assert getCurrentSize() returns 8 when 1 card is removed
     */
    @Test
    public void getCurrentSize() {
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1 Assert getCurrentSize() returns 0 when bag is empty
        Assert.assertEquals(bag.getCurrentSize(),0);

        //Test 2 Assert getCurrentSize() returns 2 when bag has 2 cards
        bag.addNewEntry(testCard1);
        Assert.assertEquals(bag.getCurrentSize(),1);

        //Test 3 Assert getCurrentSize() returns 9 when the bag is full
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        Assert.assertEquals(bag.getCurrentSize(),9);

        //Test 4 Assert getCurrentSize() returns 8 when 1 card is removed
        bag.remove();
        Assert.assertEquals(bag.getCurrentSize(),8);
    }

    /**
     * Test 1 Assert Test 1 is isEmpty() returns true when it is empty
     * Test 2 Assert Test 2 isEmpty() returns false when it is not empty
     */
    @Test
    public void isEmpty() {
        CardSlotsBag bag = new CardSlotsBag();

        //Assert is isEmpty() returns true when it is empty
        Assert.assertTrue(bag.isEmpty());

        //Assert is isEmpty() returns false when it is not empty
        bag.addNewEntry(testCard1);
        Assert.assertFalse(bag.isEmpty());
    }

    /**
     * Test 1: Assert that addNewEntry() adds a new card to an empty that has card but is not full.
     * Test 2: Assert that addNewEntry() does not add a new card to a bag that is full.
     */
    @Test
    public void addNewEntry() {
        //Assert that addNewEntry() adds a new card to an empty bag
        CardSlotsBag bag = new CardSlotsBag();

        //Test 1: test that addNewEntry() adds a new card to an empty that has card but is not full
        //Assert that the bag is empty
        Assert.assertTrue(bag.isEmpty());

        //add the card
        bag.addNewEntry(testCard1);

        //Assert that the addNewEntry(testCard1) add the card to the bag.
        Assert.assertEquals(bag.cardAtPosition(0),testCard1);

        //Test 2: test that addNewEntry() does not add a new card to a bag that is full.
        //fill the bag until its full, add 8 more cards
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);

        //Assert that testCard2 does not get added when the bag contains is full
        //Assert that the bag is full
        Assert.assertTrue(bag.isArrayFull());

        //try to add testCard2
        bag.addNewEntry(testCard2);

        //Assert that the bag does not contain testCard2
        Assert.assertFalse(bag.contains(testCard2));
    }

    /**
     * Test 1 Assert that isArrayFull() returns false when it is not full but is empty.
     * Test 2 Assert that isArrayFull() returns false when it is not full but has 1 card.
     * Test 3 Assert that isArrayFull() returns true when it is full
     */
    @Test
    public void isArrayFull() {
        CardSlotsBag bag = new CardSlotsBag();

        //Assert that isArrayFull() returns false when it is not full but is empty.
        Assert.assertFalse(bag.isArrayFull());

        //Assert that isArrayFull() returns false when it is not full but has 1 card.
        bag.addNewEntry(testCard1);
        Assert.assertFalse(bag.isArrayFull());

        //Add 8 more cards to fill up the bag.
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);
        bag.addNewEntry(testCard1);

        //Assert that isArrayFull() returns true when it is full
        Assert.assertTrue(bag.isArrayFull());
    }

    /**
     * Testing the remove(), which takes no params that it removes the last card in the bag
     */
    @Test
    public void remove() {
        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Assert bag is not empty
        Assert.assertFalse(bag.isEmpty());

        //Assert that remove removes the last element in the bag, and assert bag is now empty.
        bag.remove();
        Assert.assertTrue(bag.isEmpty());

        //Assert that removing when the bag is empty, should still be empty
        bag.remove();
        Assert.assertTrue(bag.isEmpty());
    }

    /**
     * Testing the remove(Card aCard), that it removes the last card in the bag
     */
    @Test
    public void testRemove() {
        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Assert bag is not empty
        Assert.assertFalse(bag.isEmpty());

        //Assert that remove remove(param) is called the last element in the bag, and assert bag is now empty.
        bag.remove(testCard1);
        Assert.assertTrue(bag.isEmpty());

        //Assert that remove(param) is called when the card cant be found, does not remove any cards in the bag
        bag.addNewEntry(testCard1);
        bag.remove(testCard2);
        Assert.assertFalse(bag.isEmpty());

        //Assert that remove(param) does remove a card when it can be found and the bag is now empty
        bag.remove(testCard1);
        Assert.assertTrue(bag.isEmpty());
    }

    /**
     * Test, clear(), clears the CardSlotsBag
     */
    @Test
    public void clear() {

        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(new Card(House.CLUBS, Rank.ACE));

        //Assert bag is not empty
        Assert.assertFalse(bag.isEmpty());

        //Assert bag is empty after clear()
        bag.clear();
        var expected = true;
        var actual = bag.isEmpty();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Test that contains() returns true when it finds a card and false when it does not.
     */
    @Test
    public void contains() {
        /*
        * Test 1, Assert that contains returns true when the bag contains the input card.
        */
        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Assert that bag does Contain a card of Card(House.CLUBS, Rank.ACE)
        var actual = bag.contains(testCard1);
        var expected = true;

        Assert.assertEquals(expected, actual);

        /*
         * Test 2, Assert that contains returns false when the bag does not contain the input card.
         */
        CardSlotsBag bag2 = new CardSlotsBag();
        var actual2 = bag2.contains(testCard1);
        var expected2 = false;
        Assert.assertEquals(expected2, actual2);
    }

    /**
     * Test that toArrayCopy can copy the bag object
     */
    @Test
    public void toArrayCopy() {
        //Assert that toArrayCopy() creates a new Card[] of size 9
        CardSlotsBag bag = new CardSlotsBag();

        //Create expected and set first slot in array.
        Card[] expectedCardArray = new Card[9];
        expectedCardArray[0] = testCard1;

        //Add testCard1
        bag.addNewEntry(testCard1);

        //Assert that toArrayCopy() creates a new Card[] of size 9
        var actual = bag.toArrayCopy();
        Assert.assertEquals(9, actual.length);

        //Assert that bag.bag.toArrayCopy() when testCard1 has been added equals expectedCardArray
        Assert.assertEquals(actual, expectedCardArray);
    }

    /**
     * Test that display does not crash the application by calling .display()
     * as we cant test system.out without illegal libraries
     */
    @Test
    public void display() {

        CardSlotsBag bag = new CardSlotsBag();
        bag.addNewEntry(testCard1);

        //Assert that .display(true), does not crash the application
        bag.display(true);

        //Assert that .display(true), does not crash the application
        bag.display(false);
    }
}