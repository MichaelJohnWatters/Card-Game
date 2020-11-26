
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;

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


    //TODO check for allready selected chars
    private static boolean validStringSelection(String input) {
        boolean valid = true;

        //if the input is greater than 2 but less than 3, check if characters selected are allow.
        if(input.length() > 1 && input.length() < 4){
            char[] inputAsCharArray = input.toLowerCase().toCharArray();
            //char[] alreadyAskedFor = new char[9];

            //for each character in input check if it is not an allow character.
            //if so valid = false.
            for (char character : inputAsCharArray) {
                if (!allowedCharacter(character)) {
                    System.out.println(character + " is NOT allowed..");
                    valid = false;
                    break;
                }
                System.out.println(character + " is allowed..");
            }

        } else { valid = false; }
        return valid;
    }

    private static boolean allowedCharacter(char letter) {
        char[] allowedChars = {'a','b','c','d','e','f','g','i'};
        boolean contains = false;

        for (char character : allowedChars) {
            if (character == letter) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    private static boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) return true; else return false;
    }

    //play the game then return the game.
    public Game playGame() {
        boolean playing = true;
        boolean won = false;

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
            //TODO REMOVE ! from  if (!currentRound.isStalemate())
            if (!currentRound.isStalemate()) {
                System.out.println("Game is stalemate");
                playing = false;
            } else {
                System.out.println();
                System.out.println(" -------- Round " + currentRound.getRoundNumber() + " --------" );
                currentRound.getCardSlotBag().display();
                System.out.println();
                System.out.println("Input Options:");
                System.out.println("    hint - displays a hint about cards to pick.");
                System.out.println("    forfeit - forfeit to post game .");
                System.out.println("    select 2 cards: 'ab' for Elevens pair, or 3 cards: 'abc' for face Pairs.");

                String selectedCardsOrHint = "";
                boolean validInputSelection = false;

                while(!validInputSelection) {
                    System.out.println("please select a valid pair or pairs >");
                    selectedCardsOrHint = scanner.nextLine();

                    //if so display a hint
                    if(askedForHint(selectedCardsOrHint)){
                        System.out.println("Hint: Hey fake hint here");
                        validInputSelection = false;
                    } else if (validStringSelection(selectedCardsOrHint)) {
                        System.out.println("Yeo valid input for letters");
                        validInputSelection = true;
                    }
                }

                if(selectedCardsOrHint.length() == 2) {
                    //find those 2 cards


                    //GameMechanics.isElevensPair();


                } else if (selectedCardsOrHint.length() == 3) {
                    //find those 3 cards

                    //GameMechanics.isFacePairs();

                }

            }
        }
        return this;
    }
}
