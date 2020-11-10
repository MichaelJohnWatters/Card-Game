public class Deck {

    //TODO a deck will have 52 cards, max, this ADT can be of type card.

    static final int MAX_CARDS = 52;

    public Deck() {

    }

    //loop through gen all cards for each house
    //maybee we should create a deck upon instiantion
    public static Deck createDeck() {
        return new Deck();
    }

    //think about what data types will be good for s shuffling
    public static void shuffleDeck(Deck aDeck) {
        //either return new deck or shuffle, i think shuffle the actual object
    }

}
