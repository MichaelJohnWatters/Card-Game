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
     * @param roundNumber the number of the round
     * @param cardSlotBag a bag for Cards representing cards in play.
     */
    public Round(int roundNumber, CardSlotsBag cardSlotBag) {
        this.roundNumber = roundNumber;
        this.cardSlotBag = cardSlotBag;
    }

    public void setupRound(){
        //Firstly replace empty slots if required.
        maybeReplaceEmptySlots(this);

        //Check if the current round is a stalemate.
        if (isRoundStalemate(this)){

        } else {
            //playRound
        }
    }

    public static boolean isRoundStalemate(Round aRound){
        // if round cant be completed
        //go to failed game menu
        return true;
    }

    public static void maybeReplaceEmptySlots(Round aRound){
        //find the empty slots caused from prev round and fill them with new cards
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
