public class Round {

    private static final int MAX_CARD_SLOTS = 9;
    private int roundNumber;
    private Card[] cardSlots;
    private Card[] chosenCards;//temp replace somthing else
    private Round nextRound;

    /**
     * When a round is created it will have a round number.
     * cardSlots will be filled with the cardSlots of the previous round.
     *
     * At instaiation of a round there will be no chosen cards
     * At instaiation of a round there will be currently no next round.
     *
     * @param roundNumber the number of the round
     * @param cardSlots number of available slots, elevens has 9
     */
    public Round(int roundNumber, Card[] cardSlots) {
        this.roundNumber = roundNumber;
        this.cardSlots = cardSlots;
        this.chosenCards = null;
        this.nextRound = null;
    }


    public void setupRound(){
        //Firstly replace empty slots if required.
        maybeReplaceEmptySlots(this);

        //Check if the current round is a stalemate.
        if (isRoundStalemate(this)){
            //exit to failed game menu
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

    public Card[] getCardSlots() {
        return cardSlots;
    }

    public void setCardSlots(Card[] cardSlots) {
        this.cardSlots = cardSlots;
    }

    public Card[] getChosenCards() {
        return chosenCards;
    }

    public void setChosenCards(Card[] chosenCards) {
        this.chosenCards = chosenCards;
    }

    public Round getNextRound() {
        return nextRound;
    }

    public void setNextRound(Round nextRound) {
        this.nextRound = nextRound;
    }

}
