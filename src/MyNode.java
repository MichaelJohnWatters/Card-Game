public class MyNode<T> {

    private T data;
    private MyNode<T> next;

    public MyNode(T dataValue) {
        this.data = dataValue;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T dataValue) {
        this.data = dataValue;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> nextNode) {
        this.next = nextNode;
    }
}
