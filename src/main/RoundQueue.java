package main;

/**
 * This Class is a object that holds information about each round.
 * As new rounds are created they will be enqueued and when we replay a round we can dequeue the round
 *
 */
//TODO consider changing to a linked list
public class RoundQueue {

    private Round front, rear;

    /**
     * Constructor for a RoundQueue, creates an queue with no front or rear
     */
    public RoundQueue(){
        front = null;
        rear  = null;
    }

    /**
     * Enqueue a new round
     * @param newRound the round to enqueue
     */
    public void enqueue(Round newRound) {
        if(front==null){
            front = newRound;
            rear = newRound;
        } else {
            rear.setNextRound(newRound);
            rear = newRound;
        }
    }

    /**
     * Removes the first round in the queue.
     * @return the removed Round
     */
    public Round dequeue() {
        if(front==null) return  null;
        else {
            Round valueToReturn = front;
            front = front.getNextRound();
            if (front ==null) rear = null;
            return valueToReturn;
        }
    }

    /**
     * Returns the front round without removing it from the RoundQueue
     * @return the Front round but does not remove
     */
    public Round getFront() {
        if(front == null) return null;
        else return front;
    }

    /**
     * Checks if the Queue is empty or not
     * @return true if empty false if not
     */
    public boolean isEmpty() {
        return (front == null);
    }

    /**
     * Clears the queue, by setting both front and rear to null
     */
    public void clear() {
        front = null;
        rear  = null;
    }
}
