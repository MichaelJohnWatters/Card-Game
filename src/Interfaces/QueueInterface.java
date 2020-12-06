package Interfaces;

/**
 * A Generic Type of a Queue ADT, for use in this project.
 * With be used in the RoundQueue.
 * @param <T> the type of the stack.
 */
public interface QueueInterface<T> {

    /**
     * Add a entity to the back of the que or front and back if its the first object in the Queue.
     * @param newEntry the entity to add to the back of the queue
     */
    public void enqueue(T newEntry);

    /**
     * Removes the entity at the front of the queue
     * @return returns the Object that was dequeued
     */
    public T dequeue();

    /**
     * Returns but does not remove the Object at the front of the queue.
     * @return returns Object at the front of the queue.
     */
    public T getFront();

    /**
     * Check if the Queue is empty or not
     * @return returns true if empty, false if not
     */
    public boolean isEmpty();

    /**
     * Clears the Queue.
     */
    public void clear();

}