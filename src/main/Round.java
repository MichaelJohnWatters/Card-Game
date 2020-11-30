package main;

public class Round {

    private int roundNumber;
    private CardSlotsBag cardSlotBag;
    private Round nextRound;

    //Used to remember each rounds actions.
//    private Card[] drawnCardsMemory = new Card[9];
//    private Card[] bagCardSlotsMemory = new Card[9];
//    private Card[] pairsRemovedMemory = new Card[3];

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
    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
        this.cardSlotBag = new CardSlotsBag(); // new empty cardSlotBag,
        this.nextRound = null; //next round is always null until set by setNextRound()
    }

    private static Card drawFromDeck(Deck deck){
        //TODO Fake return atm replace with deck.pop();
        return new Card(House.DIAMONDS, Rank.ACE);
        //return deck.pop();
    }

    protected boolean isStalemate() {
        return (!cardSlotBag.containsKingQueenJack() || !cardSlotBag.containsElevensPair());
    }

    protected void replaceEmptyCardSlots(Deck deck) {


//
//        System.out.println("*******************************************************");
//        System.out.println("*******************************************************");
//        System.out.println("**********************BEFORE**********************");
//        System.out.println("*******************************************************");
//        this.cardSlotBag.display();
//        System.out.println("*******************************************************");
//        System.out.println("**********************BEFORE***************************");
//        System.out.println("*******************************************************");
//        System.out.println("*******************************************************");

        //if not all slots in the bag are filled draw new cards.
        if(!cardSlotBag.isArrayFull()){
            System.out.println("replaceEmptyCardSlots - NOT FULL");

            //TODO do checks if there is cards to draw
            int cardsToDraw = CardSlotsBag.getFixedCapacity() - cardSlotBag.countCards();
            System.out.println(CardSlotsBag.getFixedCapacity() + " - " + cardSlotBag.countCards() + " = " + (CardSlotsBag.getFixedCapacity() - cardSlotBag.countCards()) );
            System.out.println("number of Cards to be drawn: " + cardsToDraw);

            //loop through number cards needed to reach 9 cards in play.
            System.out.print("drawn: ");
            for (int i = 0; i < cardsToDraw; i++) {

                // TODO check if null if is null
                // say that no more cards to card.
                Card drawnCard = drawFromDeck(deck);

                System.out.print(" " + drawnCard.toString());
                //TODO
                boolean added = cardSlotBag.addNewCard(drawnCard);
                System.out.println("ADDED??" + added);
            }
            System.out.println();
        } else {
            System.out.println("no cards drawn...");
        }

//        System.out.println("*******************************************************");
//        System.out.println("*******************************************************");
//        System.out.println("**********************AFTER**********************");
//        System.out.println("*******************************************************");
//        this.cardSlotBag.display();
//        System.out.println("*******************************************************");
//        System.out.println("**********************AFTER***************************");
//        System.out.println("*******************************************************");
//        System.out.println("*******************************************************");

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
