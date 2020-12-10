package main;

/**
 * Class Elevens holds the main method
 */
public class Elevens {

    // Welcome the User, only once per application start.
    // Create Menu.
    private static void startElevensApplication() {
        Display.welcome();
        new Menu().MainMenu();
    }

    //Main method for the application
    public static void main(String[] args) {
        startElevensApplication();
    }
}
