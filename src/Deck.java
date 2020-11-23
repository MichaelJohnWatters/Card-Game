import java.util.Random;

public class Deck {
    
    private static final int MAX_CARDS = 52;
    private int cardIndex;
    private static final String[] DECK = {"Ace Clubs", "2 Clubs", "3 Clubs", "4 Clubs", "5 Clubs", "6 Clubs", "7 Clubs", "8 Clubs", " 9 Clubs", "10 Clubs", "Jack Clubs", "Queen Clubs", "King Clubs",
            "Ace Hearts", "2 Hearts", "3 Hearts", "4 Hearts", "5 Hearts", "6 Hearts", "7 Hearts", "8 Hearts", "9 Hearts", "10 Hearts", "Jack Hearts", "Queen Hearts", "King Hearts",
            "Ace Spades", "2 Spades", "3 Spades", "4 Spades", "5 Spades", "6 Spades", "7 Spades", "8 Spades", "9 Spades", "10 Spades", "Jack Spades", "Queen Spades", "King Spades",
            "Ace Diamonds", "2 Diamonds", "3 Diamonds", "4 Diamonds", "5 Diamonds", "6 Diamonds", "7 Diamonds", "8 Diamonds", "9 Diamonds", "10 Diamonds", "Jack Diamonds", "Queen Diamonds", "King Diamonds"
            };
    /**
     * Note
     *
     * I have shuffled the DECK array using util.Random
     * created a shuffled deck when a new deck object is instantiated
     *
     *
     * */

    public Deck() {
        this.cardIndex = MAX_CARDS - 1;
    }

    public void shuffleDeck() {
        int index;
        String temp;

        Random random = new Random();
        for(int i = this.DECK.length - 1; i > 0; i--){
            index = random.nextInt(i + 1);
            temp = this.DECK[index];
            this.DECK[index] = this.DECK[i];
            this.DECK[i] = temp;
        }
    }

    public String deal(){
        return this.DECK[this.cardIndex--];
    }

    public static void main(String[] args) {
        Deck deck = new Deck();

    }
}
