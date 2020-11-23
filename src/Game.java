import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);

    //Max drawn cards for a round will be 9.
    private Card[] drawnCards = new Card[9];

    //Max selected cards for a round will be 3
    private Card[] selectedCards = new Card[3];

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();  //create deck of 52 card objects.
        this.discardDeck = new Deck(); //empty deck.
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    //TODO work out if we can acctually use set or have to make our own
    public boolean validCharSelection(String input){

        Set<Character> allowedCharValues = Set.of('a','b','c','d','e','f', 'g','h', 'i');

        Set<Character> inputSet = Set.of('a', 'b');

        return allowedCharValues.containsAll(inputSet);



        //char[] inputAsCharArray = input.toCharArray();

//        HashSet<Character> validCharInputs = new HashSet<Character>();
//        validCharInputs.add('a');
//        validCharInputs.add('b');
//        validCharInputs.add('c');
//        validCharInputs.add('d');
//        validCharInputs.add('e');
//        validCharInputs.add('f');
//        validCharInputs.add('g');
//        validCharInputs.add('h');
//        validCharInputs.add('i');

    }

    public boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) {
            return true;
        }
        else return false;
    }

    //play the game then return the game.
    public Game playGame() {
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
                System.out.println();
                System.out.println(" -------- Round " + currentRound.getRoundNumber() + " --------" );

                //TODO show current Slots
                currentRound.getCardSlotBag().display();
                System.out.println("Options");
                System.out.println("    hint - displays a hint about cards to pick.");
                System.out.println("    forfeit - forfeit to post game .");
                System.out.println("    'ab', 'abc' or other valid selection.");
                System.out.println("please select >");

                String input = "";
                boolean validInputSelection = false;

                while(!validInputSelection) {
                    //get input
                    input = scanner.nextLine();

                    //check if the user input 'hint' for a hint.
                    //if so display a hint
                    if(askedForHint(input)){
                        System.out.println("Hint: Hey fake hint here");
                        validInputSelection = true;
                    } else if (validCharSelection(input)) {
                        System.out.println("Yeo valid");
                        validInputSelection = true;
                        //then check if the chosen chars are pairs or face pairs.

                    }

                }

            }
        }
        return this;
    }
}
