package main;

//TODO JAVA DOC/commennts
public class Display extends Colors {

    public static void  welcome(){
        System.out.println();
        System.out.println("Welcome to Michael Watters and Aaron Hoy's Assignment 2 - Elevens ");
    }

    public static void mainMenu() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("1) Play Elevens");
        System.out.println("2) Exit to desktop");
        enterInput();
    }

    public static void gameMenu() {
        System.out.println();
        System.out.println("Game Menu");
        System.out.println("1) Setup playable Elevens Game!");
        System.out.println("2) Watch the computer Play Elevens!");
        System.out.println("3) Back to main menu");
        enterInput();
    }

    public static void displayPostGameMenu(Game lastGame) {
        if (lastGame.getGameResult()){
            System.out.println(COLOR_GREEN + "You Won the Last Game.. Congrats! What would you like to do?" + COLOR_WHITE);
        } else {
            System.out.println(COLOR_RED + "You failed the Last Game.. what would you like to do?" + COLOR_RED);
        }
        System.out.println();
        System.out.println("Post Game Menu");
        System.out.println("1) Retry Elevens Game!");
        System.out.println("2) Action Replay of the Last Games's Rounds!");
        System.out.println("3) Back to Game Men");
        System.out.println();

        String resultString = "";
        System.out.println(" --- Last Games Stats --- ");
        if(lastGame.getGameResult()) resultString = " Win !"; else  resultString= " Lost !";
        System.out.println("Result: " + resultString);
        System.out.println("Rounds completed successfully: " + lastGame.getRoundQueue().getFront());
        System.out.println("fake stats bla bla");

        enterInput();
    }

    public static void displayRound(Round currentRound){
        System.out.println();
        System.out.println("------------------------ Round " + currentRound.getRoundNumber() + " ------------------------");
        currentRound.getCardSlotBag().display();
        System.out.println();
        System.out.println("Input Options:");
        System.out.println("    hint - displays a hint about cards to pick.");
        System.out.println("    quit - quit to post game .");
        System.out.println("    valid cards: a, b, c, d, e, f, g, h, i");
        System.out.println("    select 2 cards: 'ab' for Elevens pair, or 3 cards: 'abc' for face Pairs.");
    }

    public static void displayAIRound(Round currentRound){
        System.out.println();
        System.out.println("------------------------ Round " + currentRound.getRoundNumber() + " ------------------------");
        currentRound.getCardSlotBag().display();
        System.out.println();
    }

    public static void userPlayableGame() {
        System.out.println();
        System.out.println("Setting up game...");
        System.out.println("For a Human user...");
    }

    public static void aiPlayableGame() {
        System.out.println();
        System.out.println("Setting up game...");
        System.out.println("For an AI to play and user to watch...");
    }

    public static void errorExitingGame(){
        System.out.println("ERROR: an error occurred returning to main Menu...exiting game...");
    }

    public static void displayTwoCards(Card firstCard, Card secondCard, String color, String prefixString){
        System.out.print(color + prefixString + " " + firstCard + " and " + secondCard + COLOR_WHITE);
    }

    public static void displayThreeCards(Card firstCard, Card secondCard, Card thirdCard, String color, String prefixString) {
        System.out.print(color + prefixString + " " + firstCard + ", " + secondCard + " and " + thirdCard + COLOR_WHITE);
    }

    public static void displayIsStalemate(){
        System.out.println("Game is stalemate..\nYour last Hand was: \n");
    }

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

    public static void enterInput() {
        System.out.println();
        System.out.print(COLOR_GREEN + "select option > " + COLOR_WHITE);
    }

    public static void invalidInput() {
        System.out.println();
        System.out.println(COLOR_RED + "Selected an Invalid Option....try again." +  COLOR_WHITE);
    }
}
