package main;

public class Elevens {

    public static void startElevensApplication(){
        Display.welcome(); //Welcome the User, only once per application start.
        new Menu().MainMenu(); //Main Menu
    }

    public static void main(String[] args) {
        startElevensApplication();
    }
}
