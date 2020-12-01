package main;

import java.util.Scanner;

public class Game extends Colors {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);
    private Scanner keyPressScanner = new Scanner(System.in);
    private boolean gameResult = false;

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();
        this.discardDeck = new Deck();
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    public boolean getGameResult(){
        return gameResult;
    }

    private static boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) return true; else return false;
    }

    //TODO AI plays game, user does key press for each round like we do below.
    public Game computerPlayableGame(){

        boolean playing = true;
        int roundNumber = 0;
        //boolean won = false;

        //Perform actions once per game here.
        Display.aiPlayableGame();

        //setup deck
        deck.createFullDeckOfCards();
        deck.shuffleRippleRandomRipple();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        currentRound = roundQueue.getFront();

        //Effectively each loop back to the top of the while(playing) is a new round.
        while(playing) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            //TODO remove ! when ready
            if (!currentRound.isStalemate()) {
                System.out.println("Game is stalemate..");
                gameResult = false;
                playing = false;
            }

            //TODO TEMP win condition for simulation
            if(roundNumber == 5){
                gameResult = true;
                playing = false;
            }

            //winning check, if still empty after attempting to draw cards, we have won.
            if (currentRound.getCardSlotBag().isEmpty()) {
                gameResult = true;
                playing = false;
            }

            //Display current round to terminal
            Display.displayRound(currentRound);

            //game is not a stalemate and we have not won, so allow user to select cards.
            String selectedCardsOrHint = "";

            //TODO change to AI selection
            if(currentRound.getCardSlotBag().containsElevensPair()){

                Card[] elevensPairArray = currentRound.getCardSlotBag().findAndReturnElevensPair();

                if(elevensPairArray != null){
                    System.out.print("AI has selected elevens pair:");
                    for (Card card: elevensPairArray) {
                        System.out.print(" " + card);
                        currentRound.getCardSlotBag().remove(card);
                    }
                }

            } else if (currentRound.getCardSlotBag().containsKingQueenJack()) {

                Card[] elevensFacePairsArray = currentRound.getCardSlotBag().findAndReturnKingQueenJackPair();

                if(elevensFacePairsArray != null){
                    System.out.print("AI has selected face card elevens pairs:");
                    for (Card card: elevensFacePairsArray) {
                        System.out.print(" " + card);
                        currentRound.getCardSlotBag().remove(card);
                    }
                }

            } else {
                //should never get hit but better to be safe
                //AI can't find a suitable selection to win the round so we lost the game.
                System.out.println(COLOR_RED + "The Impossible happened the AI could not find a suitable Win Scenario.....!" + COLOR_WHITE);
                gameResult = false;
                playing = false;
            }

            //TODO change to AI selection

            //if we get to this point the AI has made a round winning selection.
            //prepare and create the next round
            roundNumber++;
            CardSlotsBag copyOfBag = new CardSlotsBag(currentRound.getCardSlotBag().toArrayCopy());
            Round nextRound = new Round(roundNumber, copyOfBag);
            roundQueue.enqueue(nextRound);

            //set the current round to the next round, so when we loop to the top of the while we are in the correct round.
            currentRound = currentRound.getNextRound();

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            System.out.println("The AI has Won this round! I hope you learned something... press enter to continue...");
            keyPressScanner.nextLine();
        }

        if(gameResult){
            System.out.println();
            System.out.println(COLOR_GREEN + "Congratz!! you have won this Game! in " + (roundNumber-1) + " rounds starting at 0 because we are programmers :)" + COLOR_WHITE);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(COLOR_RED + "Sadly you have lost this Game, better luck next time!" + COLOR_WHITE);
            System.out.println();
        }

        System.out.println("press enter to continue to the post game menu...");
        keyPressScanner.nextLine();

        //return game to be passed other methods.
        return this;
    }

    public Game userPlayableGame() {
        boolean playing = true;
        int roundNumber = 0;

        //Perform actions once per game here.
        Display.userPlayableGame();

        //setup deck
        deck.createFullDeckOfCards();
        deck.shuffleRippleRandomRipple();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        currentRound = roundQueue.getFront();

        //Effectively each loop back to the top of the while(playing) is a new round.
        while(playing) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            //TODO remove ! when ready
            if (currentRound.isStalemate()) {
                System.out.println("Game is stalemate..");
                gameResult = false;
                playing = false;
                break;
            }

            //Display current round to terminal
            Display.displayRound(currentRound);

            //game is not a stalemate and we have not won, so allow user to select cards.
            boolean roundWinningSelection = false;
            String selectedCardsOrHint = "";

            while(!roundWinningSelection) {

                System.out.println(COLOR_GREEN + "please select a valid Elevens pair or pairs >" + COLOR_WHITE);
                selectedCardsOrHint = scanner.nextLine();

                //TODO remove out
                //if so display a hint
                if(askedForHint(selectedCardsOrHint)){
                    System.out.println(COLOR_GREEN + "Hint: " + COLOR_WHITE);
                    if(currentRound.getCardSlotBag().containsElevensPair()){
                        Card[] foundPair = currentRound.getCardSlotBag().findAndReturnElevensPair();
                        try {
                            for (Card card: foundPair) {
                                System.out.println(COLOR_RED + card + COLOR_WHITE);
                            }
                        } catch (Exception e){
                            Display.errorExitingGame();
                            gameResult =  false;
                            playing = false;
                            break;
                        }
                    } else if(currentRound.getCardSlotBag().containsKingQueenJack()) {
                        Card[] foundFacePairs = currentRound.getCardSlotBag().findAndReturnKingQueenJackPair();

                        // should never return null as we perform containsKingQueenJack() but added - try for safety.
                        try {
                            for (Card card: foundFacePairs) {
                                System.out.println(COLOR_RED + card + COLOR_WHITE);
                            }
                        } catch (Exception e){
                            Display.errorExitingGame();
                            gameResult =  false;
                            playing = false;
                            break;
                        }
                    } else { Display.errorExitingGame(); } // if we get here the game had no win condition but was not caught previously for some reasonn.
                    roundWinningSelection = false;
                } else if (GameMechanics.validStringSelection(selectedCardsOrHint)) {

                    if(selectedCardsOrHint.length() == 2) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                        //todo some checks here, do try catch and if fail force new cards to pick
                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));

                        System.out.println(COLOR_GREEN + "you selected : " + firstCard + " and " + secondCard + COLOR_WHITE);

                        if(GameMechanics.isElevensPair(firstCard, secondCard)){
                            //Valid selection we can now remove cards and move to next round
                            System.out.println(COLOR_GREEN + "Success! Your selected cards were a valid Elevens pair: " + firstCard + " and " + secondCard + COLOR_WHITE);

                            //remove the valid cards.
                            currentRound.getCardSlotBag().remove(firstCard);
                            currentRound.getCardSlotBag().remove(secondCard);

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            System.out.println(COLOR_RED + "Invalid Selection: Your selected cards were not a valid Elevens pair: " + firstCard + " and " + secondCard + COLOR_WHITE);
                            roundWinningSelection = false;
                        }

                    } else if (selectedCardsOrHint.length() == 3) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();
                        //todo some checks here
                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));
                        Card thirdCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[2]));

                        Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "You Selected: ");

                        if(GameMechanics.isFacePairs(firstCard, secondCard, thirdCard)){
                            //Valid selection we can now remove cards and move to next round
                            System.out.println(COLOR_GREEN + "Success! Your selected cards did contain a King, Queen and a Jack..." + COLOR_WHITE);
                            Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "");
                            //remove the valid cards.
                            currentRound.getCardSlotBag().remove(firstCard);
                            currentRound.getCardSlotBag().remove(secondCard);
                            currentRound.getCardSlotBag().remove(thirdCard);
                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            System.out.println(COLOR_RED + "Invalid Selection: Your select cards did not contain a King, Queen and Jack..." + COLOR_WHITE);
                            System.out.println(firstCard + ", " + secondCard + ", " + thirdCard);
                            roundWinningSelection = false;
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

            //winning check, if cardslotBag is empty and deck is empty we have won
            if (currentRound.getCardSlotBag().isEmpty() && deck.isEmpty()) {
                gameResult = true;
                playing = false; //TODO we either need this var or use while(true) and use breaks to exit.
                break;
            }

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            System.out.println("You have Won this round! press enter to continue...");
            keyPressScanner.nextLine();
        }

        if(gameResult){
            System.out.println();
            System.out.println(COLOR_GREEN + "Congratz!! you have won this Game! in " + (roundNumber-1) + " rounds starting at 0 because we are programmers :)" + COLOR_WHITE);
            System.out.println();
        } else {
            System.out.println();
            System.out.println(COLOR_RED + "Sadly you have lost this Game, better luck next time!" + COLOR_WHITE);
            System.out.println();
        }

        System.out.println("press enter to continue to the post game menu...");
        keyPressScanner.nextLine();

        //return game to be passed other methods.
        return this;
    }
}
