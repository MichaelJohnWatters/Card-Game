package main;

/**
 * This Class is used to represent a Card with this application.
 * Contains information about its house and its rank.
 */
public class Card extends Colors {

    private House house;
    private Rank rank;

    /**
     * Constructor for an object representing a playing main.Card.
     * Only require a constructor with both fields, as a card
     * must have a main.House and a main.Rank in main.Elevens.
     * <p>
     * No field should ever be defaulted in this class.
     *
     * @param house house/suite of the card, Enum of main.House
     * @param rank  rank of the card, Enum of main.Rank: King, Queen, Jack, Ace, One,Two,Three,Four,Five,Six,Seven,Nine,Ten
     */

    public Card(House house, Rank rank) {
        this.house = house;
        this.rank = rank;
    }

    /**
     * Converts the Cards Rank Value into a single or double letter digit.
     *
     * @param aCard
     * @return String, single letter String value of main.House
     */
    public static String extractRankAsDigit(Card aCard) {
        String output = "";
        switch (aCard.getRank()) {
            case KING:
                output = "K";
                break;
            case QUEEN:
                output = "Q";
                break;
            case JACK:
                output = "J";
                break;
            case ACE:
                output = "A";
                break;
            case TWO:
                output = "2";
                break;
            case THREE:
                output = "3";
                break;
            case FOUR:
                output = "4";
                break;
            case FIVE:
                output = "5";
                break;
            case SIX:
                output = "6";
                break;
            case SEVEN:
                output = "7";
                break;
            case EIGHT:
                output = "8";
                break;
            case NINE:
                output = "9";
                break;
            case TEN:
                output = "10";
                break;
        }
        return output;
    }

    /**
     * Converts A Cards House Object into a Single Color coded Digit.
     *
     * @param aCard
     * @return String, single letter String value of main.House
     */
    public static String extractHouseAsDigitWithColor(Card aCard) {
        String output = "";
        switch (aCard.getHouse()) {
            case DIAMONDS:
                output = COLOR_RED + "D" + COLOR_WHITE;
                break;
            case HEARTS:
                output = COLOR_RED + "H" + COLOR_WHITE;
                break;
            case CLUBS:
                output = COLOR_GREEN + "C" + COLOR_WHITE;
                break;
            case SPADES:
                output = COLOR_GREEN + "S" + COLOR_WHITE;
                break;
        }
        return output;
    }

    /**
     * Information such as the cards main.Rank and main.House as a String.
     * <p>
     * If is a face card will return Rank as String and its house including Ace(but is not a face card).
     * If is a value card will return numeric the value of the card and its house.
     * <p>
     * Example King of DIAMONDS
     * Example 10 of DIAMONDS
     * Example Ace of Spades
     *
     * @return String, a description of the main.Card
     */
    @Override
    public String toString() {
        if (rank.getValue() == -1 || rank.getValue() == 1) {
            return rank.getRank() + " of " + house.toString();
        } else {
            return rank.getValue() + " of " + house.toString();
        }
    }

    /**
     * Returns the house of the card
     * <p>
     * Heart, Spade, Club, Diamond
     *
     * @return main.House
     */
    public House getHouse() {
        return house;
    }

    /**
     * Returns the rank of the card
     * Can be a face card or ace or value card.
     * King, Queen, Jack, Ace, 2,3,4,5,6,7,8,9,10
     *
     * @return main.Rank
     */
    public Rank getRank() {
        return rank;
    }
}
