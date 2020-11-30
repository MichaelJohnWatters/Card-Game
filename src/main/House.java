package main;

public enum House {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    SPADES("Spades"),
    CLUBS("Clubs");

    private String houseName;

    /**
     * Constructor for house.
     *
     * @param house one of the houses of a suite of cards.
     */
    House(String house) {
        this.houseName = house;
    }

    /**
     * Return the String value of the main.House.
     *
     * @return String
     */
    public String toString() {
        return houseName;
    }
}
