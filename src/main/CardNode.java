package main;

//TODO write java doc and comments
//TODO write tests
public class CardNode {
    private Card data;
    private CardNode next;

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

    public CardNode getNext() {
        return next;
    }

    public void setNext(CardNode nextNode) {
        this.next = nextNode;
    }
}
