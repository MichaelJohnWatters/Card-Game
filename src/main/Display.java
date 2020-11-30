package main;

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
        System.out.println();
        System.out.println("Post Game Menu");
        System.out.println("1) Retry Elevens Game!");
        System.out.println("2) Action Replay of the Last Games's Rounds!");
        System.out.println("3) Back to main menu");
        System.out.println();

        //Print differing output dependant on win or not.
        if (lastGame.getGameResult()){
            System.out.println(COLOR_GREEN + "You Won the Last Game.. Congrats! What would you like to do?" + COLOR_WHITE);
        } else {
            System.out.println(COLOR_RED + "You failed the Last Game.. what would you like to do?" + COLOR_RED);
        }

        System.out.println("Last Games Stats");
        System.out.println("fake stats bla bla");
        System.out.println("fake stats bla bla");
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
        System.out.println("    forfeit - forfeit to post game .");
        System.out.println("    valid cards: a, b, c, d, e, f, g, h, i");
        System.out.println("    select 2 cards: 'ab' for Elevens pair, or 3 cards: 'abc' for face Pairs.");
    }
    public static void playGame() {
        System.out.println();
        System.out.println("Setting up game...");
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
