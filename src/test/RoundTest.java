package test;

import junit.framework.TestCase;
import main.*;

/**
 * Unit tests for the Round Class methods.
 */
public class RoundTest extends TestCase {

    /**
     * Test 1 Start
     * Returns true when there is no elevens pairs and no pairs of face cards
     * <p>
     * Test 2 start
     * Returns false when there is a elevens pair
     * <p>
     * Test 3 start
     * Returns false when there is face card pairs.
     */
    public void testIsStalemate() {
        //Test 1 Start
        //Returns true when there is no elevens pairs and no pairs of face cards
        CardSlotsBag bag1 = new CardSlotsBag();
        bag1.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag1.addNewEntry(new Card(House.CLUBS, Rank.ACE));
        Round round1 = new Round(1, bag1);
        //Assert Test 1
        assertTrue(round1.isStalemate());

        //Test 2 start
        //Returns false when there is a elevens pair
        CardSlotsBag bag2 = new CardSlotsBag();
        bag2.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag2.addNewEntry(new Card(House.DIAMONDS, Rank.TEN));
        Round round2 = new Round(2, bag2);
        //Assert Test 2
        assertFalse(round2.isStalemate());

        //Test 3 start
        //Returns false when there is face card pairs.
        CardSlotsBag bag3 = new CardSlotsBag();
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.TEN));
        bag3.addNewEntry(new Card(House.CLUBS, Rank.KING));
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.QUEEN));
        bag3.addNewEntry(new Card(House.CLUBS, Rank.JACK));
        Round round3 = new Round(2, bag2);

        //Assert Test 3
        assertFalse(round3.isStalemate());
    }

    /**
     * Test 1
     * replaceEmptyCardSlots() should add 9 cards to bag1
     * Test 2
     * replaceEmptyCardSlots should add 1 card to the bag2 when bag2 has 8 cards inside it.
     */
    public void testReplaceEmptyCardSlots() {
        Deck deck = new Deck();
        deck.createFullDeckOfCards();

        // Test 1
        // replaceEmptyCardSlots() should add 9 cards to bag1
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(bag1.countCards(), 0);
        round1.replaceEmptyCardSlots(deck);

        //Assert Test 1
        assertEquals(bag1.countCards(), 9);

        // Test 2
        // ReplaceEmptyCardSlots should add 1 card to the bag2 when bag2 has 8 cards inside it.
        CardSlotsBag bag2 = new CardSlotsBag();
        bag2.addNewEntry(new Card(House.CLUBS, Rank.ACE));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.TWO));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.THREE));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.FOUR));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.FIVE));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.SIX));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.SEVEN));
        bag2.addNewEntry(new Card(House.CLUBS, Rank.EIGHT));
        Round round2 = new Round(2, bag2);

        //Assertions Test 2
        assertEquals(bag2.countCards(), 8);
        round2.replaceEmptyCardSlots(deck);
        assertEquals(bag2.countCards(), 9);
    }

    /**
     * getRoundNumber() should return 1
     */
    public void testGetRoundNumber() {
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(round1.getRoundNumber(), 1);
    }

    /**
     * setRoundNumber() should set the round number to 2.
     */
    public void testSetRoundNumber() {
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        round1.setRoundNumber(2);
        assertEquals(round1.getRoundNumber(), 2);
    }

    /**
     * getCardSlotBag should return a empty bag.
     */
    public void testGetCardSlotBag() {
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(round1.getCardsInPlayBag(), bag1);
    }

    /**
     * setCardSlotBag should set the rounds bag 'bagWithCard' with a Card in it
     */
    public void testSetCardSlotBag() {
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bagWithCard = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        bagWithCard.addNewEntry(new Card(House.CLUBS, Rank.ACE));
        round1.setCardsInPlayBag(bagWithCard);
        assertEquals(round1.getCardsInPlayBag(), bagWithCard);
    }

    /**
     * round1.getNextRound() should return  CardSlotsBag round2
     */
    public void testGetNextRound() {
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bag2 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        Round round2 = new Round(2, bag2);
        round1.setNextRound(round2);
        assertEquals(round1.getNextRound(), round2);
    }

    /**
     * round1.setNext Round should set round1's nextRound to Round2.
     */
    public void testSetNextRound() {
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bag2 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        Round round2 = new Round(2, bag2);
        round1.setNextRound(round2);
        assertEquals(round1.getNextRound(), round2);
    }
}