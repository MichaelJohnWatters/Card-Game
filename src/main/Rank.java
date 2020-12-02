package main;

/**
 * Note face cards values are set to -1, as in main.Elevens face cards to not have a usable value.
 * So we have assigned face cards a value of -1.
 */
public enum Rank {
    KING("King", -1),
    QUEEN("Queen",-1),
    JACK("Jack",-1),
    ACE("Ace", 1),
    TWO("Two",2),
    THREE("Three",3),
    FOUR("Four",4),
    FIVE("Five", 5),
    SIX("Six", 6),
    SEVEN("Seven", 7),
    EIGHT("Eight", 8),
    NINE("Nine", 9),
    TEN("Ten", 10);

    private String rank;
    private int value;

    /**
     * Constructor to create a main.Rank Object,
     * Only Require a constructor with all parameters.
     * Object fields will never be defaulted for this class.
     *
     * @param rank rank of the card example King or Ace or One.
     * @param value the integer value of the Card.
     */
    Rank(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    /***
     * Get the main.Rank in Sting format.
     * @return String
     */
    public String getRank() {
        return rank;
    }

    /***
     * Get the integer value of a card.
     * @return Int
     */
    public int getValue() {
        return value;
    }

    /**
     * Return the String value of the main.Rank.
     * Override to string for easy use in Strings.
     *
     * @return String
     */
    @Override
    public String toString() {
        return rank;
    }

}
