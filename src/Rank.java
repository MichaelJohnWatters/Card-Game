public enum Rank {
    KING("king", 10),
    QUEEN("queen",10),
    JACK("jack",10),
    ACE("ace", 1),
    TWO("two",2),
    THREE("three",3),
    FOUR("four",4),
    FIVE("five", 5),
    SIX("one", 6),
    SEVEN("seven", 7),
    EIGHT("eight", 8),
    NINE("nine", 9),
    TEN("ten", 10);

    private String rank;
    private int value;

    /**
     * Constructor to create a Rank Object,
     * Only Require a constructor with all parameters.
     * Object fields will never be defaulted for this class.
     *
     * @param rank
     * @param value
     */
    Rank(String rank, int value) {
        this.rank = rank;
        this.value = value;
    }

    /**
     * Getters And Setters
     *
     * We will only require Getters, as the rank can only be set during
     * instantiation of the object.
     * */
    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

}
