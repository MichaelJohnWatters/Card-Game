public class Card<T> {

    private House house;
    private Rank rank;

    private T data;
    private Card<T> next;

    /**
     * Constructor for an object representing a playing Card.
     * Only require a constructor with both fields, as a card
     * must have a House and a Rank in Elevens.
     *
     * No field should ever be defaulted in this class.
     *
     * @param house house/suite of the card, Enum of House
     * @param rank rank of the card, Enum of Rank: King, Queen, Jack, Ace, 2,3,4,5,6,7,8,9,10
     */
    public Card(T dataValue) {
        this.data = dataValue;
        this.next = null;
        this.house = house;
        this.rank = rank;
    }

    public Card(House house, Rank rank) {
        this.house = house;
        this.rank = rank;
    }

    /**
     * Information such as the cards Rank and House as a String.
     *
     * @return String, a description of the Card
     */
    @Override
    public String toString() {
        return rank.toString() + "(" + rank.getValue() + ") of " + house.toString();
    }

    /**
     * Returns the house of the card
     *
     * Heart, Spade, Club, Diamond
     * @return House
     */
    public House getHouse() {
        return house;
    }

    /**
     * Returns the rank of the card
     * Can be a face card or ace or value card.
     * King, Queen, Jack, Ace, 2,3,4,5,6,7,8,9,10
     * @return Rank
     */
    public Rank getRank() {
        return rank;
    }

    public T getData() {
        return data;
    }

    public void setData(T dataValue) {
        this.data = dataValue;
    }

    public Card<T> getNext() {
        return next;
    }

    public void setNext(Card<T> nextNode) {
        this.next = nextNode;
    }
}
