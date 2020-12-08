package test;

import main.Card;
import main.CardSlotsBag;
import main.House;
import main.Rank;
import org.junit.Assert;
import org.junit.Test;

public class CardSlotsBagTest {

    Card testCard1 = new Card(House.CLUBS, Rank.ACE);
    Card testCard2 = new Card(House.DIAMONDS, Rank.TWO);
    Card testCard3 = new Card(House.SPADES, Rank.KING);

    /**
     * Test that containsCardValue() can find cards of the input int value in the bag.
     */
    @Test
    public void containsCardValue() {
        //Assert containsCardValue() returns true when it finds a card with the same int value

        //Assert containsCardValue() returns false when it does not find a card with the input in value.

    }

    /**
     * Test that findsAndReturnsCardValue() can find and return cards with the asked for value
     */
    @Test
    public void findsAndReturnsCardValue() {
        //Assert findsAndReturnsCardValue() finds and returns a card value when the asked for card are present

        //Assert findsAndReturnsCardValue() returns null when the asked for cards are not in the bag

    }

    /**
     * Test that containsKingQueenJack() returns true when it finds a king queen jack set.
     */
    @Test
    public void containsKingQueenJack() {
        //Assert containsKingQueenJack() should return true when there is a King Queen and a Jack in the bag

        //Assert containsKingQueenJack() should return false when there is only a King and a Queen in the bag

        //Assert containsKingQueenJack() should return false when there is no face cards in the bag.
    }

    /**
     * Test findAndReturnKingQueenJackPair finds and returns a king queen jack set.
     */
    @Test
    public void findAndReturnKingQueenJackPair() {
        //Assert findAndReturnKingQueenJackPair() finds and returns a card King Queen Jack set are in the bag.

        //Assert findAndReturnKingQueenJackPair() returns null when the bag does not contain a King Queen Jack set.
    }

    /**
     * Test containsElevensPair() returns true when the bag contains an elevens pair
     */
    @Test
    public void containsElevensPair() {
        //Assert  containsElevensPair() returns true when the bag contains an elevens pair

        //Assert containsElevensPair() returns false when the bag does not contain an  elevens pair

    }

    /**
     * Test findAndReturnElevensPair() can find and return an array of the valid elevens pair
     */
    @Test
    public void findAndReturnElevensPair() {
        //Assert findAndReturnElevensPair() returns the found elevens pair cards

        //Assert returns null when it cant find a valid elevens pair.

    }

    /**
     * Test countCard and manually count all the card in the bag onne by one
     */
    @Test
    public void countCards() {

        //Asset that countCards() returns 0 when there is zero cards

        //Asset that countCards() returns 2 when there is 2 cards

        //Asset that countCards() returns 9 when the bag is full

    }

    /**
     * Test countEmptySlots is able to count the number of empty slots.
     */
    @Test
    public void countEmptySlots() {
        //Asset that countEmptySlots() returns 9 when there is zero cards in the bag.

        //Asset that countEmptySlots() returns 2 when there is 2 empty slots

        //Asset that countEmptySlots() returns 0 when the bag is full


    }

    /**
     * Test that cardAtPosition() can return the correct card at the input int postion
     */
    @Test
    public void cardAtPosition() {

        //Assert cardAtPosition() returns a Card at the correct position when the slot is not empty

        //Assert cardAtPosition() returns null when the slot asked for is empty.

    }

    @Test
    public void getCurrentSize() {
        //Assert getCurrentSize() returns 0 when bag is empty

        //Assert getCurrentSize() returns 2 when bag has 2 cards

        //Assert getCurrentSize() returns 9 when the bag is full
    }

    @Test
    public void isEmpty() {
        //Assert is isEmpty() returns true when it is empty

        //Assert is isEmpty() returns false when it is not empty

    }

    @Test
    public void addNewEntry() {
        //Assert that addNewEntry() adds a new card to an empty bag

        //Assert that addNewEntry() adds a new card to an empty that has card but is not full

        //Assert that addNewEntry() does not add a new card when the bag is full

    }

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
        //Assert that toArrayCopy() is able to copy the current bag object to a new bag.

    }

    /**
     * Test that display displays cards correctly.
     */
    @Test
    public void display() {

        //Asset that the display prints out correctly

    }
}