public class CardNode<T> {
    private T data;
    private CardNode<T> next;

    public CardNode(T dataValue) {
        this.data = dataValue;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T dataValue) {
        this.data = dataValue;
    }

    public CardNode<T> getNext() {
        return next;
    }

    public void setNext(CardNode<T> nextNode) {
        this.next = nextNode;
    }
}
