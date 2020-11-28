package Test;

public class DeckTestSpec {

    public static void displayCard(String rank, String value){
        System.out.println("   _________");
        System.out.println("a:|         |");
        System.out.println("  |         |");
        System.out.println("  |   10    |");
        System.out.println("  | Diamonds|");
        System.out.println("  |         |");
        System.out.println("  |         |");
        System.out.println("  |_________|");
    }

    public static String formatValue(int value){
        if(value > 9) return value + "";
        else return value + " ";
    }


}
