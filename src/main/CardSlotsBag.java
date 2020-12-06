package main;

import java.util.Queue;

//TODO write java doc and comments
//TODO write tests
public final class CardSlotsBag {

    private Card[] bag;
    private int numberOfEntries;
    private static final int MAX_CAPACITY = 9;

    public CardSlotsBag() {
        bag = new Card[MAX_CAPACITY];
        numberOfEntries = 0;
    }

    public CardSlotsBag(Card[] copiedBag) {
        bag = copiedBag;
        numberOfEntries = countCards();
    }

    public boolean containsCardValue(int cardValue) {
        boolean found = false;
        int index = 0;

        while (!found && index < bag.length) {

            if(bag[index] != null){

                if (bag[index].getRank().getValue() == cardValue) {

                    if (!GameMechanics.isFaceCard(bag[index])) {
                        found = true;
                    }
                }
            }
            index++;
        }
        return found;
    }

    /**
     * Returns the Card if it finds the Card otherwise returns false.
     * @param cardValue
     * @return
     */
    public Card findsAndReturnsCardValue(int cardValue) {
        Card foundCard = null;
        int index = 0;

        while (index < bag.length) {

            if(bag[index] != null){

                if (bag[index].getRank().getValue() == cardValue) {

                    if (!GameMechanics.isFaceCard(bag[index])) {
                        foundCard = bag[index];
                    }
                }
            }
            index++;
        }

        return foundCard;
    }

    /**
     * Returns true if this contains 3 face cards of jack queen king.
     * @return boolean
     */
    public boolean containsKingQueenJack() {
        boolean foundKing  = false;
        boolean foundQueen = false;
        boolean foundJack  = false;

        for (Card card : bag) {

            //if the current card is not null, check for each of the face cards.
            //if a face card exists set flag for that rank to true.
            if (card != null) {
                if (card.getRank().equals(Rank.KING))  foundKing  = true;
                if (card.getRank().equals(Rank.QUEEN)) foundQueen = true;
                if (card.getRank().equals(Rank.JACK))  foundJack  = true;
            }
        }

        return foundKing && foundQueen && foundJack;
    }

    /**
     * If there is 3 face card pairs, returns an array with them, otherwise returns null;
     * @return either null or Card Array of size 3 with the 3 face cards.
     */
    public Card[] findAndReturnKingQueenJackPair(){
        Card king  = null;
        Card queen = null;
        Card jack  = null;

        //for each card in bag if its not a null slot, look for each of King, Queen and Jack
        for (Card card : bag) {
            if (card != null) {
                if (card.getRank().equals(Rank.KING))  king  = card;
                if (card.getRank().equals(Rank.QUEEN)) queen = card;
                if (card.getRank().equals(Rank.JACK))  jack  = card;
            }
        }

        if(king != null && queen != null && jack != null){
            return new Card[] {king, queen, jack};
        } else return null;
    }


    public boolean containsElevensPair() {

        //found flag, default false
        boolean foundPair = false;

        //Take each card in the cardSlotBag
        for (Card card : bag) {

            //if the card selected is not null then
            if (card != null) {

                //check if card is not a face card, as face cards are checked in method containsKingQueenJack.
                if(!GameMechanics.isFaceCard(card)){

                    //if not a face card, find this cards pair, main.Elevens pairs will always be 11 minus the current cards value.
                    int requiredPairValue = (11 - card.getRank().getValue());

                    //search for the require value pair
                    //we will not ignore the current card, as it would actually be less perfornat to filter it out.
                    //And will never result in a true result.
                    if(this.containsCardValue(requiredPairValue)) {
                        //set foundPair to true and break out of the current loop.
                        foundPair = true;
                    }
                }
            }
        }
        return foundPair;
    }

    public Card[] findAndReturnElevensPair() {
        Card[] foundElevensPair = null;

        //Take each card in the cardSlotBag
        for (Card card : bag) {

            //if the card selected is not null then
            if (card != null) {

                //check if card is not a face card, as face cards are checked in method containsKingQueenJack.
                if(!GameMechanics.isFaceCard(card)) {

                    //if not a face card, find this cards pair, Elevens pairs will always be 11 minus the current cards value.
                    int requiredPairValue = (11 - card.getRank().getValue());

                    //search for the require value pair
                    Card foundPairValueCard = this.findsAndReturnsCardValue(requiredPairValue);

                    //if findsAndReturnsCardValue is not null we found the card.
                    if(foundPairValueCard != null) {
                        //return the found pair of cards in a Array of size 2.
                        foundElevensPair = new Card[]{card, foundPairValueCard};

                        //break out of the loop as we found our pair.
                        break;
                    }
                }
            }
        }

        return foundElevensPair;
    }

    /**
     * Safe method of count the number of slots that are not null
     * @return int number of cards
     */
    public int countCards() {
        int cardCount = 0;

        for (int i = 0; i < bag.length; i++) {

            if (bag[i] !=null) {
                cardCount++;
            }
        }
        return cardCount;
    }

    /**
     * Safe method of count the number of slots that are null.
     * @return int number of null slots in array.
     */
    public int countEmptySlots() {
        int cardCount = 0;

        for (int i = 0; i < bag.length; i++) {

            if (bag[i] ==null) {
                cardCount++;
            }
        }
        return cardCount;
    }

    public Card cardAtPosition(int index){
        Card card = null;
        if(index >= 0 && index < 9) {
            if(bag[index] != null) {
                card = bag[index];
            }
        }
        return card;
    }

    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public boolean addNewEntry(Card newEntry) {
        if (isArrayFull()) return false;
        else {
            bag[numberOfEntries++] = newEntry;
            return true;
        }
    }

    public boolean isArrayFull() {
        return (bag.length == numberOfEntries);
    }

    public Card remove() {
        return removeElementAt(numberOfEntries-1);
    }

    public Card remove(Card anEntry) {
        boolean found = false;
        Card cardToReturn =  null;
        int index = 0;
        while(!found && index < numberOfEntries)
            if(bag[index].equals(anEntry)) found = true;
            else  index++;

        if(found) {
            cardToReturn = removeElementAt(index);
        }
        return cardToReturn;
    }

    //in demo
    private Card removeElementAt(int index) {
        Card result = null;

        if(!isEmpty() && index >= 0 && index < numberOfEntries) {
            result = bag[index];
            bag[index] = bag[numberOfEntries -1];
            bag[numberOfEntries -1] = null;
            numberOfEntries--;
        }
        return result;
    }

    public void clear() {
        while (!isEmpty()) remove();
    }

    public boolean contains(Card anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfEntries)
            if (bag[index++].equals(anEntry)) found = true;
        return found;
    }

    /**
     * Perform a clone or copy of the current bag, even null slots in the array.
     * @return Card[]
     */
    public Card[] toArrayCopy() {
        Card[] resultArray = new Card[MAX_CAPACITY];
        System.arraycopy(bag, 0, resultArray, 0, MAX_CAPACITY);
        return resultArray;
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

    /**
     * This method is used to display a cardSlotsBag
     *
     * Prints 3 rows of 3 cards, containing digit values representing houses and ranks.
     */
    public void display(boolean withLegend) {
        //16 space string, to pad out print lines if a card slot is empty.
        String hiddenCardStringRow = "                ";

        //represents each row of cards in a 3x3.
        String [][] rowOne   = new String[7][3];
        String [][] rowTwo   = new String[7][3];
        String [][] rowThree = new String[7][3];

        for (int i = 0; i < 3; i++) {

            if(bag[i] != null){
                String house = Card.extractHouseAsDigitWithColor(bag[i]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(bag[i]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i);
                rowOne[0][i] =               "      _________ ";
                rowOne[1][i] = String.format("   %s:|%s      %s|", cardSlot, value, house);
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

            if(bag[i+3] != null){
                String house = Card.extractHouseAsDigitWithColor(bag[i+3]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(bag[i+3]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i+3);
                rowTwo[0][i] =               "      _________ ";
                rowTwo[1][i] = String.format("   %s:|%s      %s|", cardSlot, value, house);
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

            if(bag[i+6] != null){
                String house = Card.extractHouseAsDigitWithColor(bag[i+6]);
                String value = formatStringForDisplay(Card.extractRankAsDigit(bag[i+6]));
                String cardSlot = GameMechanics.cardSelectionNumberToString(i+6);
                rowThree[0][i] =               "      _________ ";
                rowThree[1][i] = String.format("   %s:|%s      %s|", cardSlot, value, house);
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
        if(withLegend) {
            System.out.println("Legend:");
            System.out.println("    Houses: D = Diamonds, H = Hearts, S = Spades, C = Clubs");
            System.out.println("    Values:  K = King, Q = Queen, J = Jack, A = Ace");
        }

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
