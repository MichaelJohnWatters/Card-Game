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

    public static boolean isElevensPair(Card lhs, Card rhs) {
        if (!isFaceCard(lhs) && !isFaceCard(rhs)){
            return (lhs.getRank().getValue() + rhs.getRank().getValue()) == 11;
        } else return false;
    }

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

    //TODO check for allready selected chars
    public static boolean validStringSelection(String input) {
        boolean valid = true;

        //if the input is greater than 2 but less than 3, check if characters selected are allow.
        if(input.length() > 1 && input.length() < 4){
            char[] inputAsCharArray = input.toLowerCase().toCharArray();
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

    public static boolean allowedCharacter(char letter) {
        char[] allowedChars = {'a','b','c','d','e','f','g','i'};
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
