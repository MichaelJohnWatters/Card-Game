import java.util.Scanner;

public class Game {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();  //create deck of 52 card objects.
        this.discardDeck = new Deck(); //empty deck.
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    public boolean validGameInput(String input){
        if(input.length() < 4 && input.length() > 0) return true;
        else return true;
    }

    public boolean playGame() {


        boolean playing = true;

        Display.playGame();

        //In a new Game we must shuffle the cards.
        deck.shuffleDeck();

        //instantiate the roundQueue and first Round.
        this.roundQueue = new RoundQueue(new Round());

        //set the current Round, to the first Round
        currentRound = roundQueue.getFront();

        //each iteration will represent the start of a new round so we must.
        while(playing) {

            //replace any empty slots, as cards are removed, from previous rounds.
            currentRound.replaceEmptyCardSlots(deck);

            //Must check if the current round can be completed, after replacing empty slots.
            if (currentRound.isStalemate()) {
                System.out.println("Game is stalemate");

                playing = false;
            } else {
                System.out.println(" ******* Round " + currentRound.getRoundNumber() + "*******" );
                System.out.println("    type hint for a valid move suggestion.....");
                System.out.println("    type exit to forfeit the game.....");

                //TODO show current Slots
                currentRound.getCardSlotBag().display();

                System.out.println("please select (example: 'abc' or 'ab') >");

                String userSelection = scanner.nextLine();

                if(validGameInput(userSelection)){

                } else {
                    //keep asking
                }


                //check if selection is valid





                playing = false;
            }
        }
        return true;
    }
}
