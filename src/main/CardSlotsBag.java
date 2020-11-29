package main;

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

                    //if not a face card, find this cards pair, main.Elevens pairs will always be 11 minus the current cards value.
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
        if (!foundPair) System.out.println("No main.Elevens Pair"); else System.out.println("This round contains an main.Elevens pair");
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
     * Used to format card values if single digit or double.
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
            //cant convert to string so much be a single letter either, Jack King Queen or Ace.
            return str + " ";
        }
    }


    //Todo fix, no need for 3x duplication, quick solution, fix if we have time.
    public void display(){

        //16 space string, to pad out print lines if a card slot is empty.
        String hiddenCardStringRow = "                ";

        //represents each row of cards in a 3x3.
        String [][] rowOne   = new String[7][3];
        String [][] rowTwo   = new String[7][3];
        String [][] rowThree = new String[7][3];

        for (int i = 0; i < 3; i++) {

            if(cardSlotsBag[i] != null){
                String house = Card.extractHouseAsDigit(cardSlotsBag[i]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(cardSlotsBag[i]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i);
                rowOne[0][i] =               "      _________ ";
                rowOne[1][i] = String.format("   %s:|%s      %s|", cardSlot,value, house);
                rowOne[2][i] =               "     |         |";
                rowOne[3][i] =               "     |         |";
                rowOne[4][i] =               "     |         |";
                rowOne[5][i] = String.format("     |%s      %s|",value, house);
                rowOne[6][i] =               "     |_________|";
            } else {
                rowOne[0][i] = hiddenCardStringRow;
                rowOne[1][i] = hiddenCardStringRow;
                rowOne[2][i] = hiddenCardStringRow;
                rowOne[3][i] = hiddenCardStringRow;
                rowOne[4][i] = hiddenCardStringRow;
                rowOne[5][i] = hiddenCardStringRow;
                rowOne[6][i] = hiddenCardStringRow;
            }
        }

        for (int i = 0; i < 3; i++) {

            if(cardSlotsBag[i+3] != null){
                String house = Card.extractHouseAsDigit(cardSlotsBag[i+3]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(cardSlotsBag[i+3]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i+3);
                rowTwo[0][i] =               "      _________ ";
                rowTwo[1][i] = String.format("   %s:|%s      %s|",cardSlot,value, house);
                rowTwo[2][i] =               "     |         |";
                rowTwo[3][i] =               "     |         |";
                rowTwo[4][i] =               "     |         |";
                rowTwo[5][i] = String.format("     |%s      %s|",value, house);
                rowTwo[6][i] =               "     |_________|";
            } else {
                rowTwo[0][i] = hiddenCardStringRow;
                rowTwo[1][i] = hiddenCardStringRow;
                rowTwo[2][i] = hiddenCardStringRow;
                rowTwo[3][i] = hiddenCardStringRow;
                rowTwo[4][i] = hiddenCardStringRow;
                rowTwo[5][i] = hiddenCardStringRow;
                rowTwo[6][i] = hiddenCardStringRow;
            }
        }

        for (int i = 0; i < 3; i++) {

            if(cardSlotsBag[i+6] != null){
                String house = Card.extractHouseAsDigit(cardSlotsBag[i+6]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(cardSlotsBag[i+6]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i+6);
                rowThree[0][i] =               "      _________ ";
                rowThree[1][i] = String.format("   %s:|%s      %s|",cardSlot,value, house);
                rowThree[2][i] =               "     |         |";
                rowThree[3][i] =               "     |         |";
                rowThree[4][i] =               "     |         |";
                rowThree[5][i] = String.format("     |%s      %s|",value, house);
                rowThree[6][i] =               "     |_________|";
            } else {
                rowThree[0][i] = hiddenCardStringRow;
                rowThree[1][i] = hiddenCardStringRow;
                rowThree[2][i] = hiddenCardStringRow;
                rowThree[3][i] = hiddenCardStringRow;
                rowThree[4][i] = hiddenCardStringRow;
                rowThree[5][i] = hiddenCardStringRow;
                rowThree[6][i] = hiddenCardStringRow;
            }
        }

        //print legend out legend before cards
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
