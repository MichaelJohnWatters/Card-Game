public class Round {

    private int roundNumber;
    private CardSlotsBag cardSlotBag;
    private Round nextRound;

    /**
     * When a round is created it will have a round number.
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
    }

    public void setupRound(){
        //Firstly replace empty slots if required.
        maybeReplaceEmptySlots();

        //Check if the current round is a stalemate otherwise allow user to play round.
        if (isStalemate(cardSlotBag)){
            //if is Stalemate

        } else {
            //playRound
        }
    }

    private static boolean isStalemate(CardSlotsBag bag) {
        if(bag.containsThreeFaceCards()) {
            return false;
        } else if (bag.containsElevensPair()) {
            return false;
        } else {
            return true;
        }
    }

    private void maybeReplaceEmptySlots() {
        //temp card, will use a draw from deck method
        Card tempCard1 = new Card(House.CLUBS, Rank.ACE);

        //if not all slots in the bag are filled draw new cards.
        if(!cardSlotBag.isArrayFull()){

            //Work out how many cards need drawn.
            int cardsToDraw = CardSlotsBag.getFixedCapacity() - cardSlotBag.getCurrentSize();

            //loop through number cards needed to reach 9 cards in play.
            for (int i = 0; i < cardsToDraw; i++) {
                //draw a card from the top of the Deck.
                //TEMP TODO
                cardSlotBag.addNewCard(tempCard1);
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
