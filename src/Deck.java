import java.util.EmptyStackException;
import java.util.Random;

public class Deck {
    
    private static final int MAX_CARDS = 52;
    private int cardIndex;
    private static final String[] DECK = {"Ace Clubs", "2 Clubs", "3 Clubs", "4 Clubs", "5 Clubs", "6 Clubs", "7 Clubs", "8 Clubs", " 9 Clubs", "10 Clubs", "Jack Clubs", "Queen Clubs", "King Clubs",
            "Ace Hearts", "2 Hearts", "3 Hearts", "4 Hearts", "5 Hearts", "6 Hearts", "7 Hearts", "8 Hearts", "9 Hearts", "10 Hearts", "Jack Hearts", "Queen Hearts", "King Hearts",
            "Ace Spades", "2 Spades", "3 Spades", "4 Spades", "5 Spades", "6 Spades", "7 Spades", "8 Spades", "9 Spades", "10 Spades", "Jack Spades", "Queen Spades", "King Spades",
            "Ace Diamonds", "2 Diamonds", "3 Diamonds", "4 Diamonds", "5 Diamonds", "6 Diamonds", "7 Diamonds", "8 Diamonds", "9 Diamonds", "10 Diamonds", "Jack Diamonds", "Queen Diamonds", "King Diamonds"
            };
    private Card<String> topNode;
    /**
     * Note
     *
     * I have shuffled the DECK array using util.Random
     * created a shuffled deck when a new deck object is instantiated
     *
     *
     * */
    public Deck() {
        topNode = null;
        this.cardIndex = MAX_CARDS - 1;
        deal();
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

    public void push (String newEntry) {
        Card<String> newNode = new Card<String>(newEntry);
        newNode.setNext(topNode);
        topNode = newNode;
    }

    public String pop() {
        String dataToReturn = peek();
        topNode = topNode.getNext();
        return dataToReturn;
    }

    public String peek() {
        if (topNode == null) {
            throw new EmptyStackException();
        }
        else {
            return topNode.getData();
        }
    }

    public boolean isEmpty() {
        return topNode == null;
    }
    public void printStackDeck(){
    if(isEmpty()){
        return;
    }
        for(int i = 0; i < MAX_CARDS; i++){
        String item = pop();
        System.out.println(item); //Pop & Print
        push(item);
    }
}
    public void deal(){
        shuffleDeck();
        for(int i = 0; i < MAX_CARDS; i++){
            System.out.println(this.DECK[i]);
        }

        for(int i = 0; i < MAX_CARDS; i++){
            push(this.DECK[i]);
        }

        printStackDeck();

    }

    public static void main(String[] args) {
        Deck deck = new Deck();

    }
}
