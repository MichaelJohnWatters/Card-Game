import java.util.HashMap;

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

    /**
     * Checks if a Card is a face card
     * @param aCard the card in question.
     * @return boolean
     */
    private boolean isFaceCard(Card aCard) {
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

    public boolean containsCardValue(int cardValue) {
        boolean found = false;
        int index = 0;
        while (!found && index < numberOfCards) {

            //TODO swap if orders

            //If we find a card with the correct value, we must check it is not a face card.
            if (cardSlotsBag[index++].getRank().getValue() == cardValue) {

                //if the card is not a Face card
                if (!isFaceCard(cardSlotsBag[index])){
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

    //maybee return the 3 cards
    public boolean containsKingQueenJack() {
        boolean foundKing = false;
        boolean foundQueen = false;
        boolean foundJack = false;

        for (Card card : cardSlotsBag) {

            //if the current card is not null, check for each of the face cards.
            //if a face card exists set flag for that rank to true.
            if (card != null){
                if (card.getRank().equals(Rank.KING)) foundKing = true;
                if (card.getRank().equals(Rank.QUEEN)) foundQueen = true;
                if (card.getRank().equals(Rank.JACK)) foundJack = true;
            }
        }

        //Return all flags, only returns true if all flags are true, otherwise false.
        return foundKing && foundQueen && foundJack;
    }

    //TODO maybee return the two cards
    public boolean containsElevensPair() {

        //found flag, default false
        boolean foundPair = false;

        //Take each card in the cardSlotBag
        for (Card card : cardSlotsBag) {

            //if the card selected is not null then
            if (card != null) {

                //check if card is not a face card, as face cards are checked in method containsKingQueenJack.
                if(!isFaceCard(card)){

                    //if not a face card, find this cards pair, Elevens pairs will always be 11 minus the current cards value.
                    int requiredPairValue = (11 - card.getRank().getValue());

                    System.out.println("required Pair" + requiredPairValue);

                    //search for the require value pair
                    //we will not ignore the current card, as it would acctually be less performant to filter it out.
                    if(this.containsCardValue(requiredPairValue)) {

                        //set foundPair to true and break out of the current loop.
                        foundPair = true;
                        break;
                    }
                }
            }
        }
        return foundPair;
    }


    //@Override
    public Card[] toArray() {
        Card[] resultCardArray = new Card[FIXED_CAPACITY];
        System.arraycopy(cardSlotsBag, 0, resultCardArray, 0, numberOfCards);
        return resultCardArray;
    }


    public void display() {

        HashMap<Integer, String> intToLetterMap = new HashMap<>();
        intToLetterMap.put(0,"A");
        intToLetterMap.put(1,"B");
        intToLetterMap.put(2,"C");
        intToLetterMap.put(3,"D");
        intToLetterMap.put(4,"E");
        intToLetterMap.put(5,"F");
        intToLetterMap.put(6,"G");
        intToLetterMap.put(7,"H");
        intToLetterMap.put(8,"I");

        System.out.println();
        System.out.println("************* Cards on Table *************");
        for (int i = 0; i < numberOfCards ; i++) {
            System.out.println("    Slot " + intToLetterMap.get(i) + ": " + cardSlotsBag[i]);
        }
        System.out.println("******************************************");
    }

}
