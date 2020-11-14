public class Display {

    public static void  welcome(){
        System.out.println();
        System.out.println("Welcome to Michael Watters and Araon Hoy's Assignment 2 - Elevens ");
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
        System.out.println("1) Setup Elevens Game!");
        System.out.println("2) Back to main menu");
        enterInput();
    }

    public static void postGameMenu() {
        System.out.println();
        System.out.println("Post Game Menu");
        System.out.println("1) Retry Elevens Game!");
        System.out.println("2) Action Replay of the Last Games's Rounds!");
        System.out.println("3) Back to main menu");
        enterInput();
    }

    public static void enterInput() {
        System.out.print("select option > ");
    }

    public static void invalidInput() {
        System.out.println("Selected an Invalid Option....try again.");
    }





}
