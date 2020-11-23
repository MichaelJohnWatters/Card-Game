public class Round {

    private int roundNumber;
    private CardSlotsBag cardSlotBag;
    private Round nextRound;

    /**
     * Used for subsequent rounds
     *
     * cardSlots will be filled with the cardSlots of the previous round.
     *
     * At instaiation of a round there will be no chosen cards
     * At instaiation of a round there will be currently no next round.
     *
     * @param roundNumber the number of the round.
     * @param cardSlotBag a bag for Cards representing cards in play.
     */
    public Round(int roundNumber, CardSlotsBag cardSlotBag) {
        this.roundNumber = roundNumber;
        this.cardSlotBag = cardSlotBag;
        this.nextRound = null; //next round is always null until set by setNextRound()
    }

    //for the first round in a game
    public Round() {
        this.roundNumber = 0;
        this.cardSlotBag = new CardSlotsBag(); // new empty cardSlotBag,
        this.nextRound = null; //next round is always null until set by setNextRound()
    }

    private static Card drawTopCard(Deck deck){
        //TODO Fake return atm
        return new Card(House.DIAMONDS, Rank.ACE);
        //return deck.pop();
    }

    protected boolean isStalemate() {
        return (cardSlotBag.containsKingQueenJack() || cardSlotBag.containsElevensPair());
    }

    protected void replaceEmptyCardSlots(Deck deck) {
        //TODO AND DECK ISNT EMPTY
        //if not all slots in the bag are filled draw new cards.
        if(!cardSlotBag.isArrayFull()){

            //Work out how many cards need drawn.
            int cardsToDraw = CardSlotsBag.getFixedCapacity() - cardSlotBag.getCurrentSize();
            System.out.println("Number of Cards to draw: " + cardsToDraw);

            //loop through number cards needed to reach 9 cards in play.
            for (int i = 0; i < cardsToDraw; i++) {
                //draw a card from the top of the Deck.
                //TEMP TODO
                Card drawnCard = drawTopCard(deck);
                System.out.println("drawing: " + drawnCard);
                cardSlotBag.addNewCard(drawnCard);
            }

        }
    }
    
    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public CardSlotsBag getCardSlotBag() {
        return cardSlotBag;
    }

    public void setCardSlotBag(CardSlotsBag cardSlotBag) {
        this.cardSlotBag = cardSlotBag;
    }

    public Round getNextRound() {
        return nextRound;
    }

    public void setNextRound(Round nextRound) {
        this.nextRound = nextRound;
    }
}
