package main;

import java.util.EmptyStackException;
import java.util.Random;


public class Deck {
    
    private static final int MAX_CARDS = 52;
    private CardNode topNode;
    private Deck cards;


    /**
     * Note
     *
     * deck of cards as a stack
     *
     * shuffle method
     *
     *
     * */
    public Deck() {
        topNode = null;
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

    /*public void shuffleRandom() {
        Random random = new Random();
        int n = MAX_CARDS;
        for (int i = n - 1; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            // swap cards at indices i and j
            CardNode tmp = (CardNode) this.cards.get(j);
            this.cards.set(j, cards.get(i));
            this.cards.set(i, tmp);
        }

    }*/

    // Randomly shuffles the cards in the deck in place.
    public void shuffleRiffle() {
        // Implements a riffle shuffle -- splits the deck in two,
        // and splices the two together.

        int half = MAX_CARDS / 2;
        Deck temp1 = new Deck();
        Deck temp2 = new Deck();

        // Split the deck in two
        this.transfer(this.cards, temp1, half);
        this.transfer(this.cards, temp2); // move everything else

        // Take one card at a time from each half and add it back
        for (int j = 0; j < half; j++) {
            this.cards.push(temp1.pop());
            this.cards.push(temp2.pop());
        }

        // Take care of the last card if we have an odd number of cards
        if (!temp2.isEmpty()) {
            this.cards.push(temp2.pop());
        }
    }

    // Transfers everything in the 'src' stack to the 'dest' stack.
    private void transfer(Deck src, Deck dest) {
        this.transfer(src, dest, MAX_CARDS);
    }

    // Transfers the top 'amount' of elements from the 'src' stack to
    // the 'dest' stack.
    private void transfer(Deck src, Deck dest, int amount) {
        for (int i = 0; i < amount; i++) {
            dest.push(src.pop());
        }
    }
}
