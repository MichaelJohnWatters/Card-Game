package main;

import java.util.Scanner;

//TODO JAVA DOC
// TODO TESTS
public class Game extends Colors {

    private Deck deck;
    private Deck discardDeck;
    private RoundQueue roundQueue;
    private Round currentRound;
    private Scanner scanner = new Scanner(System.in);
    private Scanner keyPressScanner = new Scanner(System.in);
    private boolean gameResult = false;

    public Deck getDeck() {
        return deck;
    }

    public Deck getDiscardDeck() {
        return discardDeck;
    }

    public RoundQueue getRoundQueue() {
        return roundQueue;
    }

    /**
     * This constructor will consist all the components required to play a game.
     */
    public Game() {
        this.deck = new Deck();
        this.discardDeck = new Deck();
        this.roundQueue = null;  //Will be assigned a value in the Play method for readability.
    }

    public Game computerPlayableGame(){
        boolean playing = true;
        int roundNumber = 0;

        //Perform actions once per game here.
        Display.aiPlayableGame();

        //setup deck
        deck.createFullDeckOfCards();
        deck.rigourousShuffle();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        currentRound = roundQueue.getFront();

        //Effectively each loop back to the top of the while(playing) is a new round.
        while(true) {
        //while(playing) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            if (currentRound.isStalemate()) {

                //display isStalemate system.out
                Display.displayIsStalemate();
                currentRound.getCardSlotBag().display();

                gameResult = false;
                //playing = false;
                break;
            }

            //Display current round to terminal
            Display.displayAIRound(currentRound);

            //Hint for player's benefit
            System.out.println(COLOR_GREEN + "Hint for Player's benefit: " + COLOR_WHITE);
            if(currentRound.getCardSlotBag().containsElevensPair()){
                Card[] foundPair = currentRound.getCardSlotBag().findAndReturnElevensPair();
                try {
                    for (Card card: foundPair) {
                        System.out.println(COLOR_RED + card + COLOR_WHITE);
                    }
                } catch (Exception e){
                    Display.errorExitingGame();
                    gameResult =  false;
                    //playing = false;
                    break;
                }
            } else if(currentRound.getCardSlotBag().containsKingQueenJack()) {
                Card[] foundFacePairs = currentRound.getCardSlotBag().findAndReturnKingQueenJackPair();

                try {
                    for (Card card: foundFacePairs) { // should never return null as we perform containsKingQueenJack() but added - try for safety.
                        System.out.println(COLOR_RED + card + COLOR_WHITE);
                    }
                } catch (Exception e){
                    Display.errorExitingGame();
                    gameResult =  false;
                    //playing = false;
                    break;
                }
            }

            if(currentRound.getCardSlotBag().containsElevensPair()) {

                Card[] elevensPairArray = currentRound.getCardSlotBag().findAndReturnElevensPair();

                if(elevensPairArray != null){
                    System.out.println(COLOR_GREEN + "AI has selected elevens pair:" + COLOR_WHITE);
                    for (Card card: elevensPairArray) {
                        System.out.print(" " + card);
                        discardDeck.push(currentRound.getCardSlotBag().remove(card));
                    }
                    System.out.println();
                }

            } else if (currentRound.getCardSlotBag().containsKingQueenJack()) {

                Card[] elevensFacePairsArray = currentRound.getCardSlotBag().findAndReturnKingQueenJackPair();

                if(elevensFacePairsArray != null){
                    System.out.println(COLOR_GREEN +"AI has selected face card elevens pairs:" + COLOR_WHITE);
                    for (Card card: elevensFacePairsArray) {
                        System.out.print(" " + card);
                        discardDeck.push(currentRound.getCardSlotBag().remove(card));
                    }
                }
            } else {
                //should never get hit but better to be safe
                //AI can't find a suitable selection to win the round so we lost the game.
                System.out.println(COLOR_RED + "The Impossible happened the AI could not find a suitable Win Scenario.....!" + COLOR_WHITE);
                gameResult = false;
                //playing = false;
                break;
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
                //playing = false;
                break;
            }

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            System.out.println("The AI has won this round! press enter to continue...");
            keyPressScanner.nextLine();
        }

        //print out win or lose message and prompt to return to post game menu.
        Display.displayWinOrLoseOutPut(gameResult, roundNumber,false);

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
        deck.rigourousShuffle();

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
            if (currentRound.isStalemate()) {
                //display isStalemate system.out
                Display.displayIsStalemate();
                currentRound.getCardSlotBag().display();
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

                //if they asked for a hint, workout a valid selection
                if(askedForHint(selectedCardsOrHint)){

                    System.out.println(COLOR_GREEN + "Hint: " + COLOR_WHITE);

                    if(currentRound.getCardSlotBag().containsElevensPair()) {

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

                        try {
                            for (Card card: foundFacePairs) { // will never return null as we perform containsKingQueenJack() before.
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
                }
                else if(askedToForfeit(selectedCardsOrHint)) {
                    System.out.println("forfeiting current game.....");
                    gameResult = false;
                    roundWinningSelection = true;
                    playing = false;
                    break;
                }
                else if (GameMechanics.validStringSelection(selectedCardsOrHint)) {

                    if(selectedCardsOrHint.length() == 2) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));

                        System.out.println(COLOR_GREEN + "you selected : " + firstCard + " and " + secondCard + COLOR_WHITE);

                        if(GameMechanics.isElevensPair(firstCard, secondCard)) {

                            //Valid selection we can now remove cards and move to next round
                            Display.displayTwoCards(firstCard, secondCard, Colors.COLOR_GREEN,"Success! Your selected cards were a valid Elevens pair: ");

                            //remove the valid cards.
                            discardDeck.push(currentRound.getCardSlotBag().remove(firstCard));
                            discardDeck.push(currentRound.getCardSlotBag().remove(secondCard));

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            Display.displayTwoCards(firstCard, secondCard, Colors.COLOR_RED,"Invalid Selection: Your select cards were not a valid Elevens pair... ");
                            roundWinningSelection = false;
                        }

                    } else if (selectedCardsOrHint.length() == 3) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();
                        //todo some checks here
                        Card firstCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));
                        Card thirdCard  = currentRound.getCardSlotBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[2]));

                        Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "You Selected: ");

                        if(GameMechanics.isFacePairs(firstCard, secondCard, thirdCard)) {

                            //Valid selection we can now remove cards and move to next round
                            Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "Success! Your selected cards did contain a King, Queen and a Jack...");

                            //remove the valid cards.
                            discardDeck.push(currentRound.getCardSlotBag().remove(firstCard));
                            discardDeck.push(currentRound.getCardSlotBag().remove(secondCard));
                            discardDeck.push(currentRound.getCardSlotBag().remove(thirdCard));

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_RED,"Invalid Selection: Your select cards did not contain a King, Queen and Jack... ");
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

        //print out win or lose message and prompt to return to post game menu.
        Display.displayWinOrLoseOutPut(gameResult, roundNumber, true);

        keyPressScanner.nextLine();

        //return game to be passed other methods.
        return this;
    }

    public boolean getGameResult(){
        return gameResult;
    }

    private static boolean askedForHint(String input){
        if (input.toLowerCase().equals("hint")) return true; else return false;
    }

    private static boolean askedToForfeit(String input){
        if (input.toLowerCase().equals("quit")) return true; else return false;
    }
}
