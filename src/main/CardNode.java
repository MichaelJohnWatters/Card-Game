package main;

public class CardNode<Card> {
    private Card data;
    private CardNode<Card> next;

    public CardNode(Card dataValue) {
        this.data = dataValue;
        this.next = null;
    }

    public Card getData() {
        return data;
    }

    public void setData(Card dataValue) {
        this.data = dataValue;
    }

    public CardNode<Card> getNext() {
        return next;
    }

    public void setNext(CardNode<Card> nextNode) {
        this.next = nextNode;
    }
}
