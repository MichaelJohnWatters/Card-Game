public enum House {
    HEARTS("HEARTS"),
    DIAMONDS("DIAMONDS"),
    SPADES("SPADES"),
    CLUBS("CLUBS");

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
     * Return the String value of the House.
     *
     * @return String
     */
    public String getHouseName() {
        return houseName;
    }
}
