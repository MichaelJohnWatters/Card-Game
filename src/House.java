public enum House {
    HEARTS("HEARTS"),
    DIAMONDS("DIAMONDS"),
    SPADES("SPADES"),
    CLUBS("CLUBS");

    private String houseName;

    House(String house) {
        this.houseName = house;
    }

    public String getHouseName() {
        return houseName;
    }
}
