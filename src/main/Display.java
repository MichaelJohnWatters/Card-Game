package main;

import java.util.Scanner;

/**
 * This class is used to absract system.out from code blocks,
 * for readability.
 */
public class Display extends Colors {

    /**
     * Display Welcome message to the user
     */
    public static void  welcome(){
        System.out.println();
        System.out.println("Welcome to Elevens by Michael John Watters (b00751280) and Aaron Hoy's");
    }

    /**
     * Diplay main menu with the options:
     *   1) Play Elevens"
     *   2) Exit to desktop
     */
    public static void mainMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1) Play Elevens");
        System.out.println("2) Exit to desktop");
        enterInput();
    }

    /**
     * Diplay Game Menu with the options:
     *  1) Setup playable Elevens Game
     *  2) Demonstration Mode (computers plays the game)!
     *  3) Back to main menu"
     */
    public static void gameMenu() {
        System.out.println();
        System.out.println("Game Menu");
        System.out.println("1) Setup playable Elevens Game!");
        System.out.println("2) Demonstration Mode (computers plays the game)!");
        System.out.println("3) Back to main menu");
        enterInput();
    }

    /**
     * Display message if an exception is caught
     */
    public static void displayGameCrashed(){
        System.out.println("The Game Crashed return to main menu...");
    }

    /**
     * Display postgame menu
     * @param lastGame the last game
     */
    public static void displayPostGameMenu(Game lastGame) {
        String resultString = "";
        System.out.println(" --- Last Games Stats --- ");
        if(lastGame.getGameResult()) resultString = " Win !"; else  resultString= " Lost !";
        System.out.println("Result: " + resultString);
        System.out.println("Cards in deck: " + lastGame.getDeck().countNumberOfCards());
        System.out.println("Cards in play: " + lastGame.getCurrentRound().getCardsInPlayBag().countCards());
        System.out.println("Cards in discard deck: " + lastGame.getDiscardDeck().countNumberOfCards());
        System.out.println();
        System.out.println("Post Game Menu");
        System.out.println("1) Retry (play again)");
        System.out.println("2) Action Replay of the Last Games's Rounds!");
        System.out.println("3) Back to Game Men");
        enterInput();
    }

    /**
     * Display a round
     * 1) the round number
     * 2) the cards in play
     * 3) the input options legend
     * @param currentRound the current round.
     */
    public static void displayRound(Round currentRound){
        System.out.println();
        System.out.println("------------------------ Round " + currentRound.getRoundNumber() + " ------------------------");
        currentRound.getCardsInPlayBag().display(true);
        System.out.println();
        System.out.println("Input Options:");
        System.out.println("    hint - displays a hint about cards to pick.");
        System.out.println("    quit - quit to post game .");
        System.out.println("    valid card selection: a, b, c, d, e, f, g, h, i");
        System.out.println("    select 2 cards: 'ab' for Elevens pair, or 3 cards: 'abc' for face Pairs.");
    }

    /**
     * Display Computer/demonstration modes round.
     * @param currentRound current round
     */
    public static void displayAIRound(Round currentRound){
        System.out.println();
        System.out.println("------------------------ Round " + currentRound.getRoundNumber() + " ------------------------");
        currentRound.getCardsInPlayBag().display(true);
        System.out.println();
    }

    /**
     * Display setting up of game, for a human user.
     */
    public static void userPlayableGame() {
        System.out.println();
        System.out.println("Setting up game...");
        System.out.println("For a Human user...");
    }

    /**
     * Display setting up of game, for a non human user (demonstration mode).
     */
    public static void aiPlayableGame() {
        System.out.println();
        System.out.println("Setting up game...");
        System.out.println("For an AI to play and user to watch...");
    }

    /**
     * Displays an errors message and prompts the user they are going to return to the main menu
     */
    public static void errorExitingGame(){
        System.out.println("ERROR: an error occurred returning to main Menu...exiting game...");
    }

    /**
     * Display 2 Cards in text form
     * @param firstCard the first card
     * @param secondCard the second card
     * @param color the color of the text
     * @param prefixString any required prefix string example 'Cards Drawn:'
     */
    public static void displayTwoCards(Card firstCard, Card secondCard, String color, String prefixString){
        System.out.println(color + prefixString + " " + firstCard + " and " + secondCard + COLOR_WHITE);
    }

    /**
     * Display 3 Cards in text form
     * @param firstCard the first card
     * @param secondCard the second card
     * @param thirdCard the third card
     * @param color the color of the text
     * @param prefixString any required prefix string example 'Cards Drawn:'
     */
    public static void displayThreeCards(Card firstCard, Card secondCard, Card thirdCard, String color, String prefixString) {
        System.out.print(color + prefixString + " " + firstCard + ", " + secondCard + " and " + thirdCard + COLOR_WHITE);
    }

    /**
     * Display if the game is a stalemate and text prior to the last hand
     */
    public static void displayIsStalemate(){
        System.out.println(COLOR_RED + "Game is stalemate..\nYour last Hand was: \n" + COLOR_WHITE);
    }

    /**
     * Diplay win or lose output
     * @param gameResult the result of the game.
     * @param roundNumber the round number
     * @param isHuman is the user human, eg not in demo mode/computer plays mode.
     */
    public static void displayWinOrLoseOutPut(boolean gameResult, int roundNumber, boolean isHuman){
        if(gameResult){
            if(isHuman){
                System.out.println(COLOR_GREEN + "\nCongratz!! you have won this Game! in " + (roundNumber-1) + " rounds starting at 0 because we are programmers :)\n" + COLOR_WHITE);
            } else {
                System.out.println(COLOR_GREEN + "\nThe Computer has won this game! in " + (roundNumber-1) + " rounds starting at 0 because we are programmers :)\n" + COLOR_WHITE);
            }
        } else {
            if(isHuman){
                System.out.println(COLOR_RED + "\nSadly you have lost this Game, better luck next time!\n" + COLOR_WHITE);
            } else {
                System.out.println(COLOR_RED + "\nThe Computer has lost this Game, oh no!\n" + COLOR_WHITE);
            }
        }
        System.out.println(COLOR_RED +"press enter to continue to the post game menu..."+ COLOR_WHITE);
    }

    public static void returningToGameMenu(){
        System.out.println("Returning to Game Menu...");
    }

    public static void displayActionReplayOfLastGame(Game lastGame){
        Scanner keyPressScanner = new Scanner(System.in);
        System.out.println("\n------------------------------- Replay Round Number: " + lastGame.getRoundQueue().getFront().getRoundNumber() +"-------------------------------");

        //cards drawn this round.
        int drawn = lastGame.getRoundQueue().getFront().getRoundMemoryDrawCards().countCards();
        System.out.println(Colors.COLOR_GREEN +"Number of Drawn cards that round: " + drawn + ", cards drawn:" + Colors.COLOR_WHITE);

        //print the drawn cards from the rounds drawn card memory
        for (int i = 0; i < drawn; i++) {
            CardSlotsBag bag =  lastGame.getRoundQueue().getFront().getRoundMemoryDrawCards();
            String commaIfRequired = "";
            if(i == drawn-1){ commaIfRequired = " ";} else { commaIfRequired = ", ";}
            System.out.print(Colors.COLOR_RED + bag.cardAtPosition(i).toString() + commaIfRequired + Colors.COLOR_WHITE);
        }

        //print the discarded cards from the rounds discard card memory, these cards are cards that where successfully removed.
        int discarded = lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards().countCards();
        System.out.println(Colors.COLOR_GREEN + "\nNumber of discarded cards that round: " + discarded + ", discarded that round(successfully removed): " + Colors.COLOR_WHITE);

        //Print out the discarded cards.
        for (int i = 0; i < discarded; i++) {
            CardSlotsBag bag1 =  lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards();
            String commaIfRequired = "";
            if(i == discarded-1){ commaIfRequired = " ";} else { commaIfRequired = ", ";}
            System.out.print(Colors.COLOR_RED +" " + bag1.cardAtPosition(i).toString() + commaIfRequired + Colors.COLOR_WHITE);
        }

        //State of Cards on table at the end of the round
        System.out.println(Colors.COLOR_GREEN + "\nState of Cards in play at the end of the round, after discard cards where removed..." + Colors.COLOR_WHITE);
        lastGame.getRoundQueue().getFront().getCardsInPlayBag().display(false);

        //dequeue the round that's been displayed, as we no longer need it.
        lastGame.getRoundQueue().dequeue();

        //prompt and wait for input to go to the next round.
        System.out.println("\nPress any key to continue to the next replay round...");
        keyPressScanner.nextLine();
    }

    /**
     * Ask user for input
     */
    public static void enterInput() {
        System.out.println();
        System.out.print(COLOR_GREEN + "select option > " + COLOR_WHITE);
    }
    /**
     * Tell user there input was invalid and they should try again.
     */
    public static void invalidInput() {
        System.out.println();
        System.out.println(COLOR_RED + "Selected an Invalid Option....try again." +  COLOR_WHITE);
    }
}
