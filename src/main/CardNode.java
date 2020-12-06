package main;

/**
 * A class to as a wrapper object for a Card when being used in the Deck Class
 * Allows Card to be Abstracted from an ADT.
 */
public class CardNode {

    private Card data;
    private CardNode next;

    /**
     * Constructor for CardNode
     * @param dataValue Card held by the CardNode
     */
    public CardNode(Card dataValue) {
        this.data = dataValue;
        this.next = null;
    }

    /**
     * Return the Card held in the CardNode
     * @return Card the card held in the node
     */
    public Card getData() {
        return data;
    }

    /**
     * Set the Card in the CardNode
     * @param dataValue the Card you want to set as the CardNodes data value.
     */
    public void setData(Card dataValue) {
        this.data = dataValue;
    }

    /**
     * Get the next CardNode linked to this CardNode
     * @return CardNode
     */
    public CardNode getNext() {
        return next;
    }

    /**
     * Set the next CardNode
     * @param nextNode set the next CardNode
     */
    public void setNext(CardNode nextNode) {
        this.next = nextNode;
    }
}
