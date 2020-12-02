package main;

/**
 * This Class is a object that holds information about each round.
 * As new rounds are created they will be enqueued and when we replay a round we can dequeue the round
 *
 */

//TODO consider changing to a linked list
//TODO ADD TESTS
//TODO ADD JAVA DOC
public class RoundQueue {

    private Round front, rear;

    public RoundQueue(){
        front = null;
        rear  = null;
    }

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
