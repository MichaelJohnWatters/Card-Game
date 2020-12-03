package main;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

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
                    aiPlayableGame.computerPlayableGame();
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
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("------------------" + lastGame.getRoundQueue().getFront().getRoundNumber() +"------------------");
        System.out.println("------------------------------------");
        while(lastGame.getRoundQueue().getFront() != null){
            lastGame.getRoundQueue().getFront().getCardsInPlayBag().display();
            System.out.println("Round Number: " + lastGame.getRoundQueue().getFront().getRoundNumber());

            //drawn cards
            int drawn = lastGame.getRoundQueue().getFront().getRoundMemoryDrawCards().countCards();
            System.out.println("Number of Drawn cards that round : " +drawn );

            for (int i = 0; i < drawn; i++) {
              CardSlotsBag bag =  lastGame.getRoundQueue().getFront().getRoundMemoryDrawCards();
                System.out.println(bag.cardAtPosition(i).toString());
            }

            //discarded cards
            int discarded = lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards().countCards();
            System.out.println("Number of discarded cards that round : " + discarded );

            for (int i = 0; i < discarded; i++) {
                CardSlotsBag bag1 =  lastGame.getRoundQueue().getFront().getRoundMemoryDiscardCards();
                System.out.println(bag1.cardAtPosition(i).toString());
            }


            lastGame.getRoundQueue().dequeue();
        }
        System.out.println("------------------------------------");
        System.out.println("------------------------------------");
        System.out.println("------------------------------------");
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
                } else {
                    System.out.println("Setting up a watchable AI Elevens Game....");
                    Game aiPlayableGame = new Game();
                    try {
                        aiPlayableGame.computerPlayableGame();
                    } catch (Exception e){
                        Display.displayGameCrashed();
                        MainMenu();
                    }
                }
            case "2": //Action Reply of Game
                System.out.println("WARNINGNERROR NOT IMPLEMENNTED");
                System.exit(1);
            case "3": //Return to main.Game main.Menu
                System.out.println("Returning to Game Menu");
                GameMenu();
            default: //Notify Invalid input and go to PostGameMenu
                Display.invalidInput();
                //TODO fix
        }
    }
}
