public class Card {

    private House house;
    private Rank rank;

    /**
     * Constructor for an object representing a playing Card.
     * Only require a constructor with both fields, as a card
     * must have a House and a Rank in Elevens.
     *
     * No field should ever be defaulted in this class.
     *
     * @param house
     * @param rank
     */
    public Card(House house, Rank rank) {
        this.house = house;
        this.rank = rank;
    }

    /**
     * Getters And Setters
     *
     * We will only require Getters,
     * as you can not change a cards value or house.
     * */
    public House getHouse() {
        return house;
    }

    public Rank getRank() {
        return rank;
    }
}
