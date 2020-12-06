package main;

import java.util.Scanner;

/**
 * A class that represents each menu, each menu then creates game objects or other objects whenn the user selects
 * and option.
 */
public class Menu {

    private Scanner scanner = new Scanner(System.in);

    //only used for when user is prompted to key press.
    private Scanner keyPressScanner = new Scanner(System.in);

    public void MainMenu() {
        Display.mainMenu();
        String mainMenuChoice = scanner.nextLine();
        switch(mainMenuChoice) {
            case "1": // To Game Menu
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
                try {
                    game.userPlayableGame();
                } catch (Exception e){
                    Display.displayGameCrashed();
                    MainMenu();
                }

                PostGameMenu(game, true);
            case "2": // AI playable Elevens game
                System.out.println("Setting up a watchable AI Elevens Game....");

                Game aiPlayableGame = new Game();
                try {
                    aiPlayableGame.computerDemonstrationGame();
                } catch (Exception e){
                    Display.displayGameCrashed();
                    MainMenu();
                }

                PostGameMenu(aiPlayableGame, false);
            case "3": // return to main menu
                System.out.println("Returning to Main Menu...");
                MainMenu();
            default:
                Display.invalidInput();
                GameMenu();
        }
    }

    public void PostGameMenu(Game lastGame, boolean isHuman) {
        Display.displayPostGameMenu(lastGame);

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1":
                if(isHuman){
                    System.out.println("Setting up user playable Elevens Game....");

                    //create game object and start user Playable Game
                    Game game = new Game();
                    try {
                        game.userPlayableGame();
                    } catch (Exception e){
                        Display.displayGameCrashed();
                        MainMenu();
                    }

                    //go to post game Menu
                    PostGameMenu(game, true);
                } else {
                    System.out.println("Setting up a watchable AI Elevens Game....");

                    //create game object and start a computer playable Game
                    Game aiPlayableGame = new Game();
                    try {
                        aiPlayableGame.computerDemonstrationGame();
                    } catch (Exception e){
                        Display.displayGameCrashed();
                        MainMenu();
                    }

                    //go to post game Menu
                    PostGameMenu(aiPlayableGame, false);
                }
            case "2": //Action Reply of Game
                while(lastGame.getRoundQueue().getFront() != null) {
                    Display.displayActionReplayOfLastGame(lastGame);
                }

                //End of replay
                System.out.println(Colors.COLOR_RED + "End of Replay...\n" + Colors.COLOR_WHITE);

                //Wait for input
                keyPressScanner.nextLine();
                Display.returningToGameMenu();
                GameMenu();
            case "3": //Return to main.Game main.Menu
                Display.returningToGameMenu();
                GameMenu();
            default: //Notify Invalid input and go to PostGameMenu
                Display.invalidInput();
                //TODO fix
        }
    }
}
