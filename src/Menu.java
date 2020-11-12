import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    public void MainMenu() {
            Display.mainMenu();
            String mainMenuChoice = scanner.nextLine();
            switch(mainMenuChoice) {
                case "1": // Play Elevens
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
                case "1": // Setup an Elevens Game
                    System.out.println("Setting up game");
                    System.out.println("you win!");
                    PostGameMenu();
                case "2": // return to main menu
                    System.out.println("Returning to Main Menu...");
                    MainMenu();
                default:
                    Display.invalidInput();
                    GameMenu();
            }
    }

    public void PostGameMenu(){
        Display.gameFailedMenu();

        String gameMenuChoice = scanner.nextLine();

        switch(gameMenuChoice) {
            case "1":
                System.out.println("Retrying  game");
                System.out.println("Setting up game");
                System.out.println("you win!");
                PostGameMenu();
            case "2":
                System.out.println("Returning to Main Menu...");
                break;
            case "3":
                System.out.println("Returning to Game Menu...");
                GameMenu();
            default:
                Display.invalidInput();
                PostGameMenu();
        }
    }
}
