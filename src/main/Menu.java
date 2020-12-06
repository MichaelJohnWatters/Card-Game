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

                    Game game = new Game();
                    try {
                        game.userPlayableGame();
                    } catch (Exception e){
                        Display.displayGameCrashed();
                        MainMenu();
                    }

                    PostGameMenu(game, true);
                } else {
                    System.out.println("Setting up a watchable AI Elevens Game....");

                    Game aiPlayableGame = new Game();
                    try {
                        aiPlayableGame.computerDemonstrationGame();
                    } catch (Exception e){
                        Display.displayGameCrashed();
                        MainMenu();
                    }
                    PostGameMenu(aiPlayableGame, false);
                }
            case "2": //Action Reply of Game
                while(lastGame.getRoundQueue().getFront() != null){
                    System.out.println();
                    System.out.println("------------------------------- Replay Round Number: " + lastGame.getRoundQueue().getFront().getRoundNumber() +"-------------------------------");

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

                    //State of Cards on table at the end of the round
                    System.out.println(Colors.COLOR_GREEN + "\nState of Cards in play at the end of the round, after discard cards where removed..." + Colors.COLOR_WHITE);
                    lastGame.getRoundQueue().getFront().getCardsInPlayBag().display(false);

                    //print the discarded cards from the rounds discard card memory, these cards are cards that where successfully removed.
                    int discarded = lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards().countCards();
                    System.out.println(Colors.COLOR_GREEN + "\nNumber of discarded cards that round: " + discarded + ", discarded that round(successfully removed): " + Colors.COLOR_WHITE);

                    for (int i = 0; i < discarded; i++) {
                        CardSlotsBag bag1 =  lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards();
                        String commaIfRequired = "";
                        if(i == discarded-1){ commaIfRequired = " ";} else { commaIfRequired = ", ";}
                        System.out.print(Colors.COLOR_RED +" " + bag1.cardAtPosition(i).toString() + commaIfRequired + Colors.COLOR_WHITE);
                    }

                    //dequeue the round that's been displayed.
                    lastGame.getRoundQueue().dequeue();
                    System.out.println("\nPress any key to continue to the next replay round...");
                    keyPressScanner.nextLine();
                }

                System.out.println(Colors.COLOR_RED + "End of Replay..." + Colors.COLOR_WHITE);

                System.out.println();
                keyPressScanner.nextLine();
                System.out.println("Returning to Game Menu...");
                GameMenu();
            case "3": //Return to main.Game main.Menu
                System.out.println("Returning to Game Menu...");
                GameMenu();
            default: //Notify Invalid input and go to PostGameMenu
                Display.invalidInput();
                //TODO fix
        }
    }
}
