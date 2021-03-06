package main;

import Interfaces.StackInterface;

import java.util.Random;

public class Deck implements StackInterface<Card> {

    private CardNode topNode;

    /**
     * Used to populate this deck with all the required cards in a 52 card deck.
     */
    public void createFullDeckOfCards() {
        //Pack of card typically comes in the reverse of this order, but will be correct when popping from the stack.
        House[] houseArray = {House.SPADES, House.DIAMONDS, House.CLUBS, House.HEARTS};

        //in reverse order, as when we push the order will be flipped if we popped all cards.
        Rank[] rankArray = {
                Rank.KING, Rank.QUEEN, Rank.JACK, Rank.TEN, Rank.NINE,
                Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR,
                Rank.THREE, Rank.TWO, Rank.ACE
        };

        //for each house, loop through each rank and push that card to the stack.
        for (House house : houseArray) {
            for (Rank rank : rankArray) {
                this.push(new Card(house, rank));
            }
        }
    }

    /**
     * A number of shuffle to make sure the cards are well shuffled followed by another ripple
     * To make sure the cards are well shuffled.
     */
    public void rigorousShuffle() {
        rippleShuffle();
        randomShuffle();
        rippleShuffle();
        randomShuffle();
        rippleShuffle();
    }

    /**
     * Method representing a ripple shuffle performed on this deck of cards.
     */
    private void rippleShuffle() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        //the separation point of this deck (size /2)
        int separator = countNumberOfCards() / 2;

        //put the first number of cards cut off at separator into deck 1.
        for (int i = 0; i < separator; i++) {
            deck1.push(this.pop());
        }

        //loop through the rest of the cards in the original deck and place into deck2.
        while (!this.isEmpty()) {
            deck2.push(this.pop());
        }

        //We now have the original deck split into two decks.
        //deck is now 'spilt in two' re-pop them into this deck alternatively, as in a ripple shuffle.
        int assembleCounter = 1;

        //loop through while deck1 and deck 2 are not null
        //use modulus and counter to decide which deck to pop the card from and push to this deck.
        //will alternate between each deck1 and deck2
        while (deck1.topNode != null || deck2.topNode != null) {
            if ((assembleCounter % 2) == 0) {
                this.push(deck1.pop());
            } else {
                this.push(deck2.pop());
            }
            assembleCounter++;
        }
    }

    /**
     * Randomly shuffles this deck of cards by looping through the deck and randomly swapping the current card.
     * With a Card at a random index.
     */
    private void randomShuffle() {

        Random rnd = new Random();
        Card[] cardArray = this.toArray();

        //loop through each card and randomly swap with another
        for (int i = 0; i < cardArray.length; i++) {

            int roundRandom = rnd.nextInt(cardArray.length - 1);

            Card currentCard = cardArray[i];
            Card swapWithCard = cardArray[roundRandom];

            //current cards position
            cardArray[i] = swapWithCard;

            //swap with card's position
            cardArray[roundRandom] = currentCard;
        }

        //assemble the deck by re-pushing all the cards into our deck ADT.
        for (Card card : cardArray) {
            this.push(card);
        }
    }

    /**
     * Pushs a new Card onto the Deck/Stack.
     *
     * @param newCard the card you want put on the stack.
     */
    public void push(Card newCard) {
        CardNode newNode = new CardNode(newCard);
        newNode.setNext(topNode);
        topNode = newNode;
    }

    /**
     * Pops the top card from the stack and sets the topNode to the next card below.
     *
     * @return Card removed from the top of the stack.
     */
    public Card pop() {
        if (peek() != null) {
            Card dataToReturn = peek();
            topNode = topNode.getNext();
            return dataToReturn;
        } else {
            return null;
        }
    }

    /**
     * Has a look at the next card in the Deck but does not remove it from the deck.
     *
     * @return Card the top card on the deck
     */
    public Card peek() {
        if (topNode == null) return null;
        else return topNode.getData();
    }

    /**
     * A Manually Count of the cards in the stack, as a human would count, card by card.
     *
     * @return int, number of cards in the stack
     */
    public int countNumberOfCards() {
        int count = 0;
        if (topNode == null) {
            return 0;
        } else {
            CardNode currentNode = topNode;
            while (currentNode != null) {
                currentNode = currentNode.getNext();
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the stack is empty
     *
     * @return boolean returns true if empty
     */
    public boolean isEmpty() {
        return (topNode == null);
    }

    /**
     * Clears the deck/stack.
     */
    public void clear() {
        topNode = null;
    }

    /**
     * Converts the Deck/Stack into an Array by popping, the stack will be empty after this method is used.
     *
     * @return Card[] converts the Stack to an Array
     */
    public Card[] toArray() {
        Card[] cardArray = new Card[countNumberOfCards()];

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = this.pop();
        }

        return cardArray;
    }
}
