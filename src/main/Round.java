package main;

/**
 * This Class represents each round within a game,
 * It stores information about each round.
 */
public class Round {

    private int roundNumber;
    private Round nextRound;

    //Cards in play in current round.
    private CardSlotsBag cardsInPlayBag;

    //Used to remember each rounds events, such as drawn cards and discarded cards.
    private CardSlotsBag roundMemoryDrawCards;
    private CardSlotsBag roundMemoryDiscardCards;

    /**
     * Used for subsequent rounds
     *
     * cardSlots will be filled with the cardSlots of the previous round.
     *
     * At instaiation of a round there will be no chosen cards
     * At instaiation of a round there will be currently no next round.
     *
     * @param roundNumber the number of the round.
     * @param cardsInPlayBag a bag for Cards representing cards in play.
     */
    public Round(int roundNumber, CardSlotsBag cardsInPlayBag) {
        this.roundNumber = roundNumber;
        this.cardsInPlayBag = cardsInPlayBag;
        this.roundMemoryDrawCards = new CardSlotsBag();
        this.roundMemoryDiscardCards = new CardSlotsBag();
        this.nextRound = null;
    }

    /**
     * Used for first round in the round queue
     *
     * At instaiation of this round there will be no chosen cards
     * At instaiation of a round there will be currently no next round.
     *
     * @param roundNumber the number of the round.
     */
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.cardsInPlayBag = new CardSlotsBag();
        this.roundMemoryDrawCards = new CardSlotsBag();
        this.roundMemoryDiscardCards = new CardSlotsBag();
        this.nextRound = null;
    }

    /**
     * Removes the top card from the deck supplied
     * @param deck deck to remove a card from
     * @return Card at the top of the supplied deck.
     */
    private static Card drawFromDeck(Deck deck){
        return deck.pop();
    }

    /**
     * Checks if the round is a stalemate
     * @return true if is stalemate false if not
     */
    public boolean isStalemate() {
        if(cardsInPlayBag.containsKingQueenJack() || cardsInPlayBag.containsElevensPair()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Replaces empty slots in the cardsInPlayBag, from the supplied Deck
     * @param deck deck to drawn cards from
     */
    public void replaceEmptyCardSlots(Deck deck) {
        //if not all slots in the bag are filled draw new cards.
        if(!cardsInPlayBag.isArrayFull()) {

            int cardsToDraw = cardsInPlayBag.countEmptySlots();
            System.out.println("number of Cards to be drawn: " + cardsToDraw);

            if(cardsToDraw != 0) {
                System.out.print("cards drawn: ");
                for (int i = 0; i < cardsToDraw; i++) {

                    Card drawnCard = drawFromDeck(deck);

                    //Make sure drawnCard is not null, happens when deck is empty.
                    if(drawnCard != null){
                        System.out.print(" " + drawnCard.toString());

                        boolean added = cardsInPlayBag.addNewEntry(drawnCard);

                        //Add to round memory of drawn Cards for replay feature
                        roundMemoryDrawCards.addNewEntry(drawnCard);

                        if(added){
                            System.out.println(" was drawn...");
                        }
                    }
                }
            }
        } else {
            System.out.println("card slots are full no cards drawn...");
        }
    }

    /**
     * Get the memory of the cards drawn in this round
     * @return a CardSlotsBag off cards drawn
     */
    public CardSlotsBag getRoundMemoryDrawCards() {
        return roundMemoryDrawCards;
    }
    /**
     * set the memory of the cards drawn in this round
     */
    public void setRoundMemoryDrawCards(CardSlotsBag roundMemoryDrawCards) {
        this.roundMemoryDrawCards = roundMemoryDrawCards;
    }

    /**
     * Get the memory of the cards discarded in the currrent round.
     * @return a CardSlotsBag off cards discarded
     */
    public CardSlotsBag getRoundMemoryDiscardCards() {
        return roundMemoryDiscardCards;
    }

    /**
     * Update the discarded card memory,
     * @param card the card to add
     */
    public void updateDiscardCardMemory(Card card) {
        this.roundMemoryDiscardCards.addNewEntry(card);
    }

    /**
     * Get the round number
     * @return int number of the round
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * Set the round number
     * @param roundNumber number to use
     */
    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    /**
     * Get the cards in play bag
     * @return returns CardSlotsBag of cards in play.
     */
    public CardSlotsBag getCardsInPlayBag() {
        return cardsInPlayBag;
    }

    /**
     * set the cards in play bag to a supplied CardSlotsBag
     * @param cardsInPlayBag CardSlotsBag to used for set
     */
    public void setCardsInPlayBag(CardSlotsBag cardsInPlayBag) {
        this.cardsInPlayBag = cardsInPlayBag;
    }

    /**
     * Get the next round
     * @return the next Round.
     */
    public Round getNextRound() {
        return nextRound;
    }

    /**
     * Set the next round
     * @param nextRound round to use
     */
    public void setNextRound(Round nextRound) {
        this.nextRound = nextRound;
    }
}
