import java.util.Scanner;

public class Game {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);
    private Scanner keyPressScanner = new Scanner(System.in);
    private boolean didWeWin = false;

    //TODO getters and setters

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();  //create deck of 52 card objects.
        this.discardDeck = new Deck(); //empty deck.
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    private static boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) return true; else return false;
    }

    public Game playGame(){
        boolean playing = true;
        int round = 0;
        boolean won = false;

        //Perform actions once per game here.
        Display.playGame();

        //shuffle deck
        deck.shuffleDeck();

        //create first round, add to round queue.
        this.roundQueue = new RoundQueue(new Round());

        //set the current round.
        currentRound = roundQueue.getFront();
        
        //Effectively each loop back to the top of the while(playing) is a new round.
        while(playing){
            boolean inRound = true;

            while (inRound){

                System.out.println("Started round:  " + round);

                //draw cards
                System.out.println("drawning cards..");
                currentRound.getCardSlotBag().remove();
                currentRound.replaceEmptyCardSlots(deck);

                //stalemate check
                //TODO remove ! when ready
                if (!currentRound.isStalemate()) {
                    System.out.println("Game is stalemate..");
                    won = false;
                    playing = false;
                }

                //winning check, we still empty after drawing we have won.
                if (currentRound.getCardSlotBag().isEmpty()) {
                    won = true;
                    playing = false;
                }

                //display round
                currentRound.setRoundNumber(round);
                System.out.println();
                System.out.println(" -------- Round " + currentRound.getRoundNumber() + " --------" );
                currentRound.getCardSlotBag().display();
                System.out.println();
                System.out.println("Input Options:");
                System.out.println("    hint - displays a hint about cards to pick.");
                System.out.println("    forfeit - forfeit to post game .");
                System.out.println("    select 2 cards: 'ab' for Elevens pair, or 3 cards: 'abc' for face Pairs.");

                //game is not a stalemate and we have not won, so allow user to select cards.

                boolean roundWinningSelection = false;
                String selectedCardsOrHint = "";

                while(!roundWinningSelection) {
                    System.out.println("please select a valid Elevens pair or pairs >");
                    selectedCardsOrHint = scanner.nextLine();

                    //if so display a hint
                    if(askedForHint(selectedCardsOrHint)){
                        System.out.println("Hint: Hey fake hint here");
                        roundWinningSelection = false;
                    } else if (GameMechanics.validStringSelection(selectedCardsOrHint)) {


                        if(selectedCardsOrHint.length() == 2) {
                            char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                            //todo some checks here, do try catch and if fail force new cards to pick
                            Card firstCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                            Card secondCard =currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));

                            System.out.println("Result");
                            System.out.println(GameMechanics.isElevensPair(firstCard,secondCard));

                            if(GameMechanics.isElevensPair(firstCard,secondCard)){
                                //Valid selection we can now remove cards and move to next round
                                System.out.println("Success! Your selected cards were a valid Elevens pair: " + firstCard + " and " + secondCard);
                                roundWinningSelection = true;
                            } else {
                                //invalid selection, prompt to try again
                                System.out.println("Invalid Selection: Your selected cards were not a valid Elevens pair: " + firstCard + " and " + secondCard);
                                //TODO should be false, temp for testing
                                roundWinningSelection = true;
                            }


                        } else if (selectedCardsOrHint.length() == 3) {

                            char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();
                            //todo some checks here
                            Card firstCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                            Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));
                            Card thirdCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[2]));

                            System.out.println("Result");
                            System.out.println(GameMechanics.isFacePairs(firstCard,secondCard,thirdCard));

                            if(GameMechanics.isFacePairs(firstCard, secondCard, thirdCard)){
                                //Valid selection we can now remove cards and move to next round
                                System.out.println("Success! Your select cards did contain a King, Queen and a Jack..");
                                System.out.println(firstCard + "and " + secondCard);
                                roundWinningSelection = true;
                            } else {
                                //invalid selection, prompt to try again
                                System.out.println("Invalid Selection: Your select cards did not contain a King, Queen and Jack..");
                                System.out.println(firstCard + ", " + secondCard + ", " + thirdCard);
                                //TODO should be false, temp for testing
                                roundWinningSelection = true;
                            }
                        }

                    }
                }

                //end round
                System.out.println("You have Won this round! press enter to continue...");
                keyPressScanner.nextLine();
                round++;
                inRound = false; //todo think around not doing this, could maybe remove, seems pointless now i think about it
            }

        }

        System.out.println("Game over result: " + won);
        didWeWin = won;
        return this; //return the Game to be used by menus
    }

}
