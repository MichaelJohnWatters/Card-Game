package main;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    public void MainMenu() {
        Display.mainMenu();
        String mainMenuChoice = scanner.nextLine();
        switch(mainMenuChoice) {
            case "1": // main.Game main.Menu
                GameMenu();
                MainMenu();
            case "2": // Exit to desktop
                System.out.println("Exiting Application....Goodbye!!");
                System.exit(0);
            default:
                Display.invalidInput();
                MainMenu();
        }
    }

    public void GameMenu(){
        Display.gameMenu();

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1": // Setup user playable Elevens main.Game
                System.out.println("Setting up user playable Elevens Game....");
                Game game = new Game();
                game.userPlayableGame();

                PostGameMenu(game);
            case "2": // AI playable Elevens game
                System.out.println("Setting up a watchable AI Elevens Game....");

                Game aiPlayableGame = new Game();
                aiPlayableGame.computerPlayableGame();

                PostGameMenu(aiPlayableGame);
            case "3": // return to main menu
                System.out.println("Returning to Main Menu...");
                MainMenu();
            default:
                Display.invalidInput();
                GameMenu();
        }
    }

    public void PostGameMenu(Game lastGame) {
        Display.displayPostGameMenu(lastGame);

        String gameMenuChoice = scanner.nextLine();

        //TODO maybe while here instead
        switch(gameMenuChoice) {
            case "1":
                Game game = new Game();
                game.userPlayableGame();
                PostGameMenu(game);
            case "2": //Action Reply of main.Game
                System.out.println("WARNINGNERROR NOT IMPLEMENNTED");
            case "3": //Return to main.Game main.Menu
                System.out.println("Returning to main.Game Menu");
                GameMenu();
            default: //Notify Invalid input and go to PostGameMenu
                Display.invalidInput();
                //TODO fix
        }
    }
}
