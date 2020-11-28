
public class RoundQueue {

    private Round front, rear;

    public RoundQueue(Round firstRound){
        front = firstRound;
        rear  = null;
    }

    //@Override
    public void enqueue(Round newRound) {
        if(front==null){
            front = newRound;
            rear = newRound;
        } else {
            rear.setNextRound(newRound);
            rear = newRound;
        }
    }

    public Round dequeue() {
        if(front==null) return  null;
        else {
            Round valueToReturn = front;
            front = front.getNextRound();
            if (front ==null) rear = null;
            return valueToReturn;
        }
    }

    public Round getFront() {
        if(front == null) return null;
        else return front;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void clear() {
        front = null;
        rear  = null;
    }

}
