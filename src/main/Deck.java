package main;

import java.util.EmptyStackException;


public class Deck {
    
    private static final int MAX_CARDS = 52;
    private CardNode topNode;

    /**
     * Note
     *
     * I have shuffled the DECK array using util.Random
     * created a shuffled deck when a new deck object is instantiated
     *
     *
     * */
    public Deck() {
        topNode = null;
    }

    public void shuffleDeck() {
        Deck deck = new Deck();
    }

    // TODO could maybe be a way to shuffle
    // Randomly shuffles the cards in the deck in place.
    public void shuffle() {
        // Implements a riffle shuffle -- splits the deck in two,
        // and splices the two together.
        // Note: this is an _imperfect_ shuffle. It doesn't actually
        // randomly shuffle the deck in any way, making this a rigged deck.

        int half = MAX_CARDS / 2;
        Deck temp1 = new Deck();
        Deck temp2 = new Deck();

        // Split the deck in two
        //this.transfer(this.cards, temp1, half);
       //this.transfer(this.cards, temp2); // move everything else

        // Take one card at a time from each half and add it back
        for (int j = 0; j < half; j++) {
           // this.cards.push(temp1.pop());
           // this.cards.push(temp2.pop());
        }

        // Take care of the last card if we have an odd number of cards
        if (!temp2.isEmpty()) {
            //this.cards.push(temp2.pop());
        }
    }

    public void push (Card newEntry) {
        CardNode newNode = new CardNode(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
    }

    public Card pop() {
        Card dataToReturn = peek();
        topNode = topNode.getNext();
        return dataToReturn;
    }

    public Card peek() {
        if (topNode == null) {
            throw new EmptyStackException();
        }
        else {
            return topNode.getData();
        }
    }

    public boolean isEmpty() {
        return topNode == null;
    }

    public void clear() {
        topNode = null;
    }
}
