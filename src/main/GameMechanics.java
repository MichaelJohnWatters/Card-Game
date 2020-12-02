package main;

//TODO TESTS
public class GameMechanics {

    /**
     * Checks if a Card is a face card
     * @param aCard the card in question.
     * @return boolean
     */
    public static boolean isFaceCard(Card aCard) {

        //make sure the card is not null
        if (aCard != null){

            //if not null look for a Face Card rank.
            if(aCard.getRank().equals(Rank.KING)
                    || aCard.getRank().equals(Rank.QUEEN)
                    || aCard.getRank().equals(Rank.JACK)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the supplied Cards are face pairs, eg one of each King, Queen and Jack.
     * Otherwise returns false.
     * @param oneCard the first card
     * @param twoCard the second card
     * @param threeCard the third card
     * @return boolean
     */
    public static boolean isFacePairs(Card oneCard, Card twoCard, Card threeCard){
        if (isFaceCard(oneCard) && isFaceCard(twoCard) && isFaceCard(threeCard)) {
            boolean foundKing = false;
            boolean foundQueen = false;
            boolean foundJack = false;

            Card[] cardArray = {oneCard, twoCard, threeCard};

            for (int i = 0; i < cardArray.length; i++) {
                if(cardArray[i].getRank().equals(Rank.KING)){
                    foundKing = true;
                }
                if(cardArray[i].getRank().equals(Rank.QUEEN)){
                    foundQueen = true;
                }
                if(cardArray[i].getRank().equals(Rank.JACK)){
                    foundJack = true;
                }
            }
            return foundKing && foundQueen && foundJack;

        } else {
            return false;
        }
    }

    /**
     * Checks if the Supplied left and right cards, are a valid Elevens pair and returns true.
     * otherwise returns false.
     * @param lhs first/left card
     * @param rhs second/right card
     * @return boolean
     */
    public static boolean isElevensPair(Card lhs, Card rhs) {
        boolean isElevensPair = false;
        if(lhs != null && rhs !=null) {
            if (!isFaceCard(lhs) && !isFaceCard(rhs)){
                if(lhs.getRank().getValue() + rhs.getRank().getValue() == 11){
                    isElevensPair = true;
                }
            }
        }
        return isElevensPair;
    }

    /**
     * Converts chars from a-i to corresponding int values 0-8.
     * @param letter input letter to convert to int
     * @return int
     */
    public static int cardSelectionCharToInt(char letter) {
        switch (letter){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            default : return -1;
        }
    }

    /**
     * Converts int number 0-8 to corresponding String value of a-i
     * @param number input int number to convert to String.
     * @return String
     */
    public static String cardSelectionNumberToString(int number) {
        switch (number){
            case 0: return "a";
            case 1: return "b";
            case 2: return "c";
            case 3: return "d";
            case 4: return "e";
            case 5: return "f";
            case 6: return "g";
            case 7: return "h";
            case 8: return "i";
            default : return "ERROR";
        }
    }

    /**
     * Checks if the input string contains a valid card selection.
     * @param input String to check if the selection is valid eg, a valid
     * @return returns true if the string selection is valid.
     */
    //TODO need complete re-work
    //TODO start as false
    //TODO Make cards slots are not NULL, probs nneed new params or move else where etc.
    //TODO Prevent user from selecting the same card twice eg 'aa'
    public static boolean validStringSelection(String input) {
        boolean valid = true;
        //if the input is greater than 2 but less than 3, check if characters selected are allow.
        if(input.length() > 1 && input.length() < 4) {
            char[] inputAsCharArray = input.toLowerCase().toCharArray();
            //TODO alreadyAskedFor, cant ask for same card
            //char[] alreadyAskedFor = new char[9];

            //for each character in input check if it is not an allow character.
            //if so valid = false.
            for (char character : inputAsCharArray) {
                if (!allowedCharacter(character)) {
                    System.out.println(character + " is NOT allowed..");
                    valid = false;
                    break;
                }
                System.out.println(character + " is allowed..");
            }

        } else { valid = false; }
        return valid;
    }

    /**
     * Checks if the selected char is a valid card that can be choosen.
     * Does not check if the card slot has a card.
     * @param letter char to check if is one of the slots.
     * @return returns true if the selected char is one of a-i
     */
    public static boolean allowedCharacter(char letter) {
        char[] allowedChars = {'a','b','c','d','e','f','g','h','i'};
        boolean contains = false;

        for (char character : allowedChars) {
            if (character == letter) {
                contains = true;
                break;
            }
        }
        return contains;
    }
}
