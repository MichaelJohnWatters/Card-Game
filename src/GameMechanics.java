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
            if(aCard.getRank().equals(Rank.KING)  ||
                    aCard.getRank().equals(Rank.QUEEN) ||
                    aCard.getRank().equals(Rank.JACK)) {
                return true;

            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isFacePairs(Card one, Card two, Card three){
        if (isFaceCard(one) && isFaceCard(two) && isFaceCard(three)) {
            boolean foundKing = false;
            boolean foundQueen = false;
            boolean foundJack = false;

            Card[] cardArray = {one, two, three};

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


}
