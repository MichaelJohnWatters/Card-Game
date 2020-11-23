import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    public void MainMenu() {
        Display.mainMenu();
        String mainMenuChoice = scanner.nextLine();
        switch(mainMenuChoice) {
            case "1": // Game Menu
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

    //TODO mock game
    public void tempGame(){
        Game game = new Game();
        game.playGame();

        //PostGameMenu should take the game just played as input.
        PostGameMenu();
    }

    public void GameMenu(){
        Display.gameMenu();

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1": // Setup an Elevens Game
                tempGame();
            case "2": // return to main menu
                System.out.println("Returning to Main Menu...");
                MainMenu();
            default:
                Display.invalidInput();
                GameMenu();
        }
    }

    //Will take a RoundQueue for replay
    public void PostGameMenu() {
        Display.postGameMenu();

        //TODO display info about last game, did they win?
        // stats eg how many cards left

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1":
                tempGame();
            case "2": //Action Reply of Game
                System.out.println("WARNINGN ERROR NOT IMPLEMENNTED");
            case "3": //Return to Game Menu
                System.out.println("Returning to Game Menu");
                GameMenu();
            default: //Notify Invalid input and go to PostGameMenu
                Display.invalidInput();
                PostGameMenu();
        }
    }
}
