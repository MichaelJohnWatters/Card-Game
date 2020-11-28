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
     * @param house house/suite of the card, Enum of House
     * @param rank rank of the card, Enum of Rank: King, Queen, Jack, Ace, 2,3,4,5,6,7,8,9,10
     */

    public Card(House house, Rank rank) {
        this.house = house;
        this.rank = rank;
    }

    /**
     * Information such as the cards Rank and House as a String.
     *
     * If is a face card will return Rank as String and its house, except Ace.
     * If is a value card will return the value of the card and its house
     *
     * Example King of DIAMONDS
     * Example 10 of DIAMONDS
     *
     * @return String, a description of the Card
     */
    @Override
    public String toString() {
        if(rank.getValue() == -1) {
            return rank.getRank() + " of " + house.toString();
        } else {
            return rank.getValue() + " of " + house.toString();
        }
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


    /**
     *
     *
     * @param aCard
     * @return String, single letter String value of House
     */
    public static String convertRankToDigit(Card aCard) {
        String output = "";
        switch (aCard.getRank()){
            case KING  : output = "K"; break;
            case QUEEN : output = "Q"; break;
            case JACK  : output = "J"; break;
            case ACE   : output = "A"; break;
            case TWO   : output = "2"; break;
            case THREE : output = "3"; break;
            case FOUR  : output = "4"; break;
            case FIVE  : output = "5"; break;
            case SIX   : output = "6"; break;
            case SEVEN : output = "7"; break;
            case EIGHT : output = "8"; break;
            case NINE  : output = "9"; break;
            case TEN   : output = "10"; break;
        }
        return output;
    }

    /**
     *
     *
     * @param aCard
     * @return String, single letter String value of House
     */
    public static String convertHouseToDigit(Card aCard) {
        String output = "";
        switch (aCard.getHouse()) {
            case DIAMONDS: output = "D"; break;
            case CLUBS   : output = "C"; break;
            case SPADES  : output = "S"; break;
            case HEARTS  : output = "H"; break;
        }
        return output;
    }
}
