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
