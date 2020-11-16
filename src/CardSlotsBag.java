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

    public boolean containsThreeFaceCards() {
        boolean foundKing = false;
        boolean foundQueen = false;
        boolean foundJack = false;

        for (Card card : cardSlotsBag) {
            if (card.getRank().equals(Rank.KING)) foundKing = true;
            if (card.getRank().equals(Rank.QUEEN)) foundQueen = true;
            if (card.getRank().equals(Rank.JACK)) foundJack = true;
        }

        return foundKing && foundQueen && foundJack;
    }

    public boolean containsElevensPair() {
        boolean foundPair = false;

        //Take each card in the cardSlotBag and see if any of the other
        //Cards in the cardSlotBag equal Eleven.
        for (Card card : cardSlotsBag) {

            //if the card is not null then
            if (card != null) {

                //loop through all the slots in the cardSlotsBag
                for (int i = 0; i < cardSlotsBag.length; i++) {

                    //if the slot is not empty and not a face card,
                    //sum the cards if they equal eleven break loop and return true
                    if (cardSlotsBag[i] != null
                            && cardSlotsBag[i].getRank() != Rank.KING
                            && cardSlotsBag[i].getRank() != Rank.QUEEN
                            && cardSlotsBag[i].getRank() != Rank.JACK)
                    {
                        //Sum the cards
                        int sumOfCards = card.getRank().getValue() + cardSlotsBag[i].getRank().getValue();

                        //if the cards equal 11, set foundPair to true and break for-i loop
                        if(sumOfCards == 11) foundPair = true;
                        break;
                    }

                }
                //break foreach loop if pair has been found.
                if (foundPair) break;
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
        System.out.println();
        System.out.println("*************Cards on Table*************");
        for (int i = 0; i < numberOfCards ; i++) {
            System.out.println("    Slot " + i + ", Card: " + cardSlotsBag[i]);
        }
        System.out.println("****************************************");
    }

//    public void display() {
//        System.out.println("********************");
//        displayArray(0, numberOfCards-1);
//        System.out.println("********************");
//    }

//    private void displayArray(int first, int last){
//        System.out.println("" + cardSlotsBag[first]);
//        if(first < last) displayArray(first + 1, last);
//    }

//    public String toString(){
//        String strResult = "CardsOnTable[";
//        for(int i = 0; i < numberOfCards; i++)
//            strResult += cardSlotsBag[i] + " ";
//        return strResult += "]";
//    }

}
