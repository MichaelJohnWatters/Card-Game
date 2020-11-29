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
            case "1": // Setup user playable main.Elevens main.Game
                Game game = new Game();
                game.playGame();

                PostGameMenu(game);
            case "2": // Computer plays main.Elevens game
                System.out.println("Watch Computer playing main.Elevens TODO");

                //TODO make a AI GAME
                Game game2 = new Game();
                game2.playGame();

                PostGameMenu(game2);
            case "3": // return to main menu
                System.out.println("Returning to Main main.Menu...");
                MainMenu();
            default:
                Display.invalidInput();
                GameMenu();
        }
    }

    public void PostGameMenu(Game lastGame) {
        Display.postGameMenu();

        if (lastGame.getDidWeWin()){
            System.out.println("You failed the Last Game.. what would you like to do?");
        } else {
            System.out.println("You Won the Last Game.. Congrats! What would you like to do?");
        }

        //TODO display info about last game, did they win?
        // stats eg how many cards left

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1":
                Game game = new Game();
                game.playGame();
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
