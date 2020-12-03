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

    //for the first round in a game
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.cardsInPlayBag = new CardSlotsBag();
        this.roundMemoryDrawCards = new CardSlotsBag();
        this.roundMemoryDiscardCards = new CardSlotsBag();
        this.nextRound = null;
    }

    private static Card drawFromDeck(Deck deck){
        return deck.pop();
    }

    public boolean isStalemate() {
        if(cardsInPlayBag.containsKingQueenJack() || cardsInPlayBag.containsElevensPair()) {
            return false;
        } else {
            return true;
        }
    }

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

    public CardSlotsBag getRoundMemoryDrawCards() {
        return roundMemoryDrawCards;
    }

    public void setRoundMemoryDrawCards(CardSlotsBag roundMemoryDrawCards) {
        this.roundMemoryDrawCards = roundMemoryDrawCards;
    }

    public CardSlotsBag getRoundMemoryDiscardCards() {
        return roundMemoryDiscardCards;
    }

    public void setRoundMemoryDiscardCards(CardSlotsBag roundMemoryDiscardCards) {
        this.roundMemoryDiscardCards = roundMemoryDiscardCards;
    }

    public void updateDiscardCardMemory(Card card) {
        this.roundMemoryDiscardCards.addNewEntry(card);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public CardSlotsBag getCardsInPlayBag() {
        return cardsInPlayBag;
    }

    public void setCardsInPlayBag(CardSlotsBag cardsInPlayBag) {
        this.cardsInPlayBag = cardsInPlayBag;
    }

    public Round getNextRound() {
        return nextRound;
    }

    public void setNextRound(Round nextRound) {
        this.nextRound = nextRound;
    }
}
