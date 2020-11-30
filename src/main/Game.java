package main;

import java.util.Scanner;

public class Game {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);
    private Scanner keyPressScanner = new Scanner(System.in);
    private boolean didWeWin = false;

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();
        this.discardDeck = new Deck();
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    public boolean getDidWeWin(){
        return didWeWin;
    }

    private static boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) return true; else return false;
    }

    public Game playGame() {

        boolean playing = true;
        int roundNumber = 0;
        boolean won = false;

        //Perform actions once per game here.
        Display.playGame();

        //shuffle deck
        deck.shuffleDeck();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        System.out.println("HERE AGAIN");
        currentRound = roundQueue.getFront();

        //Effectively each loop back to the top of the while(playing) is a new round.
        while(playing) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            //TODO remove ! when ready
            if (!currentRound.isStalemate()) {
                System.out.println("Game is stalemate..");
                won = false;
                playing = false;
            }

            //TODO TEMP win condition for simulation
            if(roundNumber == 5){
                won = true;
                didWeWin = won;
                playing = false;
            }

            //winning check, if still empty after attempting to draw cards, we have won.
            if (currentRound.getCardSlotBag().isEmpty()) {
                won = true;
                didWeWin = won;
                playing = false;
            }

            //Display current round to terminal
            Display.displayRound(currentRound);

            //game is not a stalemate and we have not won, so allow user to select cards.
            boolean roundWinningSelection = false;
            String selectedCardsOrHint = "";

            while(!roundWinningSelection) {
                System.out.println("please select a valid main.Elevens pair or pairs >");
                selectedCardsOrHint = scanner.nextLine();

                //if so display a hint
                if(askedForHint(selectedCardsOrHint)){
                    System.out.println("Hint: Hey fake hint here");
                    roundWinningSelection = false;
                } else if (GameMechanics.validStringSelection(selectedCardsOrHint)) {

                    if(selectedCardsOrHint.length() == 2) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                        //todo some checks here, do try catch and if fail force new cards to pick
                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));

                        System.out.println("picked: " + firstCard + " " + secondCard);

                        System.out.println("Result");
                        System.out.println(GameMechanics.isElevensPair(firstCard, secondCard));

                        if(GameMechanics.isElevensPair(firstCard, secondCard)){
                            //Valid selection we can now remove cards and move to next round
                            System.out.println("Success! Your selected cards were a valid Elevens pair: " + firstCard + " and " + secondCard);

                            //remove the valid cards.
//                                currentRound.getCardSlotBag().remove(firstCard);
//                                currentRound.getCardSlotBag().remove(secondCard);
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            System.out.println("Invalid Selection: Your selected cards were not a valid Elevens pair: " + firstCard + " and " + secondCard);
                            //TODO SIMULATION should be false, temp for testing removes also remove removes
                            //remove the valid cards.
//                                currentRound.getCardSlotBag().remove(firstCard);
//                                currentRound.getCardSlotBag().remove(secondCard);
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();
                            roundWinningSelection = true;
                        }

                    } else if (selectedCardsOrHint.length() == 3) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();
                        //todo some checks here
                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));
                        Card thirdCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[2]));

                        System.out.println("Result");
                        System.out.println(GameMechanics.isFacePairs(firstCard, secondCard, thirdCard));

                        if(GameMechanics.isFacePairs(firstCard, secondCard, thirdCard)){
                            //Valid selection we can now remove cards and move to next round
                            System.out.println("Success! Your select cards did contain a King, Queen and a Jack...");
                            System.out.println(firstCard + "and " + secondCard);
                            //remove the valid cards.
//                                currentRound.getCardSlotBag().remove(firstCard);
//                                currentRound.getCardSlotBag().remove(secondCard);
//                                currentRound.getCardSlotBag().remove(thirdCard);
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();
                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            System.out.println("Invalid Selection: Your select cards did not contain a King, Queen and Jack...");
                            System.out.println(firstCard + ", " + secondCard + ", " + thirdCard);

                            //TODO SIMULATION should be false, temp for testing and removes and remove removes
                            //remove the valid cards.
//                                currentRound.getCardSlotBag().remove(firstCard);
//                                currentRound.getCardSlotBag().remove(secondCard);
//                                currentRound.getCardSlotBag().remove(thirdCard);
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();
                            currentRound.getCardSlotBag().remove();
                            roundWinningSelection = true;
                        }
                    }

                }
            }

            //if we get to this point the user has made a round winning selection.
            //prepare and create the next round
            roundNumber++;
            CardSlotsBag copyOfBag = new CardSlotsBag(currentRound.getCardSlotBag().toArrayCopy());
            Round nextRound = new Round(roundNumber, copyOfBag);
            roundQueue.enqueue(nextRound);

            //set the current round to the next round, so when we loop to the top of the while we are in the correct round.
            currentRound = currentRound.getNextRound();

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            System.out.println("You have Won this round! press enter to continue...");
            keyPressScanner.nextLine();
        }

        didWeWin = won; //TODO thinnk about implementation, not realy nessary is duplication
        System.out.println("Game over result: " + won);
        System.out.println("press enter to continue to the post game menu...");
        keyPressScanner.nextLine();

        //return game to be passed other methods.
        return this;
    }
}
