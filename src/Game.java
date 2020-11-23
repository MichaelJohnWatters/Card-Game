public class Game {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();  //create deck of 52 card objects.
        this.discardDeck = new Deck(); //empty deck.
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    //once a games components have been created.
    //we can begin applying the games rule set.
    public void playGame() {
        boolean playing = true;

        Display.playGame();

        //In a new Game we must shuffle the cards.
        deck.shuffleDeck();

        //instantiate the roundQueue and first Round.
        this.roundQueue = new RoundQueue(new Round());

        //set the current Round, to the first Round
        currentRound = roundQueue.getFront();

        currentRound.replaceEmptyCardSlots();
        if (currentRound.isStalemate(currentRound.getCardSlotBag())) {
            System.out.println("Game is stalemate");
        } else {
            System.out.println("Game is not stalemate");
        }
    }
}
