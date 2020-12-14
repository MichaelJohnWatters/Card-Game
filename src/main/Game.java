package main;

import java.util.Scanner;

/**
 * This Class Represents a Game, holding all the required components to play a game.
 * Including every round with memory of actions perform in the round.
 * The result of the game and the Deck and the discard deck.
 */
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
        this.roundQueue = null;
    }

    /**
     * Checks if the input string equals 'hint'
     *
     * @param input the input string
     * @return boolean true if equals 'hint' or false if not
     */
    private static boolean askedForHint(String input) {
        return input.toLowerCase().equals("hint");
    }

    /**
     * Checks if the input string equals 'quit'
     *
     * @param input the input string
     * @return boolean true if equals 'quit' or false if not
     */
    private static boolean askedToForfeit(String input) {
        return input.toLowerCase().equals("quit");
    }

    /**
     * Get playable Deck
     *
     * @return Deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Get the discard Deck, eg the deck of cards that where successfully removed.
     *
     * @return the discard deck
     */
    public Deck getDiscardDeck() {
        return discardDeck;
    }

    /**
     * Return the Round Queue holding every Round.
     *
     * @return the round queue
     */
    public RoundQueue getRoundQueue() {
        return roundQueue;
    }

    /**
     * Get the current Round
     *
     * @return returns the current round
     */
    public Round getCurrentRound() {
        return currentRound;
    }

    /**
     * get the game result either win(true) or lose(false)
     *
     * @return boolean game result
     */
    public boolean getGameResult() {
        return gameResult;
    }

    /**
     * This method allows the Computer to play the game, also know as demonstration mode.
     * Provides automatic Card Selection, all user has to to is prompt the Computer to continue to each round.
     *
     * @return Game
     */
    public Game computerDemonstrationGame() {
        int roundNumber = 0;

        //Perform actions once per game here.
        Display.aiPlayableGame();

        //setup deck
        deck.createFullDeckOfCards();
        deck.rigorousShuffle();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        currentRound = roundQueue.getFront();

        //Each loop is a new round.
        //This loop is only broken if we win or lose or quit, in which we exit with break.
        while (true) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            if (currentRound.isStalemate()) {

                //display isStalemate system.out
                Display.displayIsStalemate();
                Display.failedAtRound(currentRound.getRoundNumber());

                // if is statement display last hand for the user to see
                System.out.println(COLOR_RED + "last cards in play: " + COLOR_WHITE);
                currentRound.getCardsInPlayBag().display(false);

                gameResult = false;
                break;
            }

            //Display current round to terminal
            Display.displayAIRound(currentRound);

            //Hint for player's benefit
            System.out.println(COLOR_GREEN + "Hint for Player's benefit: " + COLOR_WHITE);
            if (currentRound.getCardsInPlayBag().containsElevensPair()) {
                Card[] foundPair = currentRound.getCardsInPlayBag().findAndReturnElevensPair();
                try {
                    for (Card card : foundPair) {
                        System.out.println(COLOR_RED + " - " +card + COLOR_WHITE);
                    }
                } catch (Exception e) {
                    Display.errorExitingGame();
                    gameResult = false;
                    break;
                }
            } else if (currentRound.getCardsInPlayBag().containsKingQueenJack()) {
                Card[] foundFacePairs = currentRound.getCardsInPlayBag().findAndReturnKingQueenJackPair();
                try {
                    for (Card card : foundFacePairs) { // will never return null as we perform containsKingQueenJack();
                        System.out.println(COLOR_RED + card + COLOR_WHITE);
                    }
                } catch (Exception e) {
                    Display.errorExitingGame();
                    gameResult = false;
                    break;
                }
            }

            if (currentRound.getCardsInPlayBag().containsElevensPair()) {

                Card[] elevensPairArray = currentRound.getCardsInPlayBag().findAndReturnElevensPair();

                if (elevensPairArray != null) {
                    System.out.println(COLOR_GREEN + "AI has selected elevens pair:" + COLOR_WHITE);
                    for (Card card : elevensPairArray) {
                        System.out.println(" - " + card);
                        discardDeck.push(currentRound.getCardsInPlayBag().remove(card));
                        currentRound.updateDiscardCardMemory(card);
                    }
                    System.out.println();
                }

            } else if (currentRound.getCardsInPlayBag().containsKingQueenJack()) {

                Card[] elevensFacePairsArray = currentRound.getCardsInPlayBag().findAndReturnKingQueenJackPair();

                if (elevensFacePairsArray != null) {
                    System.out.println(COLOR_GREEN + "AI has selected face card elevens pairs:" + COLOR_WHITE);
                    for (Card card : elevensFacePairsArray) {
                        System.out.print(" " + card);
                        discardDeck.push(currentRound.getCardsInPlayBag().remove(card));
                        currentRound.updateDiscardCardMemory(card);
                    }
                }
            } else {
                //should never get hit but better to be safe
                //AI can't find a suitable selection to win the round so we lost the game.
                System.out.println(COLOR_RED + "The Impossible happened the AI could not find a suitable Win Scenario.....!" + COLOR_WHITE);
                gameResult = false;
                break;
            }

            //if we get to this point the user has made a round winning selection.

            //winning check, if cardslotBag is empty and deck is empty we have won
            if (currentRound.getCardsInPlayBag().isEmpty() && deck.isEmpty()) {
                gameResult = true;
                break;
            }

            //prepare and create the next round
            roundNumber++;
            CardSlotsBag copyOfBag = new CardSlotsBag(currentRound.getCardsInPlayBag().toArrayCopy());
            Round nextRound = new Round(roundNumber, copyOfBag);
            roundQueue.enqueue(nextRound);

            //set the current round to the next round, so when we loop to the top of the while we are in the correct round.
            currentRound = currentRound.getNextRound();

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            System.out.println("\nThe AI has won this round! press enter to continue...");
            keyPressScanner.nextLine();
        }

        //print out win or lose message and prompt to return to post game menu.
        Display.displayWinOrLoseOutPut(gameResult, roundNumber, false);

        keyPressScanner.nextLine();

        //return game to be passed other methods.
        return this;
    }

    /**
     * This Method allows the user to play the Elevens Game
     * They well select valid selections until the game is either lost or won.
     * Game will automatically end, if the player wins or loses.
     *
     * @return Game
     */
    public Game userPlayableGame() {
        boolean playing = true;
        int roundNumber = 0;

        //Perform actions once per game here.
        Display.userPlayableGame();

        //setup deck and shuffle
        deck.createFullDeckOfCards();
        deck.rigorousShuffle();

        //create first round, add to round queue.
        Round firstRound = new Round(0);

        //place the first round in RoundQueue
        roundQueue = new RoundQueue();
        roundQueue.enqueue(firstRound);

        //set the current round.
        currentRound = roundQueue.getFront();

        //Effectively each loop back to the top of the while(playing) is a new round.
        while (playing) {

            //Try replace empty slots with new card from the top of the deck.
            currentRound.replaceEmptyCardSlots(deck);

            //stalemate check
            if (currentRound.isStalemate()) {
                //display isStalemate system.out
                Display.displayIsStalemate();
                Display.failedAtRound(currentRound.getRoundNumber());
                currentRound.getCardsInPlayBag().display(true);
                gameResult = false;
                break;
            }

            //Display current round to terminal
            Display.displayRound(currentRound);

            //game is not a stalemate and we have not won, so allow user to select cards.
            boolean roundWinningSelection = false;
            String selectedCardsOrHint = "";

            while (!roundWinningSelection) {

                System.out.println(COLOR_GREEN + "please select a valid Elevens pair or pairs >" + COLOR_WHITE);

                selectedCardsOrHint = scanner.nextLine();

                //if they asked for a hint, workout a valid selection
                if (askedForHint(selectedCardsOrHint)) {

                    System.out.println(COLOR_GREEN + "Hint: " + COLOR_WHITE);

                    if (currentRound.getCardsInPlayBag().containsElevensPair()) {

                        Card[] foundPair = currentRound.getCardsInPlayBag().findAndReturnElevensPair();

                        try {
                            for (Card card : foundPair) {
                                System.out.println(COLOR_RED + card + COLOR_WHITE);
                            }
                        } catch (Exception e) {
                            Display.errorExitingGame();
                            gameResult = false;
                            playing = false;
                            break;
                        }

                    } else if (currentRound.getCardsInPlayBag().containsKingQueenJack()) {

                        Card[] foundFacePairs = currentRound.getCardsInPlayBag().findAndReturnKingQueenJackPair();

                        try {
                            for (Card card : foundFacePairs) { // will never return null as we perform containsKingQueenJack() before.
                                System.out.println(COLOR_RED + card + COLOR_WHITE);
                            }
                        } catch (Exception e) {
                            Display.errorExitingGame();
                            gameResult = false;
                            playing = false;
                            break;
                        }
                    } else {
                        Display.errorExitingGame();
                    } // if we get here the game had no win condition but was not caught previously for some reasonn.
                    roundWinningSelection = false;
                } else if (askedToForfeit(selectedCardsOrHint)) {
                    System.out.println("forfeiting current game.....");
                    gameResult = false;
                    playing = false;
                    break;
                } else if (GameMechanics.validStringSelection(selectedCardsOrHint)) {

                    if (selectedCardsOrHint.length() == 2) {

                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                        Card firstCard = currentRound.getCardsInPlayBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardsInPlayBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));

                        Display.displayTwoCards(firstCard, secondCard, COLOR_GREEN, "\nYou Selected: ");

                        if (GameMechanics.isElevensPair(firstCard, secondCard)) {

                            //Valid selection we can now remove cards and move to next round
                            Display.displayTwoCards(firstCard, secondCard, Colors.COLOR_GREEN, "\nValid Selection! Your selected cards were a valid Elevens pair: ");

                            //remove the valid cards.
                            discardDeck.push(currentRound.getCardsInPlayBag().remove(firstCard));
                            discardDeck.push(currentRound.getCardsInPlayBag().remove(secondCard));

                            //update round memory for replay feature
                            currentRound.updateDiscardCardMemory(firstCard);
                            currentRound.updateDiscardCardMemory(secondCard);

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            Display.displayTwoCards(firstCard, secondCard, Colors.COLOR_RED, "\nInvalid Selection: Your select cards were not a valid Elevens pair... ");
                            roundWinningSelection = false;
                        }

                    } else if (selectedCardsOrHint.length() == 3) {
                        char[] selectedCards = selectedCardsOrHint.toLowerCase().toCharArray();

                        Card firstCard = currentRound.getCardsInPlayBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[0]));
                        Card secondCard = currentRound.getCardsInPlayBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[1]));
                        Card thirdCard = currentRound.getCardsInPlayBag().cardAtPosition(GameMechanics.cardSelectionCharToInt(selectedCards[2]));

                        Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "\nYou Selected 3 face cards: ");

                        if (GameMechanics.isFacePairs(firstCard, secondCard, thirdCard)) {

                            //Valid selection we can now remove cards and move to next round
                            Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_GREEN, "\nValid Selection! Your selected cards contained a King, Queen and a Jack...");

                            //remove the valid cards.
                            discardDeck.push(currentRound.getCardsInPlayBag().remove(firstCard));
                            discardDeck.push(currentRound.getCardsInPlayBag().remove(secondCard));
                            discardDeck.push(currentRound.getCardsInPlayBag().remove(thirdCard));

                            //update round memory for replay feature
                            currentRound.updateDiscardCardMemory(firstCard);
                            currentRound.updateDiscardCardMemory(secondCard);
                            currentRound.updateDiscardCardMemory(thirdCard);

                            roundWinningSelection = true;
                        } else {
                            //invalid selection, prompt to try again
                            Display.displayThreeCards(firstCard, secondCard, thirdCard, Colors.COLOR_RED, "\nInvalid Selection: Your select cards did not contain a King, Queen and Jack... ");
                            System.out.println(firstCard + ", " + secondCard + ", " + thirdCard);
                            roundWinningSelection = false;
                        }
                    }
                }
            }

            //winning check, if cardslotBag is empty and deck is empty we have won
            if (currentRound.getCardsInPlayBag().isEmpty() && deck.isEmpty()) {
                gameResult = true;
                break;
            }

            //if we get to this point the user has made a round winning selection.
            //prepare and create the next round
            roundNumber++;
            CardSlotsBag copyOfBag = new CardSlotsBag(currentRound.getCardsInPlayBag().toArrayCopy());
            Round nextRound = new Round(roundNumber, copyOfBag);
            roundQueue.enqueue(nextRound);

            //set the current round to the next round, so when we loop to the top of the while we are in the correct round.
            currentRound = currentRound.getNextRound();

            //prompt to key press to continue, prevents user confusion, user can except what will happen
            if (playing) {
                System.out.println("\nYou have Won this round! press enter to continue...");
            }
            keyPressScanner.nextLine();
        }

        //print out win or lose message and prompt to return to post game menu.
        Display.displayWinOrLoseOutPut(gameResult, roundNumber, true);

        //wait for key press
        keyPressScanner.nextLine();

        return this;
    }
}
