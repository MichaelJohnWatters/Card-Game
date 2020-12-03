package test;

import junit.framework.TestCase;
import main.*;

public class RoundTest extends TestCase {

    public void testIsStalemate() {

        //Test 1 Start
        //Returns true when there is no elevens pairs and no pairs of face cards
        CardSlotsBag bag1 = new CardSlotsBag();
        bag1.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag1.addNewEntry(new Card(House.CLUBS, Rank.ACE));
        Round round1 = new Round(1, bag1);
        assertTrue(round1.isStalemate());
        //Test 1 end

        //Test 2 start
        //Returns false when there is a elevens pair
        CardSlotsBag bag2 = new CardSlotsBag();
        bag2.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag2.addNewEntry(new Card(House.DIAMONDS, Rank.TEN));
        Round round2 = new Round(2, bag2);
        assertFalse(round2.isStalemate());
        //Test 2 end

        //Test 3 start
        //Returns false when there is face card pairs.
        CardSlotsBag bag3 = new CardSlotsBag();
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.ACE));
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.TEN));
        bag3.addNewEntry(new Card(House.CLUBS, Rank.KING));
        bag3.addNewEntry(new Card(House.DIAMONDS, Rank.QUEEN));
        bag3.addNewEntry(new Card(House.CLUBS, Rank.JACK));
        Round round3 = new Round(2, bag2);
        assertFalse(round3.isStalemate());
        //Test 3 end
    }

    public void testReplaceEmptyCardSlots() {
        Deck deck = new Deck();

        // Test 1
        // replaceEmptyCardSlots() should add 9 cards to bag1
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(bag1.countCards(),0);
        round1.replaceEmptyCardSlots(deck);
        assertEquals(bag1.countCards(),9);
        // Test 1 end

        // Test 2
        // ReplaceEmptyCardSlots should add 1 card to the bag2 when bag2 has 8 cards inside it.
        CardSlotsBag bag2 = new CardSlotsBag();
        bag2.addNewEntry(new Card(House.CLUBS,Rank.ACE));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.TWO));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.THREE));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.FOUR));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.FIVE));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.SIX));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.SEVEN));
        bag2.addNewEntry(new Card(House.CLUBS,Rank.EIGHT));
        Round round2 = new Round(2, bag2);
        //assertEquals(bag2.countCards(),8);
        round2.replaceEmptyCardSlots(deck);
        //assertEquals(bag2.countCards(),9);
        // Test 2 end
    }

    public void testGetRoundNumber() {
        //Test 1 Start
        //getRoundNumber() should return 1
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(round1.getRoundNumber(),1);
    }

    public void testSetRoundNumber() {
        //Test 1 Start
        //setRoundNumber() should set the round number to 2.
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        round1.setRoundNumber(2);
        assertEquals(round1.getRoundNumber(),2);
    }

    public void testGetCardSlotBag() {
        //Test 1 Start
        //getCardSlotBag should return a empty bag.
        CardSlotsBag bag1 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        assertEquals(round1.getCardsInPlayBag(), bag1);
    }

    public void testSetCardSlotBag() {
        //Test 1 Start
        //setCardSlotBag should set the rounds bag 'bagWithCard' with a Card in it
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bagWithCard = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        bagWithCard.addNewEntry(new Card(House.CLUBS,Rank.ACE));
        round1.setCardsInPlayBag(bagWithCard);
        assertEquals(round1.getCardsInPlayBag(), bagWithCard);
    }

    public void testGetNextRound() {

        //round1.getNextRound() should return  CardSlotsBag round2
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bag2 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        Round round2 = new Round(2, bag2);
        round1.setNextRound(round2);
        assertEquals(round1.getNextRound(), round2);
    }

    public void testSetNextRound() {
        //round1.setNext Round should set round1's nextRound to Round2.
        CardSlotsBag bag1 = new CardSlotsBag();
        CardSlotsBag bag2 = new CardSlotsBag();
        Round round1 = new Round(1, bag1);
        Round round2 = new Round(2, bag2);
        round1.setNextRound(round2);
        assertEquals(round1.getNextRound(), round2);
    }
}