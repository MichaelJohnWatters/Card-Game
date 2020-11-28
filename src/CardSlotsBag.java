public final class CardSlotsBag {

    private Card[] cardSlotsBag;
    private int numberOfCards;

    private static final int FIXED_CAPACITY = 9;

    public CardSlotsBag() {
        cardSlotsBag = new Card[FIXED_CAPACITY];
        numberOfCards = 0;
    }

    public boolean isArrayFull() {
        return (cardSlotsBag.length == numberOfCards);
    }

    //@Override
    public int getCurrentSize() {
        return numberOfCards;
    }

    public static int getFixedCapacity() {
        return FIXED_CAPACITY;
    }

    //@Override
    public boolean isEmpty() {
        return numberOfCards == 0;
    }

    //@Override
    public boolean addNewCard(Card aCard) {
        if (isArrayFull()) return false;
        else {
            cardSlotsBag[numberOfCards++] = aCard;
            return true;
        }
    }

    //@Override
    public Card remove() {
        return removeCardAt(numberOfCards-1);
    }

    //@Override
    public boolean remove(Card aCard) {
        boolean found = false;
        int index = 0;
        while(!found && index < numberOfCards)
            if(cardSlotsBag[index].equals(aCard)) found = true;
            else  index++;
        if(found) removeCardAt(index);
        return found;
    }

    private Card removeCardAt(int index){
        Card result = null;

        if(!isEmpty() && index >= 0 && index < numberOfCards) {
            result = cardSlotsBag[index];
            cardSlotsBag[index] = cardSlotsBag[numberOfCards -1];
            cardSlotsBag[numberOfCards -1] = null;
            numberOfCards--;
        }
        return result;
    }

    //@Override
    public void clear() {
        while (!isEmpty()) remove();
    }

    //@Override
    public int getFrequencyOf(Card aCard) {
        int count = 0;
        for (int i = 0; i < numberOfCards; i++)
            if(cardSlotsBag[i].equals(aCard)) count++;
        return count;
    }

    //@Override
    public boolean contains(Card aCard) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards)
            if (cardSlotsBag[index++].equals(aCard)) found = true;
        return found;
    }

    public Card cardAtPosition(int index){
        if(index >= 0 && index <= 9){
            if(cardSlotsBag[index] != null){
                return cardSlotsBag[index];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean containsCardValue(int cardValue) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {

            //TODO swap if orders

            //If we find a card with the correct value, we must check it is not a face card.
            if (cardSlotsBag[index++].getRank().getValue() == cardValue) {

                //if the card is not a Face card
                if (!GameMechanics.isFaceCard(cardSlotsBag[index])){
                    found = true;
                }
            }
        }
        return found;
    }

    public boolean containsRank(Rank cardRank) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {
            if (cardSlotsBag[index++].getRank().equals(cardRank)) found = true;
        }
        return found;
    }

    /**
     * Returns true if this contains 3 face cards of jack queen king.
     * @return boolean
     */
    public boolean containsKingQueenJack() {
        boolean foundKing  = false;
        boolean foundQueen = false;
        boolean foundJack  = false;

        for (Card card : cardSlotsBag) {

            //if the current card is not null, check for each of the face cards.
            //if a face card exists set flag for that rank to true.
            if (card != null) {
                if (card.getRank().equals(Rank.KING)) foundKing   = true;
                if (card.getRank().equals(Rank.QUEEN)) foundQueen = true;
                if (card.getRank().equals(Rank.JACK)) foundJack   = true;
            }
        }

        //Return all flags, only returns true if all flags are true, otherwise false.
        System.out.println("contains king, queen, jack:" + " " + foundKing + " " + foundQueen + " " + foundJack);
        return foundKing && foundQueen && foundJack;
    }


    public boolean containsElevensPair() {

        //found flag, default false
        boolean foundPair = false;

        //Take each card in the cardSlotBag
        for (Card card : cardSlotsBag) {

            //if the card selected is not null then
            if (card != null) {

                //check if card is not a face card, as face cards are checked in method containsKingQueenJack.
                if(!GameMechanics.isFaceCard(card)){

                    //if not a face card, find this cards pair, Elevens pairs will always be 11 minus the current cards value.
                    int requiredPairValue = (11 - card.getRank().getValue());

                    //search for the require value pair
                    //we will not ignore the current card, as it would acctually be less performant to filter it out.
                    if(this.containsCardValue(requiredPairValue)) {
                        //set foundPair to true and break out of the current loop.
                        foundPair = true;
                    }
                }
            }
        }
        if (!foundPair) System.out.println("No Elevens Pair"); else System.out.println("This round contains an Elevens pair");
        return foundPair;
    }

    //@Override
    public Card[] toArray() {
        Card[] resultCardArray = new Card[FIXED_CAPACITY];
        System.arraycopy(cardSlotsBag, 0, resultCardArray, 0, numberOfCards);
        return resultCardArray;
    }

    /**
     * Dont use this out side of display();
     *
     * used to format Strings for display method.
     * @param str
     * @return
     */
    private static String formatStringForDisplay(String str){
        try {
            int parsedInt = Integer.parseInt(str);

            //dont append extra whitespace if double digit int value.
            if(parsedInt > 9) return parsedInt + "";
            else return parsedInt + " ";
        }
        catch(Exception e) {
            //cant convert to string so much be a single letter either, Jack King Queen.
            return str + " ";
        }
    }

    // TODO Only if we have time, convert this method to for loops.
    //     don't do this until everything else is done.
    //     Also Make house and value diagonal rather than vertical
    public void display(){

        //16 space string, pad out print lines if slot is empty.
        String hiddenCardStringRow = "                ";

        //TODO unnecessary array, replace with direct reference to bag array.
        Card [][] threeByThreeCardMatrix = {
                {cardSlotsBag[0],cardSlotsBag[1],cardSlotsBag[2]},
                {cardSlotsBag[3],cardSlotsBag[4],cardSlotsBag[5]},
                {cardSlotsBag[6],cardSlotsBag[7],cardSlotsBag[8]}
        };

        String [][] rowOne   = new String[7][3];
        String [][] rowTwo   = new String[7][3];
        String [][] rowThree = new String[7][3];

        if(threeByThreeCardMatrix[0][0] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[0][0]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[0][0]));
            rowOne[0][0] =               "      _________ ";
            rowOne[1][0] = String.format("   a:|%s      %s|",value, house);
            rowOne[2][0] =               "     |         |";
            rowOne[3][0] =               "     |         |";
            rowOne[4][0] =               "     |         |";
            rowOne[5][0] = String.format("     |%s      %s|",value, house);
            rowOne[6][0] =               "     |_________|";
        } else {
            rowOne[0][0] = hiddenCardStringRow;
            rowOne[1][0] = hiddenCardStringRow;
            rowOne[2][0] = hiddenCardStringRow;
            rowOne[3][0] = hiddenCardStringRow;
            rowOne[4][0] = hiddenCardStringRow;
            rowOne[5][0] = hiddenCardStringRow;
            rowOne[6][0] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[0][1] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[0][1]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[0][1]));
            rowOne[0][1] =               "      _________ ";
            rowOne[1][1] = String.format("   b:|%s      %s|",value, house);
            rowOne[2][1] =               "     |         |";
            rowOne[3][1] =               "     |         |";
            rowOne[4][1] =               "     |         |";
            rowOne[5][1] = String.format("     |%s      %s|",value, house);
            rowOne[6][1] =               "     |_________|";
        } else {
            rowOne[0][1] = hiddenCardStringRow;
            rowOne[1][1] = hiddenCardStringRow;
            rowOne[2][1] = hiddenCardStringRow;
            rowOne[3][1] = hiddenCardStringRow;
            rowOne[4][1] = hiddenCardStringRow;
            rowOne[5][1] = hiddenCardStringRow;
            rowOne[6][1] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[0][2] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[0][2]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[0][2]));
            rowOne[0][2] =               "      _________ ";
            rowOne[1][2] = String.format("   c:|%s      %s|",value, house);
            rowOne[2][2] =               "     |         |";
            rowOne[3][2] =               "     |         |";
            rowOne[4][2] =               "     |         |";
            rowOne[5][2] = String.format("     |%s      %s|",value, house);
            rowOne[6][2] =               "     |_________|";
        } else {
            rowOne[0][2] = hiddenCardStringRow;
            rowOne[1][2] = hiddenCardStringRow;
            rowOne[2][2] = hiddenCardStringRow;
            rowOne[3][2] = hiddenCardStringRow;
            rowOne[4][2] = hiddenCardStringRow;
            rowOne[5][2] = hiddenCardStringRow;
            rowOne[6][2] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[1][0] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[1][0]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[1][0]));
            rowTwo[0][0] =               "      _________ ";
            rowTwo[1][0] = String.format("   d:|%s      %s|",value, house);
            rowTwo[2][0] =               "     |         |";
            rowTwo[3][0] =               "     |         |";
            rowTwo[4][0] =               "     |         |";
            rowTwo[5][0] = String.format("     |%s      %s|",value, house);
            rowTwo[6][0] =               "     |_________|";
        } else {
            rowTwo[0][0] = hiddenCardStringRow;
            rowTwo[1][0] = hiddenCardStringRow;
            rowTwo[2][0] = hiddenCardStringRow;
            rowTwo[3][0] = hiddenCardStringRow;
            rowTwo[4][0] = hiddenCardStringRow;
            rowTwo[5][0] = hiddenCardStringRow;
            rowTwo[6][0] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[1][1] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[1][1]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[1][1]));
            rowTwo[0][1] =               "      _________ ";
            rowTwo[1][1] = String.format("   e:|%s      %s|",value, house);
            rowTwo[2][1] =               "     |         |";
            rowTwo[3][1] =               "     |         |";
            rowTwo[4][1] =               "     |         |";
            rowTwo[5][1] = String.format("     |%s      %s|",value, house);
            rowTwo[6][1] =               "     |_________|";
        } else {
            rowTwo[0][1] = hiddenCardStringRow;
            rowTwo[1][1] = hiddenCardStringRow;
            rowTwo[2][1] = hiddenCardStringRow;
            rowTwo[3][1] = hiddenCardStringRow;
            rowTwo[4][1] = hiddenCardStringRow;
            rowTwo[5][1] = hiddenCardStringRow;
            rowTwo[6][1] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[1][2] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[1][2]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[1][2]));
            rowTwo[0][2] =               "      _________ ";
            rowTwo[1][2] = String.format("   f:|%s      %s|",value, house);
            rowTwo[2][2] =               "     |         |";
            rowTwo[3][2] =               "     |         |";
            rowTwo[4][2] =               "     |         |";
            rowTwo[5][2] = String.format("     |%s      %s|",value, house);
            rowTwo[6][2] =               "     |_________|";
        } else {
            rowTwo[0][2] = hiddenCardStringRow;
            rowTwo[1][2] = hiddenCardStringRow;
            rowTwo[2][2] = hiddenCardStringRow;
            rowTwo[3][2] = hiddenCardStringRow;
            rowTwo[4][2] = hiddenCardStringRow;
            rowTwo[5][2] = hiddenCardStringRow;
            rowTwo[6][2] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[2][0] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[2][0]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[2][0]));
            rowThree[0][0] =               "      _________ ";
            rowThree[1][0] = String.format("   g:|%s      %s|",value, house);
            rowThree[2][0] =               "     |         |";
            rowThree[3][0] =               "     |         |";
            rowThree[4][0] =               "     |         |";
            rowThree[5][0] = String.format("     |%s      %s|",value, house);
            rowThree[6][0] =               "     |_________|";
        } else {
            rowThree[0][0] = hiddenCardStringRow;
            rowThree[1][0] = hiddenCardStringRow;
            rowThree[2][0] = hiddenCardStringRow;
            rowThree[3][0] = hiddenCardStringRow;
            rowThree[4][0] = hiddenCardStringRow;
            rowThree[5][0] = hiddenCardStringRow;
            rowThree[6][0] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[2][1] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[2][1]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[2][1]));
            rowThree[0][1] =               "      _________ ";
            rowThree[1][1] = String.format("   h:|%s      %s|",value, house);
            rowThree[2][1] =               "     |         |";
            rowThree[3][1] =               "     |         |";
            rowThree[4][1] =               "     |         |";
            rowThree[5][1] = String.format("     |%s      %s|",value, house);
            rowThree[6][1] =               "     |_________|";
        } else {
            rowThree[0][1] = hiddenCardStringRow;
            rowThree[1][1] = hiddenCardStringRow;
            rowThree[2][1] = hiddenCardStringRow;
            rowThree[3][1] = hiddenCardStringRow;
            rowThree[4][1] = hiddenCardStringRow;
            rowThree[5][1] = hiddenCardStringRow;
            rowThree[6][1] = hiddenCardStringRow;
        }

        if(threeByThreeCardMatrix[2][2] != null){
            String house = Card.convertHouseToDigit(threeByThreeCardMatrix[2][2]);
            String value = formatStringForDisplay(Card.convertRankToDigit(threeByThreeCardMatrix[2][2]));
            rowThree[0][2] =               "      _________ ";
            rowThree[1][2] = String.format("   i:|%s      %s|",value, house);
            rowThree[2][2] =               "     |         |";
            rowThree[3][2] =               "     |         |";
            rowThree[4][2] =               "     |         |";
            rowThree[5][2] = String.format("     |%s      %s|",value, house);
            rowThree[6][2] =               "     |_________|";
        } else {
            rowThree[0][2] = hiddenCardStringRow;
            rowThree[1][2] = hiddenCardStringRow;
            rowThree[2][2] = hiddenCardStringRow;
            rowThree[3][2] = hiddenCardStringRow;
            rowThree[4][2] = hiddenCardStringRow;
            rowThree[5][2] = hiddenCardStringRow;
            rowThree[6][2] = hiddenCardStringRow;
        }

        //print legend
        System.out.println("Legend:");
        System.out.println("    Houses: D = Diamonds, H = Hearts, S = Spades, C = Clubs");
        System.out.println("    Values:  K = King, Q = Queen, J = Jack, A = Ace");

        //print first row of cards
        for (int i = 0; i < rowOne.length ; i++) {
            System.out.println(rowOne[i][0] + rowOne[i][1] + rowOne[i][2]);
        }
        //print second row of cards
        for (int i = 0; i < rowOne.length ; i++) {
            System.out.println(rowTwo[i][0] + rowTwo[i][1] + rowTwo[i][2]);
        }
        //print third row of cards
        for (int i = 0; i < rowOne.length ; i++) {
            System.out.println(rowThree[i][0] + rowThree[i][1] + rowThree[i][2]);
        }
    }
}
